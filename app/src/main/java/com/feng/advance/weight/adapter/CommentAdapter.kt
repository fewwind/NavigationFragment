package com.feng.advance.weight.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.feng.advance.R
import com.feng.advance.bean.TestBean
import com.feng.advance.weight.WrapLinearLayout

class CommentAdapter(var ctx: Context, var data: MutableList<TestBean>, var layoutId: Int = R.layout.item_apply_history) : WrapLinearLayout.LinearAdapter<TestBean, View>(ctx, data, layoutId) {
    override fun getCount() = data.size

    override fun getItem(pos: Int) = data.get(pos)
    override fun getView(pos: Int, view: View?): View {
        var rootView: View = view ?: LayoutInflater.from(ctx).inflate(layoutId, null)
        rootView.findViewById<TextView>(R.id.name).setText(getItem(pos).id.toString())
        return rootView
    }
}