package com.maple.easyadapterhelperlib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by yuanweinan on 16-2-22.
 * @param <T>
 * @param <H>
 */
public abstract  class EasyAdapter<T, H extends BaseViewHolderHelper> extends BaseAdapter {


    /**
     * @param <T>
     */
    public interface IMultiItemType<T> {
         int getResource(int position, T t);
         int getViewTypeCount();
         int getItemViewType(int position, T t);

    }

    private List<T> mDatas;
    protected Context mContext;
    private int mResource;
    private IMultiItemType<T> mMultiItemType;

    public EasyAdapter(Context context, int resource, List<T> datas) {
        mContext = context;
        mResource = resource;
        mDatas = datas;
    }

    public EasyAdapter(Context context, List<T> datas, IMultiItemType<T> multiItemType) {
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
            viewHolderHelper = H.createViewHolder(mContext, mMultiItemType.getResource(position, mDatas.get(position)), convertView);
        } else {
            viewHolderHelper = H.createViewHolder(mContext, mResource, convertView);
        }
        convert(viewHolderHelper, position, mDatas.get(position));
        return viewHolderHelper.getConvertView();
    }

    public abstract void convert(BaseViewHolderHelper viewHolderHelper, int position, T data);
}
