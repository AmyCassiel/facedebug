package com.jiachang.facedebug.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiachang.facedebug.R;
import com.jiachang.facedebug.utils.NameUtil;

import java.util.List;

/**
 * @author Mickey.Ma
 * @date 2020-01-10
 * @description
 */
public class MyAdapter extends BaseQuickAdapter {

    public MyAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, Object item) {
        helper.setText(R.id.name_text,NameUtil.device_name);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
