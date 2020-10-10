package com.ajanuw.flutter_android_pip


import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.Rational
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


const val CHANNEL: String = "github.com/januwA/flutter_android_pip"

/** FlutterAndroidPipPlugin */
public class FlutterAndroidPipPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    private lateinit var activity: Activity
    private lateinit var applicationContext: Context
    private lateinit var methodChannel: MethodChannel

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        onAttachedToEngine(binding.getApplicationContext(), binding.getBinaryMessenger());
    }

    // 旧的构建方法
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            FlutterAndroidPipPlugin().onAttachedToEngine(registrar.context(), registrar.messenger())
        }
    }

    private fun onAttachedToEngine(context: Context, messenger: BinaryMessenger) {
        applicationContext = context
        methodChannel = MethodChannel(messenger, CHANNEL)
        methodChannel.setMethodCallHandler(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "pip") {
            if (Build.VERSION.SDK_INT > 24) {
                val mParams = PictureInPictureParams.Builder()

                // See Also:
                // https://developer.android.com/guide/topics/ui/picture-in-picture
                // https://developer.android.com/reference/kotlin/android/app/PictureInPictureParams.Builder
                // https://www.programcreek.com/java-api-examples/index.php?api=android.app.PictureInPictureParams
                val rationalMap: Map<String, Int>? = call.argument("aspectRatio");
                if (rationalMap != null) {
                    val numerator = rationalMap.get("numerator")!!.toInt()
                    val denominator = rationalMap.get("denominator")!!.toInt()
                    val rational = Rational(numerator, denominator)
                    mParams.setAspectRatio(rational)
                }

                activity?.enterPictureInPictureMode(mParams.build())
                result.success(null)
            }
        } else {
            result.notImplemented()
        }
    }


    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel.setMethodCallHandler(null);
    }

    override fun onDetachedFromActivity() {

    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }
}
