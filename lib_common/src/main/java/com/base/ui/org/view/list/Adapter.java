package com.base.ui.org.view.list;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lixingxing on 2018/7/27.
 */
public interface Adapter {

    int getCount() ;
    //Item的类型
    int getItemViewType(int row);
    //Item的类型数量
    int getViewTypeCount();

    View getView(int postion, View convertView, ViewGroup parent);

    int getHeight(int index);
}
