package com.hwq.mvvmlibrary.binding.viewadapter.viewgroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hwq.mvvmlibrary.binding.adapter.ItemBinding;


/**
 * Created by king on 2018.12.21
 */
public final class ViewAdapter {

    @BindingAdapter({"itemView", "observableList"})
    public static void addViews(ViewGroup viewGroup, final ItemBinding itemBinding, final ObservableList<IBindingItemViewModel> viewModelList) {
        if (viewModelList != null && !viewModelList.isEmpty()) {
            viewGroup.removeAllViews();
            for (IBindingItemViewModel viewModel : viewModelList) {
                ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        itemBinding.layoutRes(), viewGroup, true);
                binding.setVariable(itemBinding.variableId(), viewModel);
                viewModel.injecDataBinding(binding);
            }
        }
    }
}

