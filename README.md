# pit_get_application

A plugin for flutter that functions to get list of installed application package from the mobile devices (Android Only)

## Installation

First, add `pit_get_application` as a [dependency in your pubspec.yaml file](https://flutter.io/platform-plugins/).

```
pit_get_application: ^0.1.0+3
```

## Example for Get List Application Package
```
     List<dynamic> appPackage = await PitGetApplication.getListApplication;
```
