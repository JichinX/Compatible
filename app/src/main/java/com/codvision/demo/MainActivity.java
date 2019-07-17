package com.codvision.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codvision.compatible.util.NetUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetUtil.isVpnUsed(this);
    }
}
