package com.base.ui.org.animation.scroll;

public interface DiscrollInterface {

    /**
     * 滑动的时候做的具体的事情
     * 执行动画的操作我们都放在这里
     */
    public void onDiscroll(float ratio);


    public void onResetDiscroll();

}
