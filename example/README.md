```
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:pit_get_application/pit_get_application.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  List<dynamic> appPackage = [];
  @override
  void initState() {
    super.initState();
    initPlatformState();
  }


  Future<void> initPlatformState() async {
    List<dynamic> appPackage = [];

    try {
      appPackage = await PitGetApplication.getListApplication;
    } on PlatformException {
      print("error");
    }

    if (!mounted) return;
    setState(() {
      this.appPackage = appPackage;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('List App Package :\n$appPackage'),
        ),
      ),
    );
  }
}

```