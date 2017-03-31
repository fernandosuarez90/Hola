package com.example.suarezpc.hola;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class acercade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
    }

    public void principal(View view) {
        finish();
    }

}



