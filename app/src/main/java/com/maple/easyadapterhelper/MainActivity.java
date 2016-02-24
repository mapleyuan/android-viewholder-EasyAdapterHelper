package com.maple.easyadapterhelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.maple.easyadapterhelperlib.BaseViewHolderHelper;
import com.maple.easyadapterhelperlib.EasyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.listview_id);
        List<ChatViewAdapter.MessageModle> datas = new ArrayList<>();
        String sb = "hey";
        for (int i = 0; i < 100; i++) {
            ChatViewAdapter.MessageModle messageModle = new ChatViewAdapter.MessageModle(i % 3 == 0, i % 2 == 0 ? ChatViewAdapter.MessageModle.MESSAGE_TYPE_TEXT : ChatViewAdapter.MessageModle.MESSAGE_TYPE_ATTACHMENT, sb);
            datas.add(messageModle);
        }
        listView.setAdapter(new ChatViewAdapter(getApplicationContext(), datas));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
