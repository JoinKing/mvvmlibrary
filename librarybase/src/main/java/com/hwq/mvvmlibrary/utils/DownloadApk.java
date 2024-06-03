package com.hwq.mvvmlibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import java.io.File;

/**
 * A simple {@link DownloadApk} subclass.
 * Created by WenqiangHuang
 * Creation time 2020/7/10
 * Email vieqqw@163.com
 */
public class DownloadApk {
    public static void installAPk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Android7.0及以上版本
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            //Uri contentUri = FileProvider.getUriForFile(mContext, "应用包名" + ".fileProvider", file);//参数二:应用包名+".fileProvider"(和步骤一中的Manifest文件中的provider节点下的authorities对应)
            Uri contentUri = FileProvider.getUriForFile(Utils.getContext(), "com.hwq.lingchuang" + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");

        } else {
            // Android7.0以下版本
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        Utils.getContext().startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }




}
