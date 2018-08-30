package com.base.ui.org.adapter.abslistview.base;


import com.base.ui.org.adapter.abslistview.ViewHolder;

/**
 * Created by lixingxing on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ViewHolder holder, T t, int position);



}
