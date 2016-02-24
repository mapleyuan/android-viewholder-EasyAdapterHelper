EasyAdapterHelper
============
>an abstract class that convinent to create a ViewHolder-Adapter-pattern, you can 
focus on model and you do not need to focus recycle view. 

 
Usage
------------

>you can quickly use like this:

>listView.setAdapter(new EasyAdapter<String, BaseViewHolderHelper>(getApplicationContext(), R.layout.listview_item, datas) {
>           @Override
>           public void convert(BaseViewHolderHelper viewHolderHelper, String data) {
>                viewHolderHelper.setTextView(R.id.textView, data);
>           }
>        });


Developed By
-------------

    mapleyuan - yuanweinan00@gmail.com

License
-------------

Copyright (C) 2016 maple yuan <yuanweinan00@gmail.com>
