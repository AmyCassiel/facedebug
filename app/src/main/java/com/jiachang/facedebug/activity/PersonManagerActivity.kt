package com.jiachang.facedebug.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jiachang.facedebug.R
import com.jiachang.facedebug.adapter.MyAdapter
import kotlinx.android.synthetic.main.person_manager_layout.*


/**
 * @author Mickey.Ma
 * @date 2020-01-08
 * @description
 */
class PersonManagerActivity : Activity(){
    val list: List<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_manager_layout)

        add_person.setOnClickListener{
            val intent = Intent(this@PersonManagerActivity, AddPersonActivity::class.java)
            startActivity(intent)
        }

        initList()

    }

    private fun initList() {
        //如果请求成功了,把数据加载进去
        recyclerView!!.layoutManager = LinearLayoutManager(this@PersonManagerActivity)
        var mAdapter = MyAdapter(R.layout.item_recycler, list)
        recyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val posit = position // gets item position

            when {
                posit != RecyclerView.NO_POSITION -> { // Check if an item was deleted, but the user clicked it before the UI removed it
//                        val name: String = position.getText()
//                        Toast.makeText(this@PersonManagerActivity, "$name was clicked!", Toast.LENGTH_SHORT)
//                            .show()
                }
            }
        })
    }
}