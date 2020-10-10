# flutter_ajanuw_android_pip

Set picture-in-picture mode on Android

```xml
<activity 
  android:resizeableActivity="true" 
  android:supportsPictureInPicture="true" 
  ...
```
see: https://developer.android.com/guide/topics/ui/picture-in-picture

## Install
```yaml
dependencies:
  flutter_ajanuw_android_pip:
```


## use
```dart
import 'package:flutter_ajanuw_android_pip/flutter_ajanuw_android_pip.dart';


 FlutterAndroidPip.pip();

 // or
 FlutterAndroidPip.pip(aspectRatio: PipRational(1, 1));
```