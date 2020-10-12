package com.feng.advance.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.feng.advance.R
import com.orhanobut.logger.Logger
import java.util.jar.Attributes

class WrapLinearLayout2<T>(ctx: Context, att: AttributeSet? = null) : LinearLayout(ctx, att) {
    lateinit var mAdapter: WrapLinearLayout2.LinearAdapter<T, View>
    var onclick: ((pos: Int, t: T, id: Int) -> Unit)? = null
    var mClickIds: IntArray? = null
    companion object{
        const val MAX = 3
    }
    init {
        orientation = LinearLayout.VERTICAL
        for (i in 1..MAX) {
            addView(LayoutInflater.from(ctx).inflate(R.layout.item_apply_history, null))
        }
    }

    fun setAdapter(adapter: LinearAdapter<T, View>, ids: IntArray? = null, listener: ((pos: Int, t: T, id: Int) -> Unit)? = null) {
        mAdapter = adapter
        mClickIds = ids
        onclick = listener
        repeat(childCount) {
            var itemView = getChildAt(it)
            if (it >= mAdapter.getCount()) {
                itemView.visibility = View.GONE
            } else {
                itemView.visibility = View.VISIBLE
                mAdapter.getView(it, itemView)
                if (itemView.tag == null) {
                    mClickIds?.apply {
                        itemView.tag = true
                        forEach { id ->
                            itemView.findViewById<View>(id).setOnClickListener { _ ->
                                onclick?.invoke(it, mAdapter.getItem(it), id)
                                Logger.w(mAdapter.getItem(it).toString())
                            }
                        }
                    }
                }
            }

        }
    }


    abstract class LinearAdapter<T, V : View>(ctx: Context, data: MutableList<T>, layoutId: Int) {
        abstract fun getCount(): Int
        abstract fun getView(pos: Int, view: V?): V
        abstract fun getItem(pos: Int): T
    }
}