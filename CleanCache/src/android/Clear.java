package io.jijiwuming.github;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.*;

import java.io.File;

public class Clear extends CordovaActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("message","cachecleaned");
        clearcache();
        finish();
    }
    //清除缓存
    private void clearcache(){
        File cachefile = this.getCacheDir();
        delFile(0,cachefile);
        appView=super.makeWebView();
        appView.clearCache();
    }
    //递归删除文件
    private void delFile(int i,File file){
        if (file != null && file.exists() && file.isDirectory()) {
            Log.d("删除文件--"+i,file.getAbsolutePath());
            for (File item : file.listFiles()) {
                Log.d("--删除文件",file.getAbsolutePath());
                if(item.isDirectory()){delFile(i+1,item);}
                else{
                    item.delete();}
            }
            file.delete();
        }
    }

}
