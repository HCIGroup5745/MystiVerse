package com.example.luck;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity{

    private Button btnRegister;
    private EditText etAccount,etPass,etPassConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAccount = findViewById(R.id.et_account);
        etPass = findViewById(R.id.et_password);
        etPassConfirm = findViewById(R.id.et_password_confirm);
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                String password = etPass.getText().toString();
                String passConfirm = etPassConfirm.getText().toString();

                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(registerActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();

                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(registerActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!TextUtils.equals(password,passConfirm)) {
                    Toast.makeText(registerActivity.this, "密码不一致", Toast.LENGTH_LONG).show();
                    return;
                }
                int f=QQperate.insert(registerActivity.this,account,password);
                if(f==1){
                    SaveQQ.SaveUserInfo(registerActivity.this,account,password);
                    Toast.makeText(registerActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(registerActivity.this,loginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(registerActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}