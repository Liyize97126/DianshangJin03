package com.bawei.dianshangjin03;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    //定义
    @BindView(R.id.wenben)
    protected TextView wenben;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //绑定ButterKnife的Activity对象
        ButterKnife.bind(this);
        //注册EventBus（注册之后，才能订阅到事件，不然无法订阅，别忘记取消注册）
        EventBus.getDefault().register(this);
    }
    //接收订阅（订阅者）@Subscribe代表eventbus的事件接收
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void postText(String text){
        Log.i("Tag",text);
        wenben.setText(text);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }
}
