package com.shangjy.plugindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById = findViewById(R.id.button);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PluginManager.getInstance(MainActivity.this).getLoadedPlugin("com.shangjy.plugin") == null) {
                    Toast.makeText(MainActivity.this, "plugin [com.shangjy.plugin] not loaded", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClassName("com.shangjy.plugin", "com.shangjy.plugin.PluginActivity");
                startActivity(intent);
            }
        });
        loadPlugin(this, "plugin-release.apk");
    }


    private void loadPlugin(Context base, String apkname) {

        try {
            PluginManager pluginManager = PluginManager.getInstance(base);
            File file = new File(base.getFilesDir(), apkname);
            java.io.InputStream inputStream = base.getAssets().open(apkname, 2);
            java.io.FileOutputStream outputStream = new java.io.FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;

            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }

            outputStream.close();
            inputStream.close();
            pluginManager.loadPlugin(file);
            Log.i(TAG, "Loaded plugin from assets: " + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}