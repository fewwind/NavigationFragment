package com.feng.advance.weight.table

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.feng.advance.R


class GameHallBg(ctx: Context, att: AttributeSet? = null) : View(ctx, att) {
    var paintRound = Paint(Paint.ANTI_ALIAS_FLAG)
    var w = 0f
    var h = 0f
    var i = 0
    var paths = mutableListOf<Path>()
    var path = Path()
    var mode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
    var matrixRotate = Matrix()
    // 第一个四边形角度的正切是4，得出角度是76
    var degrees = floatArrayOf(38f, 90f, 142f, 218f, 270f, 322f)
    var icon: Bitmap
    lateinit var roundRectF: RectF
    var borderW = 2f
    var roundColor: Int = Color.GREEN
    var indicatorColor: Int = Color.BLUE

    init {
        setBackgroundResource(R.drawable.hall_green_bg)
        icon = BitmapFactory.decodeResource(resources, R.drawable.ic_turn_table)
        setOnClickListener { start() }
        start()
        roundColor = Color.parseColor("#15915B")
        indicatorColor= Color.parseColor("#34bd81")
    }

    fun setColor(bgRes: Int, roundC: Int, indicator: Int) {
        roundColor = roundC
        indicatorColor = indicator
        setBackgroundResource(bgRes)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        paths.clear()
        w = measuredWidth.toFloat()
        h = measuredHeight.toFloat()
        path = Path()
        path.moveTo(w / 2, h / 2)
        path.lineTo(w / 2, 0f)
        path.lineTo(w, 0f)
        path.lineTo(w, h / 4)
        path.close()
        paths.add(path)

        path = Path()
        path.moveTo(w / 2, h / 2)
        path.lineTo(w, h / 6)
        path.lineTo(w, h / 6 * 5)
        path.close()
        paths.add(path)

        path = Path()
        path.moveTo(w / 2, h / 2)
        path.lineTo(w, h / 4 * 3)
        path.lineTo(w, h)
        path.lineTo(w / 2, h)
        path.close()
        paths.add(path)

        path = Path()
        path.moveTo(w / 2, h / 2)
        path.lineTo(w / 2, h)
        path.lineTo(0f, h)
        path.lineTo(0f, h / 4 * 3)
        path.close()
        paths.add(path)

        path = Path()
        path.moveTo(w / 2, h / 2)
        path.lineTo(0f, h / 6 * 5)
        path.lineTo(0f, h / 6)
        path.close()
        paths.add(path)

        path = Path()
        path.moveTo(w / 2, h / 2)
        path.lineTo(0f, h / 4)
        path.lineTo(0f, 0f)
        path.lineTo(w / 2, 0f)
        path.close()
        paths.add(path)
        matrixRotate.postTranslate(w / 2, h / 2)
        roundRectF = RectF(borderW, borderW, w - borderW, h - borderW)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (i >= 0) {
            i = i % 6
            paintRound.color = roundColor
            val layerId = canvas.saveLayer(0f, 0f, w, h, null, Canvas.ALL_SAVE_FLAG)
            canvas.drawRoundRect(roundRectF, h / 2, h / 2, paintRound)
            paintRound.xfermode = mode
            paintRound.color = indicatorColor
            canvas.drawPath(paths[i], paintRound)
            paintRound.xfermode = null
            canvas.restoreToCount(layerId)

/*            matrixRotate.reset() 矩阵实现，注意先后顺序和旋转位移的中心点
//            matrixRotate.postRotate(30f + 60 * i, icon.width / 2f, icon.width / 2f)
//            matrixRotate.postTranslate(w / 2 - icon.width / 2, h / 2 - icon.height / 2)
            matrixRotate.postTranslate(w / 2 - icon.width / 2, h / 2 - icon.height / 2)
            matrixRotate.postRotate(30f + 60 * i, w / 2, h / 2f)
            canvas.drawBitmap(icon, matrixRotate, paintRound)
            i++*/

            val rotateId = canvas.save()
            canvas.rotate(degrees[i], w / 2, h / 2)
//            canvas.rotate(30f + 60 * i, w / 2, h / 2)
            canvas.drawBitmap(icon, w / 2 - icon.width / 2, h / 2 - icon.height / 2, paintRound)
            canvas.restoreToCount(rotateId)
            i++
        }
//        postInvalidateDelayed(666)
    }

    fun start() {
        i = 0
        ValueAnimator.ofInt(20).apply {
            duration = 3000
            interpolator = DecelerateInterpolator()
            addUpdateListener {
                i = it.animatedValue as Int
                invalidate()
            }
        }.start()
    }

    fun stop() {
        i = -1
        invalidate()
    }
}