package com.example.urlviewer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyVpnService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
