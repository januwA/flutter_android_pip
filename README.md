# flutter_android_pip

Set picture-in-picture mode on Android

```xml
<application 
  android:resizeableActivity="true" 
  android:supportsPictureInPicture="true" 
  ...
```

## Install
```yaml
dependencies:
  flutter_android_pip:
    git:
      url: https://github.com/januwA/flutter_android_pip
      ref: master
```


## use
```dart
import 'package:flutter_android_pip/flutter_android_pip.dart';


 FlutterAndroidPip.pip();

 // or
 FlutterAndroidPip.pip(aspectRatio: PipRational(1, 1));
```