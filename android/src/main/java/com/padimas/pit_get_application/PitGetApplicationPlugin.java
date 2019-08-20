package com.padimas.pit_get_application;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * PitGetApplicationPlugin
 */
public class PitGetApplicationPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    Context context;

    public PitGetApplicationPlugin(Registrar registrar) {
        context = registrar.context();
    }

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "pit_get_application");
        channel.setMethodCallHandler(new PitGetApplicationPlugin(registrar));
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("getListApplication")) {
            try {
                List<String> listApp = getAllApplication();
                result.success(listApp);
                Log.d("Success", "getListApplication:" + listApp);
            } catch (Exception e) {
                e.printStackTrace();
                result.error("Error", "Error getListApplication : " + e.getLocalizedMessage(), e);
            }
        } else {
            result.notImplemented();
        }
    }

    private List<String> getAllApplication() throws Exception {
        List<String> list = new ArrayList<>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities(mainIntent, 0);
        for (int i = 0; i < pkgAppsList.size(); i++) {
            list.add(pkgAppsList.get(i).activityInfo.packageName);
        }
        return list;
    }
}
