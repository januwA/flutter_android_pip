import 'dart:async';

import 'package:flutter/services.dart';

const String _CHANNEL = "github.com/januwA/flutter_android_pip";

class PipRational {
  final int numerator;
  final int denominator;

  const PipRational(this.numerator, this.denominator);

  Map<String, int> toMap() {
    return {"numerator": numerator, "denominator": denominator};
  }
}

class FlutterAndroidPip {
  static const MethodChannel _channel = const MethodChannel(_CHANNEL);

  static Future pip({PipRational aspectRatio: const PipRational(9, 5)}) {
    return _channel.invokeMethod('pip', {"aspectRatio": aspectRatio.toMap()});
  }
}
