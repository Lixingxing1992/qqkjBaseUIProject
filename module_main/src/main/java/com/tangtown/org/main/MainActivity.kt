package com.tangtown.org.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.app.org.constants.RouterConstans
import com.app.org.base.BaseActivity
import com.app.org.base.BaseFragment
import com.app.org.view.NoScrollViewPager

@Route(path= RouterConstans.MainActivity)
class MainActivity : BaseActivity() {

    private var mPager: NoScrollViewPager? = null
    private var mFragments = ArrayList<BaseFragment>()
    private var mAdapter: FragmentAdapter? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mPager!!.setCurrentItem(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mPager!!.setCurrentItem(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mPager!!.setCurrentItem(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        initViewPager()
    }

    private fun initViewPager() {
        val fragment1 = ARouter.getInstance().build(RouterConstans.CouponMainFragment).navigation() as BaseFragment
        val fragment2 = ARouter.getInstance().build(RouterConstans.EventMainFragment).navigation() as BaseFragment
        val fragment3 = ARouter.getInstance().build(RouterConstans.CouponMainFragment).navigation() as BaseFragment
        mFragments.add(fragment1)
        mFragments.add(fragment2)
        mFragments.add(fragment3)
        mPager = findViewById(R.id.container_pager) as NoScrollViewPager
        mAdapter = FragmentAdapter(supportFragmentManager, mFragments)
        mPager!!.setPagerEnabled(false)
        mPager!!.setAdapter(mAdapter)
    }
}
