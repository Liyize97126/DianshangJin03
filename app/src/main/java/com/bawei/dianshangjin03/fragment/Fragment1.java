package com.bawei.dianshangjin03.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei.dianshangjin03.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment1 extends Fragment {
    Unbinder bind;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //视图
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment1, container, false);
        //绑定
        bind = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    //点击事件
    @OnClick(R.id.btn)
    protected void pustBtn(){
        Toast.makeText(getContext(),"1-您按下了Push按钮！",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //释放
        bind.unbind();
    }
}
