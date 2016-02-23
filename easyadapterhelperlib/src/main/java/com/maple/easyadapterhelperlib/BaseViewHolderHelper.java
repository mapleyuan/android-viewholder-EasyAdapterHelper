package com.maple.easyadapterhelperlib;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yuanweinan on 16-2-22.
 */
public class BaseViewHolderHelper {

    private Context mContext;
    private SparseArray<View> mViews;
    private int mResource;
    private View mConvertView;

    public BaseViewHolderHelper(Context context, int resource) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        mContext = context.getApplicationContext();
        mResource = resource;
        mConvertView = LayoutInflater.from(mContext).inflate(mResource, null);
        mConvertView.setTag(this);
        mViews = new SparseArray<View>();
    }


    public static BaseViewHolderHelper createViewHolder(Context context, int resource, View convertView) {
        BaseViewHolderHelper viewHolderHelper;
        if (convertView == null) {
            return new BaseViewHolderHelper(context, resource);
        } else {
            viewHolderHelper = (BaseViewHolderHelper) convertView.getTag();
            if (viewHolderHelper.mResource != resource) {
                return new BaseViewHolderHelper(context, resource);
            }
        }
        return viewHolderHelper;
    }

    View getConvertView() {
        return mConvertView;
    }


    public void setTextView(int id, CharSequence text) {
        TextView view = (TextView) getView(id);
        view.setText(text);
    }

    /**
     * @param id
     * @param visibility One of {@link #VISIBLE}, {@link #INVISIBLE}, or {@link #GONE}.
     */
    public void setVisibility(int id, int visibility) {
        View view = getView(id);
        view.setVisibility(visibility);
    }

    public ImageView getImageView(int id) {
        return (ImageView) getView(id);
    }

    public View getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = mConvertView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

}
