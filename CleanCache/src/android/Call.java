package io.jijiwuming.github;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import org.apache.cordova.*;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by 10202 on 2016/7/24 0024.
 */
public class Call extends CordovaPlugin {
    public static final String ACTION = "call";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("CleanCache")) {
            try {
                //下面两句最关键，利用intent启动新的Activity
                Intent intent = new Intent().setClass(cordova.getActivity(), Class.forName(args.getString(0)));
                this.cordova.startActivityForResult(this, intent, 1);
                //下面三句为cordova插件回调页面的逻辑代码
                PluginResult mPlugin = new PluginResult(PluginResult.Status.NO_RESULT);
                mPlugin.setKeepCallback(true);
                callbackContext.sendPluginResult(mPlugin);
                callbackContext.success("success");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
    //onActivityResult为第二个Activity执行完后的回调接收方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        switch (resultCode) { //resultCode为回传的标记，我在第二个Activity中回传的是RESULT_OK
            case Activity.RESULT_OK:
                Bundle b=intent.getExtras();  //data为第二个Activity中回传的Intent
                String str=b.getString("change01");//str即为回传的值
                break;
            default:
                break;
        }
    }

}

