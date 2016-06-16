package com.chiemy.demo.rxjavabasicprinciple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view){
        PersonsHelper helper = new PersonsHelper();
        AsyncJob<Boolean> asyncJob = helper.addFriends("1");
        //AsyncJob<Boolean> asyncJob = helper.addFriends("3");
        asyncJob.start(new Callback<Boolean>() {
            @Override
            public void onResult(Boolean result) {
                Toast.makeText(MainActivity.this, result ? "成功" : "失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_LONG).show();
            }
        });
    }
}
