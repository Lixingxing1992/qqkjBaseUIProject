package com.tangtown.org.coupon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.github.anzewei.parallaxbacklayout.ParallaxBack
import com.app.org.constants.RouterConstans

@ParallaxBack(edge = ParallaxBack.Edge.LEFT, layout = ParallaxBack.Layout.PARALLAX)
@Route(path= RouterConstans.CouponMainActivity)
class CouponMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coupon_main)
    }
}

