package com.jiachang.facedebug.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jiachang.facedebug.R;
import com.jiachang.facedebug.http.ApiRetrofit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jiachang.facedebug.utils.NameUtil.baseURL;
import static com.jiachang.facedebug.utils.NameUtil.newpass;

/**
 * @author Mickey.Ma
 * @date 2020-01-13
 * @description
 */
public class SettingDeviceActivity extends Activity implements View.OnClickListener {
    private Button connect,restart,reset,setip;
    private EditText edit_device_ip,edit_subnet_mask,edit_gateway,edit_dns;
    private Spinner is_dhcp_mod;
    private String device_ip_value,subnet_mask_value,gateway_value,dns_value;
    private String[] dhcp_mod_value;
    private int is_dhcp_mod_value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_setting);
        initView();
    }

    private void initView(){
        edit_device_ip = findViewById(R.id.edit_device_ip);
        edit_subnet_mask = findViewById(R.id.edit_subnet_mask);
        edit_gateway = findViewById(R.id.edit_gateway);
        edit_dns = findViewById(R.id.edit_dns);
        is_dhcp_mod = findViewById(R.id.is_dhcp_mod);
        device_ip_value = edit_device_ip.getText().toString();
        subnet_mask_value = edit_subnet_mask.getText().toString();
        gateway_value = edit_gateway.getText().toString();
        dns_value = edit_dns.getText().toString();
        connect = findViewById(R.id.connect);
        restart = findViewById(R.id.restart);
        reset = findViewById(R.id.reset);
        setip = findViewById(R.id.setip);
        connect.setOnClickListener(this);
        restart.setOnClickListener(this);
        reset.setOnClickListener(this);
        setip.setOnClickListener(this);
        is_dhcp_mod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dhcp_mod_value = getResources().getStringArray(R.array.is_dhcp_mod);
                if (dhcp_mod_value[position].equals(R.string.dhcp_model)){
                    is_dhcp_mod_value = 1;
                    Toast.makeText(SettingDeviceActivity.this, "你点击的是:"+is_dhcp_mod_value, Toast.LENGTH_LONG).show();
                }else if (dhcp_mod_value[position].equals(R.string.manual)){
                    is_dhcp_mod_value = 2;
                    Toast.makeText(SettingDeviceActivity.this, "你点击的是:"+is_dhcp_mod_value, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connect:
                setNetInfo();
                break;
            case R.id.restart:
                if (newpass.isEmpty()) {
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
                }
                ApiRetrofit.initRetrofit(baseURL)
                        .restartDevice(newpass)
                        .enqueue(new Callback<Response<String>>() {
                            @Override
                            public void onResponse(Call<Response<String>> call, Response<Response<String>> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(SettingDeviceActivity.this,"重启成功",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response<String>> call, Throwable t) {
                                Toast.makeText(SettingDeviceActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                break;
            case R.id.reset:
                ApiRetrofit.initRetrofit(baseURL)
                        .reset(newpass,false)
                        .enqueue(new Callback<Response<String>>() {
                            @Override
                            public void onResponse(@NotNull Call<Response<String>> call, @NotNull Response<Response<String>> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(SettingDeviceActivity.this,"重置成功",Toast.LENGTH_LONG).show();
                                }
                            }
                            @Override
                            public void onFailure(@NotNull Call<Response<String>> call, @NotNull Throwable t) {
                                Toast.makeText(SettingDeviceActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                break;
            case R.id.setip:

                default:
        }
    }

    private void setNetInfo() {
//        if (newpass!=null && is_dhcp_mod!= null && baseURL!=null && gateway_value!=null && subnet_mask_value!=null && dns_value!= null) {
            ApiRetrofit.initRetrofit(device_ip_value+":8090")
                    .setNetInfo(newpass, is_dhcp_mod_value, baseURL, gateway_value, subnet_mask_value, dns_value)
                    .enqueue(new Callback<Response<String>>() {

                        @Override
                        public void onResponse(@NotNull Call<Response<String>> call, @NotNull Response<Response<String>> response) {
                            if (response.isSuccessful()) {
//                            JSONObject jsonObject = (JSONObject) JSONObject.parse(response.body().toString());
                                Toast.makeText(SettingDeviceActivity.this, "配置成功，请5秒钟后重启设备", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<Response<String>> call, @NotNull Throwable t) {

                        }
                    });
//        }else{
//            Toast.makeText(this,"请检查参数是否已填写完整",Toast.LENGTH_LONG).show();
//        }
    }
}
