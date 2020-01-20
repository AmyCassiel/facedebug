package com.jiachang.facedebug.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.jiachang.facedebug.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Mickey.Ma
 * @date 2020-01-08
 * @description
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        applyPermission()

        person.setOnClickListener {
            val intent = Intent(this@MainActivity, PersonManagerActivity::class.java)
            startActivity(intent)
        }

        device.setOnClickListener {
            val intent = Intent(this@MainActivity, DeviceManagerActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
     */
    private fun applyPermission() {
        if (Build.VERSION.SDK_INT > 9) {
            val policy =
                StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        if (Build.VERSION.SDK_INT >= 23) {
            val REQUEST_CODE_CONTACT = 101
            val permissions = arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            //验证是否许可权限
            for (str in permissions) {
                if (checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) { //申请权限
                    requestPermissions(permissions, REQUEST_CODE_CONTACT)
                    return
                }
            }
        }
    }

}