package com.example.xjlch.iotcar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity{
    private ProgressBar mLoadingProgress;
    private WebView webView;
    final String ip = "192.168.1.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview1);
        mLoadingProgress = findViewById(R.id.progressBarLoading);
        mLoadingProgress.setMax(100);

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setSupportZoom(true); //设置可以支持缩放
        webView.getSettings().setDisplayZoomControls(false);
        //webView.getSettings().setDefaultZoom(ZoomDensity.FAR);

        Button btn =  findViewById(R.id.connectbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获得EditText对象
                EditText et = findViewById(R.id.edit);
                // 获得地址栏输入的网址
                //String url = "http://" + et.getText().toString()+":8080/?action=stream";
                String url = "http://" + ip +":8080/?action=stream";
                webView.loadUrl(url);
            }
        });

        Button pbutton = findViewById(R.id.photobutton);
        pbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            EditText et = findViewById(R.id.edit);
                            HttpClient httpClient=new DefaultHttpClient();
                            String s = "http://"+ ip+":8899/car?a=5";
                            HttpGet httpGet = new HttpGet(s);
                            HttpResponse httpResponse=httpClient.execute(httpGet);

                        }catch(Exception e){

                        }finally {

                        }

                    }
                }).start();
            }
        });

        Button sbutton = findViewById(R.id.stopbutton);
        sbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            EditText et = findViewById(R.id.edit);
                            HttpClient httpClient=new DefaultHttpClient();
                            String s = "http://"+ ip+":8899/car?a=0";
                            HttpGet httpGet = new HttpGet(s);
                            HttpResponse httpResponse=httpClient.execute(httpGet);

                        }catch(Exception e){

                        }finally {

                        }

                    }
                }).start();
            }
        });

        Button fbutton = findViewById(R.id.forwardbutton);
        fbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            EditText et = findViewById(R.id.edit);

                            HttpClient httpClient=new DefaultHttpClient();
                            String s = "http://"+ ip+":8899/car?a=1";
                            HttpGet httpGet = new HttpGet(s);
                            HttpResponse httpResponse=httpClient.execute(httpGet);
                        }catch(Exception e){

                        }finally {

                        }

                    }
                }).start();
            }
        });

        Button bbutton = findViewById(R.id.backbutton);
        bbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            EditText et = findViewById(R.id.edit);

                            HttpClient httpClient=new DefaultHttpClient();
                            String s = "http://"+ ip+":8899/car?a=2";
                            HttpGet httpGet = new HttpGet(s);
                            HttpResponse httpResponse=httpClient.execute(httpGet);
                        }catch(Exception e){

                        }finally {

                        }

                    }
                }).start();
            }
        });

        Button lbutton = findViewById(R.id.leftbutton);
        lbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            EditText et = findViewById(R.id.edit);

                            HttpClient httpClient=new DefaultHttpClient();
                            String s = "http://"+ ip+":8899/car?a=3";
                            HttpGet httpGet = new HttpGet(s);
                            HttpResponse httpResponse=httpClient.execute(httpGet);
                        }catch(Exception e){

                        }finally {

                        }

                    }
                }).start();
            }
        });

        Button rbutton = findViewById(R.id.rightbutton);
        rbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            EditText et = findViewById(R.id.edit);

                            HttpClient httpClient=new DefaultHttpClient();
                            String s = "http://"+ ip+":8899/car?a=4";
                            HttpGet httpGet = new HttpGet(s);
                            HttpResponse httpResponse=httpClient.execute(httpGet);
                        }catch(Exception e){

                        }finally {

                        }

                    }
                }).start();
            }
        });





        // 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //设置加载进度条
                view.setWebChromeClient(new WebChromeClientProgress());
                return true;
            }

        });
    }

    private class WebChromeClientProgress extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int progress) {
            if (mLoadingProgress != null) {
                mLoadingProgress.setProgress(progress);
                if (progress == 100) mLoadingProgress.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, progress);
        }
    }

    /***
     ** 按键响应，在WebView中查看网页时，检查是否有可以前进的历史记录。
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack())
        {

            // 返回键退回
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
     **/
}

