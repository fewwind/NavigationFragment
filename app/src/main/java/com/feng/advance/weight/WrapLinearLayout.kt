package com.feng.advance.weight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.orhanobut.logger.Logger
import java.util.jar.Attributes

class WrapLinearLayout<T>(ctx: Context, att: AttributeSet? = null) : LinearLayout(ctx, att) {
    lateinit var mAdapter: LinearAdapter<T, View>
    var onclick: ((pos: Int, t: T, id: Int) -> Unit)? = null
    var mClickIds: IntArray? = null

    init {
        orientation = LinearLayout.VERTICAL
    }

    /**
     * setAdapter(CommentAdapter(context, item.replys!!), intArrayOf(R.id.item_sub), onclick)
     */
    fun setAdapter(adapter: LinearAdapter<T, View>,ids: IntArray? = null, listener:( (pos: Int, t: T, id: Int) -> Unit)? = null) {
        mAdapter = adapter
        mClickIds = ids
        onclick = listener
        if (mAdapter.getCount() < childCount) removeViewsInLayout(mAdapter.getCount(), childCount - mAdapter.getCount())
        repeat(mAdapter.getCount()) {
            if (getChildAt(it) != null) {
                mAdapter.getView(it, getChildAt(it))
            } else {
                var creatView = mAdapter.getView(it, null)
                addView(creatView)
                mClickIds?.apply {
                    forEach { id ->
                        creatView.findViewById<View>(id).setOnClickListener { _ ->
                            //注意此处必须使用mAdapter，否则数据错乱
                            onclick?.invoke(it, mAdapter.getItem(it), id)
                            Logger.w(mAdapter.getItem(it).toString())
                        }
                    }
                }
            }
        }
        requestLayout()
    }


    abstract class LinearAdapter<T, V : View>(ctx: Context, data: MutableList<T>, layoutId: Int) {
        abstract fun getCount(): Int
        abstract fun getView(pos: Int, view: V?): V
        abstract fun getItem(pos: Int): T
    }
}