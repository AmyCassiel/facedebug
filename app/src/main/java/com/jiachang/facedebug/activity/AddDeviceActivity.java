package com.jiachang.facedebug.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.jiachang.facedebug.R;
import com.jiachang.facedebug.bean.setConfigBean;
import com.jiachang.facedebug.http.ApiRetrofit;
import com.jiachang.facedebug.utils.NameUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jiachang.facedebug.utils.NameUtil.baseURL;
import static com.jiachang.facedebug.utils.NameUtil.newpass;

/**
 * @author Mickey.Ma
 * @date 2020-01-14
 * @description
 */
public class AddDeviceActivity extends Activity {
    private EditText device_name;
    private ImageView add_device;
    private Button commit_logo;
    private int REQUEST_CAPTURE_IMAGE = 1;
    public Uri fileUri;
    public Bitmap bm;
    private String filePath;
    private File f;
    private String config;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_devices);

        device_name = findViewById(R.id.edit_device_name);
        add_device = findViewById(R.id.img_logo);
        commit_logo = findViewById(R.id.commit_logo);

        String con = "{\"companyName\":\""+NameUtil.name+"\",\"identifyDistance\":1,\"identifyScores\":80,\"saveIden tifyTime\":0,\"ttsModType\":100,\"ttsModContent\":\"{n ame}\",\"comModType\":100,\"comModContent\":\"hell o\",\"displayModType\":100,\"displayModContent\":\"{$name}欢迎你\",\"slogan\":\"科技有限公司\",\"intro\":\" 智能,真的智能\",\"recStrangerTimesThreshold\":3,\"recStrangerType\"" +
                ":2,\"ttsModStrangerType\":100,\"ttsModStrangerConte nt\":\"你好\",\"multiplayerDetection\":1,\"wg\":\"#WG{id}#\",\"recR ank\":3,\"whitelist\":0}";
        config = con.replaceAll(" ","");

        NameUtil.device_name = device_name.getText().toString();

        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "图像选择"), REQUEST_CAPTURE_IMAGE);
            }
        });

        commit_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRetrofit.initRetrofit(baseURL)
                        .setConfig(newpass,config)
                        .enqueue(new Callback<setConfigBean>() {
                            @Override
                            public void onResponse(Call<setConfigBean> call, Response<setConfigBean> response) {

                            }

                            @Override
                            public void onFailure(Call<setConfigBean> call, Throwable t) {

                            }
                        });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            if(data != null) {
                Uri uri = data.getData();
                String fileName = System.currentTimeMillis() + ".jpg";
                filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator + fileName;
                f = new File(filePath);
                fileUri = Uri.fromFile(f);
                if (fileUri == null) return;
                //处理小米手机不兼容的问题
                try {
                    bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(f);
                    bm.compress(Bitmap.CompressFormat.JPEG,100, outputStream);
                    add_device.setImageBitmap(bm);
                    /*if (bm != null && !bm.isRecycled()) {
                        imageView.setImageBitmap(null); // 取消Bitmap渲染到imageView
                        bm.recycle();      //Bitmap 回收
                        bm = null;
                    }*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
