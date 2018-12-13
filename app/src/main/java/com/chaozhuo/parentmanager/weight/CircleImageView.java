package com.chaozhuo.parentmanager.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.chaozhuo.parentmanager.R;

/**
 * Created by fewwind on 18-12-13.
 */

public class CircleImageView extends ImageView {
    private float width;
    private float height;
    private float radio;
    private Paint paint;
    private Paint paintLine;
    private Matrix matrix;
    private int lineW = 12;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setAntiAlias(true);
        matrix = new Matrix();
        paintLine.setColor(getResources().getColor(R.color.colorPrimary));
        paintLine.setStrokeWidth(lineW);
        paintLine.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
//        width -= lineW * 2;
//        height -= lineW * 2;
        radio = Math.min(width, height) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null || !(getDrawable() instanceof BitmapDrawable)) {
            super.onDraw(canvas);
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        int bitmapW = Math.min(bitmap.getWidth(), bitmap.getHeight());
        int x, y;
        x = Math.abs(bitmap.getWidth() - bitmapW) / 2;
        y = Math.abs(bitmap.getHeight() - bitmapW) / 2;
        bitmap = Bitmap.createBitmap(bitmap, x, y, bitmapW, bitmapW);
        float scale = Math.min(width / bitmap.getWidth(), height / bitmap.getHeight());
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        matrix.setScale(scale, scale);
        float a = Math.abs(width - radio * 2) / 2;
        float b = Math.abs(height - radio * 2) / 2;
        matrix.postTranslate(a, b);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);
        canvas.drawCircle(width / 2, height / 2, radio, paint);
        canvas.drawCircle(width / 2, height / 2, radio - lineW / 2, paintLine);
    }
}
