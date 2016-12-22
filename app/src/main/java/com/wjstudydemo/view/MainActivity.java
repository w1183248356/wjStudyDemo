package com.wjstudydemo.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.adapter.MainAdapter;
import com.wjstudydemo.bean.StudyNameInfo;
import com.wjstudydemo.present.MainPresenter;
import com.wjstudydemo.util.LayoutTop;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

/**
 * @author wangjian
 * @title MainActivity
 * @description 主界面
 * @modifier
 * @date
 * @since 2016/12/22 10:48
 **/
@RequiresPresenter(MainPresenter.class)
public class MainActivity extends BaseNucleusActivity<MainPresenter> {
    @Bind(R.id.recyclerView)
    RecyclerView vRecyclerView;

    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTop();
        initView();
    }

    public void initView(){
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        vRecyclerView.setLayoutManager(gridLayoutManager);
        vRecyclerView.setItemAnimator(new DefaultItemAnimator());
        vRecyclerView.setHasFixedSize(true);
        mAdapter = new MainAdapter();
        vRecyclerView.setAdapter(mAdapter);
    }

    public void initTop(){
        LayoutTop top = new LayoutTop(this);
        top.setTitle("导航");
    }

    public void setAdapterData(List<StudyNameInfo> list){
        mAdapter.setList(list);
    }
}
