package com.example.luck.util;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.luck.MainActivity;
import com.example.luck.R;
import com.example.luck.loginActivity;
import com.example.luck.registerActivity;
import com.example.luck.viewer.constellation.home.GuideActivity;
import com.example.luck.viewer.constellation.home.WelcomeActivity;

public class willucome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_willucome);

        Button button=findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(willucome.this, loginActivity.class);
                startActivity(intent);

            }
        });
        Button button1=findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(willucome.this, WelcomeActivity.class);
                    startActivity(intent);
            }
        });
    }
}