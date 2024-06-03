package com.hwq.mvvmlibrary.base;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.hwq.mvvmlibrary.R;
import com.hwq.mvvmlibrary.bus.Messenger;
import com.hwq.mvvmlibrary.utils.AppConfigUtils;
import com.hwq.mvvmlibrary.utils.UltimateBar;
import com.hwq.mvvmlibrary.widget.ErBaseTitleView;
import com.hwq.mvvmlibrary.widget.dialog.LoadingDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by king on 2018.12.21
 * 一个拥有DataBinding框架的基Activity
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity implements IBaseView {
    protected String TAG = getClass().getName();
    protected V binding;
    protected VM viewModel;
    protected int viewModelId;
    protected LoadingDialog dialog;
    protected List<LifeCycleListener> mLifeCycleListeners;
    private ErBaseTitleView titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mLifeCycleListeners = new ArrayList<>();
        if (mLifeCycleListeners != null)
            for (LifeCycleListener listener : mLifeCycleListeners) listener.onCreate();
        //页面接受的参数方法
        initParam();
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState);
        //私有的ViewModel与View的契约事件回调逻辑
        registorUIChangeLiveDataCallBack();
        //页面数据初始化方法
        initData();
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable();
        //注册RxBus
        viewModel.registerRxBus();
        //沉浸式
        initUltimateBar(
                AppConfigUtils.getInstance().isBarColor(),
                AppConfigUtils.getInstance().getBarColor(),
                AppConfigUtils.getInstance().getBarAlpha(),
                new SoftReference<Activity>(this)
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mLifeCycleListeners != null)
            for (LifeCycleListener listener : mLifeCycleListeners) listener.onStart();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (mLifeCycleListeners != null)
            for (LifeCycleListener listener : mLifeCycleListeners) listener.onReStart();
    }


    protected int COLOR_BLACK = 0X001;//状态栏字体为黑色
    protected int COLOR_WHITE = 0X002;//状态栏字体为白色

    /**
     * @param b                    true 根据状态栏改变颜色 false 设置状态栏背景色
     * @param color                颜色
     * @param alpha                透明度
     * @param contextSoftReference 上下文
     */
    protected void initUltimateBar(boolean b, int color, int alpha, SoftReference<Activity> contextSoftReference) {
        if (b) {
            UltimateBar ultimateBar = new UltimateBar(contextSoftReference);
            ultimateBar.setImmersionBar();
            if (barColor() == COLOR_WHITE) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置状态栏字体颜色为浅色
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏字体颜色为深色
            }
        } else {
            ImmersionBar.with(this)
                    .barColor(color, alpha)
                    .init();
        }
    }

    protected int barColor() {
        return AppConfigUtils.getInstance().getBarTextColor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel);
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
        viewModel.removeRxBus();
        viewModel = null;
        binding.unbind();
        dialog = null;


    }

    protected Boolean useCommonTitle(){
        return false;
    }
    /**
     * 获取标题栏布局.
     */
    public ErBaseTitleView getTitleBar() {
        titleView.setVisibility(View.VISIBLE);
        titleView.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return titleView;
    }
    /**
     * 注入绑定
     */
    private void initViewDataBinding(Bundle savedInstanceState) {
        if(useCommonTitle()){
            LayoutInflater mInflater = LayoutInflater.from(this);
            ViewGroup mRootView = (ViewGroup) View.inflate(this, R.layout.er_base_layout_titlebar, null);
            titleView = mRootView.findViewById(R.id.ctBaseTitle);
            FrameLayout frameLayout = mRootView.findViewById(R.id.flBaseContent);
            super.setContentView(mRootView);
            binding = DataBindingUtil
                    .inflate(mInflater, initContentView(savedInstanceState), frameLayout, true);
        }else {
            //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
            binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        }


        viewModelId = initVariableId();
        viewModel = initViewModel();
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) createViewModel(this, modelClass);
        }
        //关联ViewModel
        binding.setVariable(viewModelId, viewModel);
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }

    //刷新布局
    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(viewModelId, viewModel);
        }
    }


    /**
     * =====================================================================
     **/
    //注册ViewModel与View的契约UI回调事件
    private void registorUIChangeLiveDataCallBack() {
        //加载对话框显示
        viewModel.getUC().getShowDialogEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String title) {
                showDialog(title);
            }
        });
        //加载对话框消失
        viewModel.getUC().getDismissDialogEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                dismissDialog();
            }
        });
        //跳入新页面
        viewModel.getUC().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                startActivity(clz, bundle);
            }
        });
        //跳入ContainerActivity
        viewModel.getUC().getStartContainerActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                String canonicalName = (String) params.get(BaseViewModel.ParameterField.CANONICAL_NAME);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                startContainerActivity(canonicalName, bundle);
            }
        });
        //关闭界面
        viewModel.getUC().getFinishEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                finish();
            }
        });
        //关闭上一层
        viewModel.getUC().getOnBackPressedEvent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                onBackPressed();
            }
        });
        viewModel.getUC().getStartActivityForResultEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                startActivityForResult(clz, bundle);
            }
        });
    }
    public void startActivityForResult(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        int requestCode = 0;
        if (bundle != null) {
            intent.putExtras(bundle);
            requestCode = bundle.getInt(BaseViewModel.ParameterField.REQUEST_CODE);
        }
        intent.putExtra(BaseViewModel.ParameterField.REQUEST_CODE, requestCode);
        startActivityForResult(intent, requestCode);
    }
    public void showDialog(String title) {
        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }

        dialog.setMessage(title);

        if (!dialog.isShowing) {
            dialog.setDrawable(AppConfigUtils.getInstance().getImage());
            dialog.showAnimation();
        }

    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dissAnimation();
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    public void startContainerActivity(String canonicalName) {
        startContainerActivity(canonicalName, null);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    public void startContainerActivity(String canonicalName, Bundle bundle) {
        Intent intent = new Intent(this, ContainerActivity.class);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle);
        }
        startActivity(intent);
    }

    /**
     * =====================================================================
     **/
    @Override
    public void initParam() {

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {

    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }


    @Override
    public void onResume() {
        super.onResume();

        if (mLifeCycleListeners != null)
            for (LifeCycleListener listener : mLifeCycleListeners) listener.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mLifeCycleListeners != null)
            for (LifeCycleListener listener : mLifeCycleListeners) listener.onPause();
        dismissDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLifeCycleListeners != null)
            for (LifeCycleListener listener : mLifeCycleListeners) listener.onStop();
    }


    public void addLifeCycleListener(LifeCycleListener listener) {
        if (mLifeCycleListeners != null && listener != null) mLifeCycleListeners.add(listener);
    }

    public void addAllLifeCycleListener(List<LifeCycleListener> listeners) {
        if (mLifeCycleListeners != null && listeners != null) mLifeCycleListeners.addAll(listeners);
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {
        if (mLifeCycleListeners != null) mLifeCycleListeners.remove(listener);
    }


    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return resources;
    }

    protected void addTextChangedListener(final EditText a, final EditText b, final int max) {
        a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == max) {
                    b.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
