package com.lxw.news.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxw.news.R;
import com.lxw.news.bean.LoadingEndBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * author  LiXiaoWei
 * date  2018/2/2.
 * desc:
 */

public class LoadingEndViewBinder extends ItemViewBinder<LoadingEndBean, LoadingEndViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected LoadingEndViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_loading_end, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LoadingEndBean item) {

    }
    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
