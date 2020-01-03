package com.quyunshuo.mvp

/**
 * @Author: MiYan
 * @Time:   2020-01-03
 * @Class:  IContract
 * @Remark: 契约类 减少接口的数量
 */
interface IContract {

    /**
     * View层接口
     */
    interface IView {
        fun requestSucceeded(str: String)

        fun requestFailed()
    }

    /**
     * Presenter层接口
     */
    interface IPresenter {
        fun request()
        fun onDestroy()
    }

    /**
     * Model层接口
     */
    interface IModel {
        fun startRequest(callBack: CallBack)

        fun onDestroy()
    }

    /**
     * 回调接口
     */
    interface CallBack {

        fun success(str: String)

        fun failure()
    }
}