package com.chaozhuo.parentmanager.util;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by hlbt on 18-3-29.
 */

public class PermissionUtils {
    private static final String TAG = "PermissionUtils";

    //检测用户是否对本app开启了“Apps with usage access”权限
    public static boolean checkUsageStatsPermission(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return true;
        }
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(context.getPackageName(), 0);
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            return AppOpsManager.MODE_ALLOWED == appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, info.uid, info.packageName);
        } catch (Exception e) {
            return false;
        }
    }

    public static void requestUsageStatsPermission(Activity activity) {
        try {
            activity.startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    public static boolean checkDrawOverlaysPermission(Context context) {
        if (OSUtils.get().isMiui() && Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            return checkOp(context, 24);
        }
        if (OSUtils.get().isFlyme() && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return checkOp(context, 24);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        return Settings.canDrawOverlays(context);
    }

    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;

        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            try {
                Class managerClass = manager.getClass();
                Method method = managerClass.getDeclaredMethod("checkOp", int.class, int.class, String.class);
                int isAllowNum = (Integer) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());

                if (AppOpsManager.MODE_ALLOWED == isAllowNum) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void requestDrawOverlaysPermission(Activity activity) {
        if (checkDrawOverlaysPermission(activity)) return;
        boolean success = false;
        // 所有的Code我们都传-1，因为有的设备上startActivityForResult不好用，所以我们在onResume再次Check
        if (OSUtils.get().isMiui()) {
            success = applyMiuiPermission(activity, -1);
        } else if (OSUtils.get().isFlyme()) {
            success = applyMeizuPermission(activity, -1);
        }
        if (success)
            return;
        Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        i.setData(Uri.parse(
                String.format("package:%s", activity.getPackageName())));
        try {
            activity.startActivityForResult(i, -1);
        } catch (Exception e) {
        }
    }

    /**
     * 去魅族权限申请页面
     */
    public static boolean applyMeizuPermission(Activity context, int code) {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
            intent.putExtra("packageName", context.getPackageName());
            context.startActivityForResult(intent, code);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean applyMiuiPermission(Activity context, int code) {
        int versionCode = OSUtils.get().getMiuiVersion();
        if (versionCode == 5) {
            goToMiuiPermissionActivity_V5(context, code);
        } else if (versionCode == 6) {
            goToMiuiPermissionActivity_V6(context, code);
        } else if (versionCode == 7) {
            goToMiuiPermissionActivity_V7(context, code);
        } else if (versionCode == 8) {
            goToMiuiPermissionActivity_V8(context, code);
        } else {
            // 如果不匹配上述的版本，从5开始一个一个尝试
            if (goToMiuiPermissionActivity_V8(context, code)) {
                return true;
            }
            if (goToMiuiPermissionActivity_V7(context, code)) {
                return true;
            }
            if (goToMiuiPermissionActivity_V6(context, code)) {
                return true;
            }
            if (goToMiuiPermissionActivity_V5(context, code)) {
                return true;
            }

            return false;
        }
        return true;
    }

    /**
     * 小米 V5 版本 ROM权限申请
     */
    public static boolean goToMiuiPermissionActivity_V5(Activity context, int code) {
        Intent intent = null;
        String packageName = context.getPackageName();
        intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", packageName, null);
        intent.setData(uri);
        if (isIntentAvailable(intent, context)) {
            context.startActivityForResult(intent, code);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 小米 V6 版本 ROM权限申请
     */
    public static boolean goToMiuiPermissionActivity_V6(Activity context, int code) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());

        if (isIntentAvailable(intent, context)) {
            context.startActivityForResult(intent, code);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 小米 V7 版本 ROM权限申请
     */
    public static boolean goToMiuiPermissionActivity_V7(Activity context, int code) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());

        if (isIntentAvailable(intent, context)) {
            context.startActivityForResult(intent, code);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 小米 V8 版本 ROM权限申请
     */
    public static boolean goToMiuiPermissionActivity_V8(Activity context, int code) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());

        if (isIntentAvailable(intent, context)) {
            context.startActivityForResult(intent, code);
            return true;
        } else {
            return false;
        }
    }

    private static boolean isIntentAvailable(Intent intent, Context context) {
        if (intent == null) {
            return false;
        }
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }
}
