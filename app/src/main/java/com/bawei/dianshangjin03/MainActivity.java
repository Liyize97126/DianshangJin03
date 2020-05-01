package com.bawei.dianshangjin03;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {
    //定义（使用BindView绑定ID）
    @BindView(R.id.edit)
    protected EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定ButterKnife的Activity对象
        ButterKnife.bind(this);
        //注册EventBus（注册之后，才能订阅到事件，不然无法订阅，别忘记取消注册）
        EventBus.getDefault().register(this);
    }
    //点击事件
    @OnClick(R.id.butt01)
    protected void click01(){
        String text = edit.getText().toString();
        if(TextUtils.isEmpty(text)){
            Toast.makeText(this,"输入的内容为空！",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"输入的内容：" + text,Toast.LENGTH_LONG).show();
        }
    }
    @OnClick(R.id.butt02)
    protected void click02(){
        edit.setText("八维学院");
    }
    //点击长按事件
    @OnLongClick({R.id.butt01,R.id.butt02})
    protected boolean longClick(){
        Toast.makeText(this,"你好哇！",Toast.LENGTH_LONG).show();
        return true;
    }
    //Fragment使用ButterKnife
    @OnClick(R.id.butt03)
    protected void click03(){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }
    //传值
    @OnClick(R.id.chuan01)
    protected void chuan01(){
        //发送普通事件（其可以被任何订阅者接收可以被任何订阅者接收）
        EventBus.getDefault().post("这是传来的值...");
        //EventBus.getDefault().post(100);
    }
    @OnClick(R.id.chuan02)
    protected void chuan02(){
        //获取值
        String text = edit.getText().toString();
        if(TextUtils.isEmpty(text)){
            Toast.makeText(this,"输入的内容为空！",Toast.LENGTH_LONG).show();
        } else {
            //发送粘性事件
            EventBus.getDefault().postSticky(text);
            //跳转
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        }
    }
    //接收普通事件：通过订阅者接收（@Subscribe代表eventbus的事件接收）
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void postText(String text){
        Log.i("Tag",text);
        edit.setText(text);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
