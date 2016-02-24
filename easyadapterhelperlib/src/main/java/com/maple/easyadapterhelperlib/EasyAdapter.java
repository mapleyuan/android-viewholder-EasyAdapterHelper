/**
 * Copyright 2016 Maple Yuan
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maple.easyadapterhelperlib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * an abstract class that convinience to create a ViewHolder-Adapter-pattern, you can
 * focus on model and you do not need to focus recycle view.
 *
 * Created by yuanweinan on 16-2-22.
 * source:https://github.com/mapleyuan/EasyAdapterHelper
 * @param <T> an model object what you should modify
 * @param <H> an object that extends #BaseViewHolderHelper
 */
public abstract  class EasyAdapter<T, H extends BaseViewHolderHelper> extends BaseAdapter {


    /**
     * an Interface that provides some multi adapter item type method.if you should use some method
     * that the interface not provide,you can implemment it and add some your modify method.
     *
     * @param <T> an model object what you should modify
     */
    public interface IMultiItem<T> {
        /**
         * Returns the layout resource ID
         * @param position the item position
         * @param t the model object
         * @return the layout resource ID
         */
         int getResource(int position, T t);

        /**
         * <p>
         * Returns the number of types of Views that will be created by
         * {@link #getView}. Each type represents a set of views that can be
         * converted in {@link #getView}. If the adapter always returns the same
         * type of View for all items, this method should return 1.
         * </p>
         * <p>
         * This method will only be called when when the adapter is set on the
         * the {@link AdapterView}.
         * </p>
         *
         * @return The number of types of Views that will be created by this adapter
         */
         int getViewTypeCount();

        /**
         *  * Get the type of View that will be created by {@link #getView} for the specified item.
         *
         * @param position The position of the item within the adapter's data set whose view type we
         *        want.
         * @param t
         * @return An integer representing the type of View. Two views should share the same type if one
         *         can be converted to the other in {@link #getView}. Note: Integers must be in the
         *         range 0 to {@link #getViewTypeCount} - 1. {@link #IGNORE_ITEM_VIEW_TYPE} can
         *         also be returned.
         */
         int getItemViewType(int position, T t);

    }

    private List<T> mDatas;
    protected Context mContext;
    private int mResource;
    private IMultiItem<T> mMultiItemType;

    public EasyAdapter(Context context, int resource, List<T> datas) {
        mContext = context;
        mResource = resource;
        mDatas = datas;
    }

    public EasyAdapter(Context context, List<T> datas, IMultiItem<T> multiItemType) {
        mContext = context;
        mDatas = datas;
        mMultiItemType = multiItemType;
    }

    @Override
    public int getViewTypeCount() {
        return mMultiItemType == null ? super.getViewTypeCount() : mMultiItemType.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemType == null ? super.getItemViewType(position) : mMultiItemType.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolderHelper viewHolderHelper;
        if (mMultiItemType != null) {
            viewHolderHelper = H.createViewHolder(mContext, mMultiItemType.getResource(position, mDatas.get(position)), parent, convertView);
        } else {
            viewHolderHelper = H.createViewHolder(mContext, mResource, parent, convertView);
        }
        convert(viewHolderHelper, mDatas.get(position));
        return viewHolderHelper.getConvertView();
    }

    /**
     * Implement it and use BaseViewHolderHelper to adpater view from the given T data.
     * @param viewHolderHelper @link #BaseViewHolderHelper
     * @param data
     */
    public abstract void convert(BaseViewHolderHelper viewHolderHelper, T data);
}
