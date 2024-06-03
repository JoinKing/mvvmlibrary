package com.hwq.mvvmlibrary.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseBindViewHolder extends RecyclerView.ViewHolder {
    ViewDataBinding mDataBinding;
    public BaseBindViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mDataBinding=binding;
    }

    public ViewDataBinding getBinding() {
        return mDataBinding;
    }
}
