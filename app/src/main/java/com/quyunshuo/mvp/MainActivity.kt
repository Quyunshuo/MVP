package com.quyunshuo.mvp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @Author: MiYan
 * @Time:   2020-01-03
 * @Class:  MainActivity
 * @Remark: View层: Activity就是View层实现View层的接口 View层主要做UI的更新
 */
class MainActivity : AppCompatActivity(), IContract.IView {
    //创建接口实例 传入View层和Model层的实例
    private val mPresenter: IContract.IPresenter = MainPresenter(this, MainModel())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    /**
     * 初始化view
     * 当点击按钮的时候通知P层开始请求数据
     * P层收到后会调用M层的方法执行具体的数据获取操作
     */
    private fun initView() = mRequestBtn.setOnClickListener { mPresenter.request() }

    /**
     * 数据请求成功P层会调用此方法
     */
    override fun requestSucceeded(str: String) {
        Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show()
        mShowTv.text = str
    }

    /**
     * 数据请求失败P层会调用此方法
     */
    override fun requestFailed() {
        Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show()
        mShowTv.text = getString(R.string.main_show_text)
    }

    /**
     * 做销毁处理 防止内存泄漏
     */
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }
}
