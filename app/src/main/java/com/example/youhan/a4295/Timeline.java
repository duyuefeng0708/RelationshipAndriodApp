//cs4295
package com.example.youhan.a4295;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.jude.utils.JUtils;

import java.util.ArrayList;

public class Timeline extends AppCompatActivity implements MessageDB.DatebaseListener {

    ArrayList<MessageItem> messageArray = new ArrayList<MessageItem>();
    private RecyclerView messageView;
    private MessageAdapter messageAdapter;
    private MessageItem selectedItem;
    MessageDB helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_r);
        init(savedInstanceState);

        setTitle("Timeline");
        (findViewById(R.id.send_button)).setOnClickListener(new ListenerInMain());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_p);
        setSupportActionBar(myToolbar);

        Intent i = getIntent();
        final String user = i.getStringExtra("username");
        final String partner = i.getStringExtra("partnername");

        ImageButton m1=(ImageButton)findViewById(R.id.button1_p);
        ImageButton m2=(ImageButton)findViewById(R.id.button2_p);
        ImageButton m3=(ImageButton)findViewById(R.id.button3_p);
        ImageButton m4=(ImageButton)findViewById(R.id.button4_p);
        ImageButton m5=(ImageButton)findViewById(R.id.button5_p);

        m1.setBackgroundColor(Color.parseColor("#808080"));
        m2.setBackgroundColor(Color.parseColor("#808080"));
        m3.setBackgroundColor(Color.parseColor("#808080"));
        m4.setBackgroundColor(Color.parseColor("#808080"));
        m5.setBackgroundColor(Color.parseColor("#808080"));

        m5.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Timeline.this, Profile.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m4.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Timeline.this, Location.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m3.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Timeline.this, ChatActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m1.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Timeline.this, MainActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });
    }

    private void init(Bundle savedInstanceState) {
        JUtils.initialize(getApplication());
        JUtils.setDebug(BuildConfig.DEBUG, "TINYNOTE");

        helper = new MessageDB(this);
        helper.setListener(this);

        messageAdapter = new MessageAdapter(messageArray);
        messageView = (RecyclerView) findViewById(R.id.recyclerview_message_show);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Timeline.this);
        linearLayoutManager.setStackFromEnd(true);
        messageView.setLayoutManager(linearLayoutManager);
        messageView.setAdapter(messageAdapter);
        messageView.setItemAnimator(new DefaultItemAnimator());
        messageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

            @Override
            public void onChanged() {
                messageView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
        registerForContextMenu(messageView);
        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.animate().translationZ(15F).setDuration(300).setListener(new AnimatorListenerAdapter() {
                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.animate().translationZ(1f).setDuration(500).start();
                        }
                    }).start();
                }
            }

            @Override
            public boolean onItemLongClick(View view, int position) {
                selectedItem = messageAdapter.getData(position);
                return false;
            }
        });
        getMessage();
    }


    private void refreshMessageView() {
        getMessage();
    }

    private void getMessage() {
        helper.getMessage();
    }

    @Override
    public void onDatabaseChnage() {
        refreshMessageView();
    }

    @Override
    public void onQueryResult(ArrayList<MessageItem> itemArray) {
        messageAdapter.setData(itemArray);
        messageAdapter.notifyDataSetChanged();
    }

    private class ListenerInMain implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.send_button:
                    EditText messageInput = (EditText) findViewById(R.id.edittext_message_input);
                    final String newMessage = messageInput.getText().toString();
                    helper.insert(newMessage, 0);
                    messageInput.setText(R.string.blank);
                    break;
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        JUtils.Log("inContextMenu");
        if (selectedItem == null) {
            return;
        }
        menu.setHeaderTitle(selectedItem.getMessage());
        menu.add(0, 0, 0, R.string.copy_to_clipboard);
        menu.add(0, 1, 0, R.string.delete_this_message);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                JUtils.copyToClipboard(selectedItem.getMessage());
                JUtils.Toast(getResources().getString(R.string.copy_to_clipboard_success));
                break;
            case 1:
                deleteMessage(selectedItem);
                break;
        }
        return true;
    }

    private void deleteMessage(MessageItem item) {
        helper.delete(item);
    }
}
