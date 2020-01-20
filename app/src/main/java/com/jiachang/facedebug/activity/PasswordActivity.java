package com.jiachang.facedebug.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jiachang.facedebug.R;
import com.jiachang.facedebug.http.ApiRetrofit;
import com.jiachang.facedebug.utils.NameUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jiachang.facedebug.utils.NameUtil.baseURL;

/**
 * @author Mickey.Ma
 * @date 2020-01-14
 * @description
 */
public class PasswordActivity extends Activity {
    private EditText oldPass,newPass;
    private String oldpass,newpass;
    private Button password_setting;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

        oldPass = findViewById(R.id.old_pass);
        newPass = findViewById(R.id.new_pass);
        oldpass = oldPass.getText().toString();
        newpass = newPass.getText().toString();
        password_setting = findViewById(R.id.password_setting);
        NameUtil.newpass = newPass.getText().toString();
        password_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRetrofit.initRetrofit(baseURL)
                        .setPassWord(oldpass,newpass)
                        .enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, Response<Response> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(PasswordActivity.this,"修改密码成功",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {

                            }
                        });
            }
        });
    }
}
