package com.base.ui.org.itemtouchhelper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by lixingxing on 2018/7/11.
 */
public class SimpleItemTouchCallback extends ItemTouchHelper.Callback {

    private static final String TAG = "ItemTouchCallback";
    private ItemTouchHelperAdapterCallback adapterCallback;

    public SimpleItemTouchCallback(ItemTouchHelperAdapterCallback adapterCallback) {
        this.adapterCallback = adapterCallback;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        //callback回调监听哪些动作？---判断方向
        //makeMovementFlags(UP | DOWN, LEFT);
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);


    }

    @Override
    public boolean onMove(RecyclerView arg0, RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
        // 监听滑动（水平方向、垂直方向）
        //让数据集合中的两个数据进行位置交换
        //同时还要刷新RecyclerView
        adapterCallback.onItemMove(srcHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {
        Log.d(TAG, "onChildDraw");
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder holder, int arg1) {
        // 滑动动作的时候回调
        //1.删除数据集合里面的position位置的数据
        //2.刷新adapter
        adapterCallback.onItemSwiped(holder.getAdapterPosition());
    }

    //滑动消失的距离，当滑动小于这个值的时候会删除这个item，否则不会视为删除
    //返回值作为用户视为拖动的距离
    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.1f;
    }
    //返回值滑动消失的距离，滑动小于这个值不消失，大于消失
    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return 5f;
    }

    //设置手指离开后ViewHolder的动画时间
    @Override
    public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return 100;
    }

    //网格型RecyclerView
    @Override
    public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.9f;
    }

    //返回值决定是否有滑动操作
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


}
