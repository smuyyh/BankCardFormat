package com.yuyh.bankcardformat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.library.BandCardEditText;

public class MainActivity extends AppCompatActivity {

    BandCardEditText editText;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        editText = (BandCardEditText) findViewById(R.id.et);
        editText.setBankCardListener(new BandCardEditText.BankCardListener() {
            @Override
            public void success(String name) {
                tv.setText("所属银行：" + name);
            }

            @Override
            public void failure() {
                tv.setText("所属银行：");
            }
        });
    }
}
