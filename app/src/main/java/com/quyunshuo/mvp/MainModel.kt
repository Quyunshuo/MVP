package com.quyunshuo.mvp

import android.os.Handler
import kotlin.random.Random

/**
 * @Author: MiYan
 * @Time:   2020-01-03
 * @Class:  MainModel
 * @Remark: Main的Model层:Model层主要负责数据的一些操作
 */
class MainModel : IContract.IModel {
    var mHandler: Handler? = Handler()
    /**
     * 具体的数据请求逻辑(此处利用延时(1s)操作模拟网络请求)
     * 通过传过来的回调接口根据请求结果调用不同的回调方法
     */
    override fun startRequest(callBack: IContract.CallBack) {
        /**
         * Random.nextBoolean()会随机返回一个boolean值表示请求失败或成功
         * 如果成功 通过回调通知P层并返回数据
         * 如果失败 通过回调通知P层
         */
        mHandler?.postDelayed(
            { if (Random.nextBoolean()) callBack.success("Kotlin nb!") else callBack.failure() },
            1000
        )
    }

    /**
     * 做销毁处理 防止内存泄漏
     */
    override fun onDestroy() {
        mHandler?.removeCallbacksAndMessages(null)
        mHandler = null
    }
}