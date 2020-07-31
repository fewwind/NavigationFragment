package com.feng.advance.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.feng.advance.R;
import com.feng.advance.util.DensityUtil;
import com.orhanobut.logger.Logger;

public class StepItemView extends View {
    int width, high, itemW, itemH, offset;
    Paint darkPaint, whitePaint, lightPaint;
    int patinW = 1;
    float x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7;//1234是斜边四边形的四个点，从左边第一个开始顺时针，5是左边竖向地2个坐标
    Bitmap bitmapNull;

    public StepItemView(Context context) {
        this(context, null);
    }

    public StepItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        darkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        darkPaint.setAntiAlias(true);
        lightPaint.setAntiAlias(true);
        whitePaint.setAntiAlias(true);
        darkPaint.setStrokeWidth(patinW);
        darkPaint.setColor(Color.parseColor("#08DF88"));
        whitePaint.setColor(Color.parseColor("#FFFFFF"));
        lightPaint.setColor(Color.parseColor("#B0F5D7"));
        initPath();
    }

    Path pathBg = new Path();
    Path pathProgress = new Path();
    Path pathLine = new Path();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, high);
    }

    void initPath() {
        width = StepUtil.getImage().x;
        high = StepUtil.getImage().y;
        x1 = 0;
        y1 = high * 0.165f;
        x2 = width * 0.336f;
        y2 = 0;
        x3 = width;
        y3 = high * 0.446f;
        x4 = width * 0.664f;
        y4 = high * 0.612f;

        x5 = 0;
        y5 = high * 0.553f;
        x6 = width * 0.664f;
        y6 = high;
        x7 = width;
        y7 = high * 0.835f;

        pathBg.moveTo(x1, y1);
        pathBg.lineTo(x2, y2);
        pathBg.lineTo(x3, y3);
        pathBg.lineTo(x7, y7);
        pathBg.lineTo(x6, y6);
        pathBg.lineTo(x5, y5);
        pathBg.lineTo(x1, y1);
//--------------------------------------------
        pathLine.moveTo(x1, y1);
        pathLine.lineTo(x4, y4);
        pathLine.lineTo(x3, y3);

        pathLine.moveTo(x4, y4);
        pathLine.lineTo(x6, y6);
//        -----------------------------------
        bitmapNull = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.step_null), width, high, true);
    }

    float progress = StepUtil.STEP_NULL;

    public void setProgress(float progressStep) {
        progress = progressStep;
        float x = progressStep;
        if (progressStep > 0 && progressStep < 1) {
            pathProgress.reset();
            pathProgress.moveTo(x1, y1);
            pathProgress.lineTo(x2, y2);
            pathProgress.lineTo(x2 + (width - x2) * x, y3 * x);
            pathProgress.lineTo(x6 * x, y3 * x + y1);
            pathProgress.lineTo((width - x2) * x, y3 * x + y1 + high * 0.388f);
            pathProgress.lineTo(x5, y5);
            Logger.i(x + "pro = " + progress);
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (progress == StepUtil.STEP_NULL) {
            canvas.drawBitmap(bitmapNull, new Rect(0, 0, bitmapNull.getWidth(), bitmapNull.getHeight()), new Rect(0, 0, bitmapNull.getWidth(), bitmapNull.getHeight()), whitePaint);
        } else if (progress >= 1) {
            darkPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(pathBg, darkPaint);
            whitePaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(pathLine, whitePaint);
        } else if (progress == 0) {
            lightPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(pathBg, lightPaint);
            whitePaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(pathLine, whitePaint);
        } else {
            lightPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(pathBg, lightPaint);
            darkPaint.setStyle(Paint.Style.FILL);
            canvas.drawPath(pathProgress, darkPaint);
            whitePaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(pathLine, whitePaint);
        }
    }

}

class StepUtil {
    public static float STEP_NULL = -1f;//默认值, 缺失的, 即将要打卡的
    public static int itemSize = 21;

    public static Point getImage() {
        Point point = new Point();
        point.y = (int) getStepHeight();
        point.x = (int) getStepWidth();
        return point;
    }

    public static float getItemStepH() {//根据宽度算出梯子竖边的真是高度，此时假设横向错位和梯子高度一致,得出总宽度是itemW+(size-1)*offset,itemH+(size-1)*offset
        // 梯子的竖边高度为10，总高度是26.3，总宽度是55，假设横向的offset是x，那么20*x +5.5x(图片矩形宽度) = rv的总宽度
        return getOffsetH();
//        return (UIUtil.width() * 0.85f) / (itemSize - 1 + 5.5f);
    }

    public static float getOffsetH() {//水平间距，是宽度的0.24，也就是宽度/水平间距 = 4.15
        return (DensityUtil.width() * 0.85f) / (itemSize - 1 + 4.15f);
    }

    public static float getOffsetV() {//垂直间距
        return getOffsetH() * 0.85f;
    }

    public static float getStepWidth() {//水平间距，是宽度的0.24，也就是宽度/水平间距 = 4.17
        return getOffsetH() * 4.15f;
    }

    public static float getStepHeight() {//水平间距，是宽度的0.24，也就是宽度/水平间距 = 4.17
        return getStepWidth() * 0.424f;
    }
}
