package com.hwq.mvvmlibrary.binding.adapter.itembindings;


import com.hwq.mvvmlibrary.binding.adapter.ItemBinding;
import com.hwq.mvvmlibrary.binding.adapter.OnItemBind;

/**
 * An {@link OnItemBind} that selects item views by delegating to each item. Items must implement
 * {@link ItemBindingModel}.
 */
public class OnItemBindModel<T extends ItemBindingModel> implements OnItemBind<T> {

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, T item) {
        item.onItemBind(itemBinding);
    }
}

