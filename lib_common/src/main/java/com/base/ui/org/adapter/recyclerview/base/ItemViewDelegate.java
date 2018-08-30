package com.base.ui.org.adapter.recyclerview.base;


/**
 * Created by lixingxing on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
