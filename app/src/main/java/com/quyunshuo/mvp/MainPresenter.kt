package com.quyunshuo.mvp

/**
 * @Author: MiYan
 * @Time:   2020-01-03
 * @Class:  MainPresenter
 * @Remark: Main的Presenter层: Presenter层负责连接View层和Model层,它是View层和Model层之间的桥梁 在构造此类的实例时就要传入View层和Model层的实例
 */
class MainPresenter(private val mView: IContract.IView, private val mModel: IContract.IModel) :
    IContract.IPresenter {

    /**
     * V层调用此方法通知当前P层执行M层暴露出来的接口来请求数据
     * M层请求数据后悔通过回调来返回结果
     */
    override fun request() =
        mModel.startRequest(object : IContract.CallBack {
            /**
             * 请求成功 通知V层更新UI并传入M层传过来的数据给V层使用
             */
            override fun success(str: String) = mView.requestSucceeded(str)

            /**
             * 请求失败 通知V层更新UI
             */
            override fun failure() = mView.requestFailed()
        })

    /**
     * 做销毁处理 防止内存泄漏
     */
    override fun onDestroy() {
        mModel.onDestroy()
    }
}