package com.chaozhuo.parentmanager.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;


import java.lang.reflect.Method;

/**
 * Created by 247321453 on 2016/7/17.
 */
public class OSUtils {
    //EMUI
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";

    //MIUI
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    //FLYME
    private static final String KEY_MEIZU_REGION_ENABLE = "ro.meizu.region.enable";

    //SAMSUNG
    private static final String KEY_RO_BUILD_FINGERPRINT = "ro.build.fingerprint";

    //Oppo
    private static final String KEY_OPPO_VERSION_CODE = "ro.oppo.version";
    private static final String KEY_OPPO_THEME_VERSION_CODE = "ro.oppo.theme.version";

    //Vivo
    private static final String KEY_VIVO_VERSION_CODE = "ro.vivo.os.version";
    private static final String KEY_VIVO_ROM_NAME = "ro.vivo.rom";

    //Smartisan
    private static final String KEY_SMARTISAN_VERSION_CODE = "ro.smartisan.version";
    private static final String KEY_SMARTISAN_TAG = "ro.smartisan.tag";

    private static final OSUtils sOSUtils = new OSUtils();
    private static Boolean mIsOnHwExternalDisplay = null;
    private boolean emui;
    private boolean miui;
    private boolean samsung;
    private boolean flyme;
    private boolean vivo;
    private boolean oppo;
    private boolean smartisan;
    private int miuiVersion = -1;

    private OSUtils() {
        String p1 = SystemPropertiesCompat.get(KEY_EMUI_VERSION_CODE, "");
        String p3 = SystemPropertiesCompat.get(KEY_MIUI_VERSION_NAME, "");
        String p4 = SystemPropertiesCompat.get(KEY_MIUI_INTERNAL_STORAGE, "");
        String p5 = SystemPropertiesCompat.get(KEY_RO_BUILD_FINGERPRINT, "");
        String p6 = SystemPropertiesCompat.get(KEY_OPPO_VERSION_CODE, "");
        String p7 = SystemPropertiesCompat.get(KEY_OPPO_THEME_VERSION_CODE, "");
        String p8 = SystemPropertiesCompat.get(KEY_VIVO_VERSION_CODE, "");
        String p9 = SystemPropertiesCompat.get(KEY_VIVO_ROM_NAME, "");
        String p10 = SystemPropertiesCompat.get(KEY_SMARTISAN_VERSION_CODE, "");
        String p11 = SystemPropertiesCompat.get(KEY_SMARTISAN_TAG, "");
        String p12 = SystemPropertiesCompat.get(KEY_MEIZU_REGION_ENABLE, "");

        emui = !TextUtils.isEmpty(p1);
        miui = !TextUtils.isEmpty(p3) || !TextUtils.isEmpty(p4);
        try {
            if (!TextUtils.isEmpty(p3)) {
                miuiVersion = Integer.parseInt(p3.substring(1));
            }
        } catch (Exception e) {}
        samsung = !TextUtils.isEmpty(p5) && p5.startsWith("samsung");
        vivo = !TextUtils.isEmpty(p8) || !TextUtils.isEmpty(p9);
        oppo = !TextUtils.isEmpty(p6) || !TextUtils.isEmpty(p7);
        smartisan = !TextUtils.isEmpty(p10) || !TextUtils.isEmpty(p11);
        flyme = hasFlyme()  || !TextUtils.isEmpty(p12);
    }

    public static OSUtils get() {
        return sOSUtils;
    }

    public int getMiuiVersion() {
        return miuiVersion;
    }

    public boolean isEmui() {
        return emui;
    }

    public boolean isMiui() {
        return miui;
    }

    public boolean isSamsung() {
        return samsung;
    }

    public boolean isOppo() {
        return oppo;
    }

    public boolean isVivo() {
        return vivo;
    }

    public boolean isSmartisan() {
        return smartisan;
    }

    public boolean isFlyme() {
        return flyme;
    }

    public boolean isOnHwExternalDisplay(Context context) {
        if (mIsOnHwExternalDisplay != null) {
            return mIsOnHwExternalDisplay;
        }
        try {
            String emuiVersion = SystemPropertiesCompat.get("ro.build.version.emui", null);
            if (!TextUtils.isEmpty(emuiVersion)) {
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                int displayId = wm.getDefaultDisplay().getDisplayId();
                mIsOnHwExternalDisplay = new Boolean(displayId != Display.DEFAULT_DISPLAY && displayId != Display.INVALID_DISPLAY);
            }
        } catch (Exception e) {
        } finally {
            if (mIsOnHwExternalDisplay == null) {
                mIsOnHwExternalDisplay = new Boolean(false);
            }
        }
        return mIsOnHwExternalDisplay;
    }

    private boolean hasFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }
}
