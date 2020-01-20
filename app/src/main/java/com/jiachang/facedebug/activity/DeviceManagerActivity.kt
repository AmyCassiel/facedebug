package com.jiachang.facedebug.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.jiachang.facedebug.R
import com.jiachang.facedebug.adapter.MyAdapter
import com.jiachang.facedebug.utils.NameUtil
import kotlinx.android.synthetic.main.device_top_bar.*

/**
 * @author Mickey.Ma
 * @date 2020-01-08
 * @description
 */
class DeviceManagerActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.device_manager_layout)
        setting_device.setOnClickListener{
            var intent = Intent(this@DeviceManagerActivity, SettingDeviceActivity::class.java)
            startActivity(intent)
        }

        add_device.setOnClickListener {
            var intent = Intent(this@DeviceManagerActivity,AddDeviceActivity::class.java)
            startActivity(intent)
        }

//        var listName = List<String>()
//        var mAdapter = MyAdapter(R.layout.item_recycler, )
    }
}