package com.feng.advance.mvp;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by fewwind on 18-12-24.
 */

@SuppressWarnings("unchecked")
public abstract class OnResultCallBack<T> {
    protected boolean success;

    private T transform(String response) {
        T dataResponse = null;
        try {
            JSONObject jsonObject = new JSONObject(response);
            String dataStr = jsonObject.opt("").toString();

            if (dataStr.charAt(0) == 123) {
                //获取泛型类型
                Class<T> classOfT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                if (classOfT == String.class) {
                    dataResponse = (T) dataStr;
                } else {
                    //对象
//                    dataResponse = (new Gson()).fromJson(dataStr, classOfT);
                }
            } else if (dataStr.charAt(0) == 91) {
                //数组
                Type collectionType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//                dataResponse = new Gson().fromJson(dataStr, collectionType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataResponse;

    }


    public abstract void onSuccess(boolean success, int code, String msg, Object tag, T response);

    public abstract void onFailure(Object tag, Exception e);

    public abstract void onCompleted();
}
