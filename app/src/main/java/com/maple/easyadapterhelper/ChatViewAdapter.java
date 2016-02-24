package com.maple.easyadapterhelper;

import android.content.Context;
import android.view.View;

import com.maple.easyadapterhelperlib.BaseViewHolderHelper;
import com.maple.easyadapterhelperlib.EasyAdapter;

import java.util.List;

/**
 * Created by yuanweinan on 16-2-23.
 */
public class ChatViewAdapter extends EasyAdapter<ChatViewAdapter.MessageModle, BaseViewHolderHelper> {

    public ChatViewAdapter(Context context, List<MessageModle> datas) {
        super(context, datas, new EasyAdapter.IMultiItem<MessageModle>() {
            @Override
            public int getResource(int position, MessageModle doubleChatMessage) {
                return doubleChatMessage.mIsFrom ? R.layout.chatview_list_right_item : R.layout.chatview_list_left_item;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position, MessageModle doubleChatMessage) {
                return doubleChatMessage.mIsFrom ? 0 : 1;
            }
        });
    }

    @Override
    public void convert(BaseViewHolderHelper viewHolderHelper, MessageModle data) {
        if (data.mType == MessageModle.MESSAGE_TYPE_TEXT) {
            viewHolderHelper.setTextView(R.id.chatview_maincontent_text_id, data.mContent);
            viewHolderHelper.setVisibility(R.id.chatview_maincontent_image_id, View.GONE);
            viewHolderHelper.setVisibility(R.id.chatview_maincontent_text_id, View.VISIBLE);
        } else {
            viewHolderHelper.setVisibility(R.id.chatview_maincontent_image_id, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.chatview_maincontent_text_id, View.GONE);
        }
    }

    /**
     *
     */
    public static class MessageModle {
        public static final int MESSAGE_TYPE_TEXT = 1;
        public static final int MESSAGE_TYPE_ATTACHMENT = 2;
        boolean mIsFrom;
        /**
         * @see {@link #MESSAGE_TYPE_TEXT}
         */
        int mType;
        public String mContent;

        public MessageModle(boolean isFrom, int type, String content) {
            mIsFrom = isFrom;
            mType = type;
            mContent = content;
        }

    }
}
