package com.lxw.news.binder.joke;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.lxw.news.IntentAction;
import com.lxw.news.R;
import com.lxw.news.bean.joke.JokeContentBean;
import com.lxw.news.util.ImageLoader;
import com.lxw.news.widget.CircleImageView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import me.drakeet.multitype.ItemViewBinder;


/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class JokeContentViewBinder extends ItemViewBinder<JokeContentBean.DataBean.GroupBean, JokeContentViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected JokeContentViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_joke_content, parent, false);
        return new ViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final JokeContentBean.DataBean.GroupBean item) {
        final Context context = holder.itemView.getContext();
        String avatar_url = item.getUser().getAvatar_url();
        String name = item.getUser().getName();
        String text = item.getText();
        String digg_count = item.getDigg_count() + "";
        String bury_count = item.getBury_count() + "";
        int comment_count = item.getComment_count();

        ImageLoader.loadCenterCrop(context, avatar_url, holder.iv_avatar, R.mipmap.error_image);
        holder.tv_username.setText(name);
        holder.tv_text.setText(text);
        holder.tv_digg_count.setText(digg_count);
        holder.tv_bury_count.setText(bury_count);
        if (comment_count > 0) {
            holder.tv_comment_count.setText(comment_count + "评论");
        } else {
            holder.tv_comment_count.setVisibility(View.GONE);
        }
        RxView.clicks(holder.itemView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //JokeComment
                    }
                });
        holder.iv_dots.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.iv_dots, Gravity.END, 0, R.style.MyPopupMenu);
                popupMenu.inflate(R.menu.menu_joke_content);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        if (itemId == R.id.action_copy) {
                            ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clipData = ClipData.newPlainText("text", item.getText());
                            if (copy != null) {
                                copy.setPrimaryClip(clipData);
                            }
                            Snackbar.make(holder.itemView, "复制成功", Snackbar.LENGTH_SHORT).show();
                        }
                        if (itemId == R.id.action_comment_share) {
                            IntentAction.send(context, item.getText());
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView iv_avatar;
        private TextView tv_username;
        private TextView tv_text;
        private TextView tv_digg_count;
        private TextView tv_bury_count;
        private TextView tv_comment_count;
        private ImageView iv_dots;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_avatar = itemView.findViewById(R.id.iv_avatar);
            this.tv_username = itemView.findViewById(R.id.tv_username);
            this.tv_text = itemView.findViewById(R.id.tv_text);
            this.tv_digg_count = itemView.findViewById(R.id.tv_digg_count);
            this.tv_bury_count = itemView.findViewById(R.id.tv_bury_count);
            this.tv_comment_count = itemView.findViewById(R.id.tv_comment_count);
            this.iv_dots = itemView.findViewById(R.id.iv_dots);
        }
    }
}
