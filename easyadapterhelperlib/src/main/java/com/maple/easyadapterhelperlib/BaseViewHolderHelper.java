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
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * it is divide data and view utils,provide some usual view utils
 * <p/>
 * Created by yuanweinan on 16-2-22.
 * source:https://github.com/mapleyuan/EasyAdapterHelper
 */
public class BaseViewHolderHelper {

    private Context mContext;
    /**
     * this is the map contains had found view.
     */
    private SparseArray<View> mViews;
    /**
     * a resource ID for an XML layout resource to load (e.g. <code>
     * R.layout.example
     * </code>)
     */
    private int mResource;
    /**
     * the view to reuse
     */
    private View mConvertView;

    /**
     * @param context
     * @param resource #mResource
     */
    public BaseViewHolderHelper(Context context, int resource, ViewGroup parent) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        mContext = context.getApplicationContext();
        mResource = resource;
        mConvertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        mConvertView.setTag(this);
        mViews = new SparseArray<>();
    }


    /**
     * @param context
     * @param resource    @see #mResource
     * @param parent
     * @param convertView {mConvertView}
     * @return
     */
    public static BaseViewHolderHelper createViewHolder(Context context, int resource, ViewGroup parent, View convertView) {
        BaseViewHolderHelper viewHolderHelper;
        if (convertView == null) {
            return new BaseViewHolderHelper(context, resource, parent);
        } else {
            viewHolderHelper = (BaseViewHolderHelper) convertView.getTag();
            if (viewHolderHelper.mResource != resource) {
                return new BaseViewHolderHelper(context, resource, parent);
            }
        }
        return viewHolderHelper;
    }

    public BaseViewHolderHelper setTextView(int id, CharSequence text) {
        TextView view = (TextView) getView(id);
        view.setText(text);
        return this;
    }

    public BaseViewHolderHelper setTextViewColor(int id, int color) {
        TextView view = (TextView) getView(id);
        view.setTextColor(color);
        return this;
    }

    public BaseViewHolderHelper setBackground(int id, Drawable drawable) {
        View view = getView(id);
        view.setBackground(drawable);
        return this;
    }

    public BaseViewHolderHelper setBackgroundResource(int id, int resource) {
        View view = getView(id);
        view.setBackgroundResource(resource);
        return this;
    }

    public BaseViewHolderHelper setOnClickListener(int id, View.OnClickListener l) {
        View view = getView(id);
        view.setOnClickListener(l);
        return this;
    }

    public BaseViewHolderHelper setImageResource(int id, int resource) {
        ImageView imageView = getImageView(id);
        imageView.setImageResource(resource);
        return this;
    }

    public BaseViewHolderHelper setImageDrawable(int id, Drawable drawable) {
        ImageView imageView = getImageView(id);
        imageView.setImageDrawable(drawable);
        return this;
    }

    /**
     * @param id
     * @param visibility @see #View.VISIBLE, #View.INVISIBLE, or View.GONE  @link #View.setVisibility().
     */
    public void setVisibility(int id, int visibility) {
        View view = getView(id);
        view.setVisibility(visibility);
    }

    /**
     * Returns the imageview that found by the given id
     *
     * @param id the view id
     * @return the imageview that found by the given id
     */
    public ImageView getImageView(int id) {
        return (ImageView) getView(id);
    }

    /**
     * Returns the view that found by the given id
     *
     * @param id the view id
     * @return the view that found by the given id
     */
    public View getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = mConvertView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    View getConvertView() {
        return mConvertView;
    }

}
