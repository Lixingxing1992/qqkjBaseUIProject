package com.tangtown.org.event

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.github.anzewei.parallaxbacklayout.ParallaxBack
import com.app.org.constants.RouterConstans

@ParallaxBack(edge = ParallaxBack.Edge.LEFT, layout = ParallaxBack.Layout.PARALLAX)
@Route(path= RouterConstans.EventMainActivity)
class EventMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_main)
    }
}