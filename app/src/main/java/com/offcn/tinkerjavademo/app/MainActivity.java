package com.offcn.tinkerjavademo.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.offcn.tinkerjavademo.R;
import com.tencent.tinker.lib.tinker.Tinker;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.tv));
        String absolutePath = Tinker.with(this).getPatchDirectory().getAbsolutePath();
        tv.setText("修复后"+absolutePath);
        Toast.makeText(this, absolutePath, Toast.LENGTH_SHORT).show();
        tv.append("修复了啥,替换了版本号");
    }
}
