package com.tangtown.org.coupon.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.org.constants.RouterConstans;
import com.tangtown.org.coupon.R;

import com.app.org.base.BaseFragment;

/**
 * Created by lixingxing on 2018/1/11.
 */
@Route(path= RouterConstans.CouponMainFragment)
public class CouponMainFragment extends BaseFragment{

    public CouponMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.coupon_main, container, false);
    }
}
