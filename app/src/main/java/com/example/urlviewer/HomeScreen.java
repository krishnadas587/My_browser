package com.example.urlviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeScreen extends AppCompatActivity {
Bundle bundle;
WebView webView;
    String url;
public static String device_name=android.os.Build.MODEL;
    public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 4.1.1;"+device_name+") AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bundle=getIntent().getExtras();
        url=bundle.getString("opend");
        System.out.println(device_name);
        setContentView(R.layout.activity_home_screen);
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new Callback());
        webView.setWebViewClient(new SSLTolerentWebViewClient());
        webView.getSettings().setUserAgentString(USER_AGENT);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);

        System.out.println(url);
        webView.loadUrl(url);
    }
    private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            System.out.println(description);
            System.out.println(errorCode);
            if(errorCode==-6&&description.equals("net::ERR_CONNECTION_CLOSED")){
                webView.loadUrl(editsslurl(url,0));
            }else{
                webView.loadUrl(editsslurl(url,1));
            }

            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

    private String editsslurl(String currenturl,int error) {
        String ulink ="";
        if (error == 0) {
            try {
                ulink=currenturl.substring(12);
                System.out.println(ulink);
                ulink="http://www."+ulink;
            }catch (Exception e){
                ulink="https://www.google.com/search?q="+currenturl;
            }
        }
        else {
            ulink="https://www.google.com/search?q="+currenturl;
        }




        return ulink;
    }


}