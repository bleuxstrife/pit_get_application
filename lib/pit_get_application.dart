import 'dart:async';

import 'package:flutter/services.dart';

class PitGetApplication {
  static const MethodChannel _channel =
      const MethodChannel('pit_get_application');

  static  Future<List<String>> get getAllApplication async {
    final List<dynamic> appPackage = await _channel.invokeMethod("getListApplication");
    return List.from(appPackage);
  }
}
