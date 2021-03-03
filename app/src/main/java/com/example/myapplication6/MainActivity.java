package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyReceiver rec;
    IntentFilter ifilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec=new MyReceiver();
        ifilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(rec,ifilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(rec);
    }
}
class MyReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conng= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni= conng.getActiveNetworkInfo();
        if(ni.getType()==ConnectivityManager.TYPE_WIFI)
        {
            Toast.makeText(context,"WIFI active",Toast.LENGTH_LONG).show();
        }
        else if(ni.getType()==ConnectivityManager.TYPE_MOBILE)
            Toast.makeText(context,"Mobile active",Toast.LENGTH_LONG).show();

    }
}