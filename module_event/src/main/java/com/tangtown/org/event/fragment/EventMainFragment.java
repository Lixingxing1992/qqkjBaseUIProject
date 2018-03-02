package com.tangtown.org.event.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.app.org.constants.RouterConstans;
import com.tangtown.org.event.R;

import com.app.org.base.BaseFragment;

/**
 * Created by lixingxing on 2018/1/11.
 */
@Route(path= RouterConstans.EventMainFragment)
public class EventMainFragment extends BaseFragment{

    public EventMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.event_main, container, false);
    }
}
