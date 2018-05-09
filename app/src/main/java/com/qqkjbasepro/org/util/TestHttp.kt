package com.qqkjbasepro.org.util

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.View
import com.app.org.base.BaseActivity
import com.app.org.utils.BaseToastUtil
import com.app.org.view.dialog.BaseDialogLoading
import com.app_res.org.util.http.HttpHandlerCallBack
import com.app_res.org.util.http.HttpUtils
import com.github.anzewei.parallaxbacklayout.ParallaxBack
import com.qqkjbasepro.org.R
import com.qqkjbasepro.org.config.AppConfig
import kotlinx.android.synthetic.main.text_http.*

/**
 * Created by lixingxing on 2018/4/16.
 */
@ParallaxBack(edge = ParallaxBack.Edge.LEFT, layout = ParallaxBack.Layout.PARALLAX)
class TestHttp : BaseActivity(){
    override fun setRootView() {
    }

    override fun initView() {
    }

    override fun getData() {
    }

    override fun initDefaultData(intent: Intent?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_http)
        setupTitleLayout(baseTitle,false)
        baseTitle.title = "测试"
    }

    fun onBtnClick(view: View) {
        when (view.id) {
            R.id.btn_get -> {
                val baseDialogLoading = BaseDialogLoading(baseContext)
                        .setLoadingText("正在get请求中...")
                baseDialogLoading.show()

                HttpUtils().initUrl(AppConfig.BaseUrl + "api/activity/getActivityList")
                        .initParams("cardCode",10001,"page", 1, "size", 50)
                        .initHttpCallBack(object : HttpHandlerCallBack(baseDialogLoading){
                            override fun hasError() {
                            }
                            override fun hasNoData() {
                            }
                            override fun dealMessage(msg: Message) {
                                BaseToastUtil.showShortToastSafe(msg.obj.toString())
                                baseDialogLoading.cancel()
                            }
                        })
                        .goHttp()
            }
            R.id.btn_post->{
                val baseDialogLoading = BaseDialogLoading(baseContext)
                        .showDialog("正在 post 请求中...")
                HttpUtils(false).initUrl(AppConfig.BaseUrl + "api/activity/getActivityLists")
                        .initParams("cardCode",10001,"page", 1, "size", 50)
                        .initHttpCallBack(object : HttpHandlerCallBack(baseDialogLoading){
                            override fun hasError() {
                            }

                            override fun hasNoData() {
                            }

                            override fun dealMessage(msg: Message) {
                                BaseToastUtil.showShortToastSafe(msg.obj.toString())
                                baseDialogLoading.cancel()
                            }
                        })
                        .goHttp()
            }
            else -> {
            }
        }
    }
}
