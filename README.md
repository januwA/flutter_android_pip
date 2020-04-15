# flutter_ajanuw_android_pip

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
  flutter_ajanuw_android_pip:
```


## use
```dart
import 'package:flutter_ajanuw_android_pip/flutter_ajanuw_android_pip.dart';


 FlutterAndroidPip.pip();

 // or
 FlutterAndroidPip.pip(aspectRatio: PipRational(1, 1));
```