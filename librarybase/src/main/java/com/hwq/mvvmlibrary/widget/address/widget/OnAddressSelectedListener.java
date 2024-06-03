package com.hwq.mvvmlibrary.widget.address.widget;


import com.hwq.mvvmlibrary.widget.address.bean.City;
import com.hwq.mvvmlibrary.widget.address.bean.County;
import com.hwq.mvvmlibrary.widget.address.bean.Province;
import com.hwq.mvvmlibrary.widget.address.bean.Street;

/**
 * Created by king on 2019/01/16.
 */
public interface OnAddressSelectedListener {
    void onAddressSelected(Province province, City city, County county, Street street);
}
