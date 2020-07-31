package com.feng.advance.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;

/**
 * Created by fewwind on 19-5-28.
 */

public class ImageUtil {
    /**
     * * @param scale         The scale(0...1).
     * * @param radius        The radius(0...25).
     */
    public static Bitmap rsBlur(Context context, Bitmap source, int radius, float scale) {
        Logger.i("origin size:" + source.getWidth() + "*" + source.getHeight());
        int width = Math.round(source.getWidth() * scale);
        int height = Math.round(source.getHeight() * scale);

        Bitmap inputBmp = Bitmap.createScaledBitmap(source, width, height, false);

        RenderScript renderScript = RenderScript.create(context);

        Logger.i("scale size:" + inputBmp.getWidth() + "*" + inputBmp.getHeight());

        // Allocate memory for Renderscript to work with

        final Allocation input = Allocation.createFromBitmap(renderScript, inputBmp);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setInput(input);

        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);

        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);


        renderScript.destroy();
        return inputBmp;
    }

    public static String drawable2Base64(Drawable drawable) {
        if (drawable == null) return null;
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        //将byte[]转为base64
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static Bitmap base64ToBitmap(String base64) {

        byte[] decode = Base64.decode(base64, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    // 按比例居中缩放图片
    public static Bitmap scaleBitmap(Bitmap bitmap, float w, float h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float newRatio = w / h;
        float oldRatio = width / height;
        Bitmap newbmp = null;
        if (newRatio > oldRatio) {
            height = (int) (width / newRatio);
        } else if (newRatio < oldRatio) {
            width = (int) (height * newRatio);
        } else {
            return bitmap;
        }
        newbmp = Bitmap.createBitmap(bitmap, Math.abs(bitmap.getWidth() - width) / 2, Math.abs(bitmap.getHeight() - height) / 2, width, height);
        return newbmp;
    }
}
