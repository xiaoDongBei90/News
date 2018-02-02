package com.lxw.news.util;

import android.support.v7.util.DiffUtil;

import javax.annotation.Nonnull;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class DiffCallback extends DiffUtil.Callback {
    private final Items mOldItems, mNewItems;

    public DiffCallback(Items oldItems, Items mNewItems) {
        this.mOldItems = oldItems;
        this.mNewItems = mNewItems;
    }

    public static void create(@Nonnull Items oldList, @Nonnull Items newList, @Nonnull MultiTypeAdapter adapter) {
        DiffCallback diffCallback = new DiffCallback(oldList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, true);
        diffResult.dispatchUpdatesTo(adapter);
    }

    @Override
    public int getOldListSize() {
        return mOldItems != null ? mOldItems.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewItems != null ? mNewItems.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).hashCode() == mNewItems.get(newItemPosition).hashCode();
    }
}
