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

  List<String> appPackage = [];
  @override
  void initState() {
    super.initState();
    initPlatformState();
  }


  Future<void> initPlatformState() async {
    List<String> appPackage = [];

    try {
      appPackage = await PitGetApplication.getAllApplication;
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
        body: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Text("List App Package : "),
              _buildItemAppPackage()
            ],
          ),
        ),
      ),
    );
  }

  _buildItemAppPackage(){
    List<Widget> item = [];

    appPackage.forEach((app){
      item.add(
          Container(
            margin: EdgeInsets.only(left: 16.0, right: 16.0, bottom: 16.0),
            child: Text(app),
          ));
    });
    return Column(crossAxisAlignment: CrossAxisAlignment.start, children: item,);

  }
}


```