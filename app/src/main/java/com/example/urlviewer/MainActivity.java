package com.example.urlviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.net.VpnService;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadthis("https://www."+query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        loadthis("https://www.google.com/");


    }
    private void loadthis(String loading_url){
                Intent intent=new Intent(MainActivity.this,HomeScreen.class);
        intent.putExtra("opend",loading_url);
        startActivity(intent);

    }

    public void tabonclick(View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView textView=view.findViewById(R.id.easytab);
                if(textView.getText().toString().toLowerCase().equals("google")){
                    loadthis("https://www.google.com/");
                }

            }
        });
    }
//    public void vpnonlick(View view){
//        String[] appPackages = {
//                "com.android.chrome",
//                "com.google.android.youtube",
//                "com.example.a.missing.app"};
//
//// Loop through the app packages in the array and confirm that the app is
//// installed before adding the app to the allowed list.
//
//        PackageManager packageManager = getPackageManager();
//        for (String appPackage: appPackages) {
//            try {
//                packageManager.getPackageInfo(appPackage, 0);
//                builder.addAllowedApplication(appPackage);
//            } catch (PackageManager.NameNotFoundException e) {
//                // The app isn't installed.
//            }
//        }
//
//// Complete the VPN interface config.
//        ParcelFileDescriptor localTunnel = builder
//                .addAddress("2001:db8::1", 64)
//                .addRoute("::", 0)
//                .establish();
//
//    }
}