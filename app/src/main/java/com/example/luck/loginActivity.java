package com.example.luck;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class loginActivity extends AppCompatActivity {

    private static final String TAG = "tag";
    private Button btnLogin;
    private EditText etAccount,etPassword;
    private Button cb_agree;
QQHelper qqHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        qqHelper=new QQHelper(loginActivity.this);

        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        cb_agree=findViewById(R.id.cb_agree);

        Map<String,String> map = SaveQQ.getUserInfo(loginActivity.this);
        String account = map.get("account");
        String password = map.get("password");
        etAccount.setText(account);
        etPassword.setText(password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();

                Log.i("debug","登录按钮");
                if(TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                    Toast.makeText(loginActivity.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                }
                int f = QQperate.query(loginActivity.this, account, password);
                if(f == 1) {
                    Intent intent = new Intent(loginActivity.this , MainActivity.class);
                    SaveQQ.SaveUserInfo(loginActivity.this, account, password);
                    Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(loginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cb_agree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });

    }
}