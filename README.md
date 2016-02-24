EasyAdapterHelper
============
>an abstract class that convinent to create a ViewHolder-Adapter-pattern, you can 
focus on model and you do not need to focus recycle view. 

Usage
------------

1.you can quickly use like this:

<pre><code>listView.setAdapter(new EasyAdapter<String, BaseViewHolderHelper>(getApplicationContext(), R.layout.listview_item, datas) {
   @Override
           public void convert(BaseViewHolderHelper viewHolderHelper, String data) {
                 viewHolderHelper.setTextView(R.id.textView, data);
            }
         });
</code></pre>

2.you can also implement EasyAdapter,like this:
 <pre><code> public class ChatViewAdapter extends EasyAdapter<ChatViewAdapter.MessageModle, BaseViewHolderHelper> {

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
 </code></pre>

example effect
-------------
![image](https://github.com/mapleyuan/EasyAdapterHelper/blob/master/screenshot/example_effect.png)

Developed By
-------------

    mapleyuan - yuanweinan00@gmail.com

License
-------------

Copyright (C) 2016 maple yuan <yuanweinan00@gmail.com>
