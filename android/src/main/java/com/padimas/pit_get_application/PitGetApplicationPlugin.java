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

/** PitGetApplicationPlugin */
public class PitGetApplicationPlugin implements MethodCallHandler {
  /** Plugin registration. */
  Registrar registrar;
  public PitGetApplicationPlugin(Registrar registrar){
    this.registrar = registrar;
  }
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "pit_get_application");
    channel.setMethodCallHandler(new PitGetApplicationPlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getListApplication")) {
      result.success(getApplicationList());
    } else {
      result.notImplemented();
    }
  }

  private List<Map<String, Object>> getApplicationList(){
    List<Map<String, Object>> list = new ArrayList<>();
    Context context = registrar.context();

    try {
      Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
      mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
      List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities( mainIntent, 0);
      for(int i=0;i<pkgAppsList.size();i++){
        Map<String, Object> maps = new HashMap<>();
        maps.put("appPackage", pkgAppsList.get(i).activityInfo.toString());
        list.add(maps);
      }
      Log.d("Success", "getListApplication:" + list);
    } catch (Exception e){
      Log.d("Error", "getListApplication:" + e.getLocalizedMessage());
    }

    return list;
  }
}
