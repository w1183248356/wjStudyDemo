package com.wjstudydemo.view.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.adapter.MainAdapter;
import com.wjstudydemo.bean.StudyNameInfo;
import com.wjstudydemo.interfaces.OnRecyclerViewItemClickListener;
import com.wjstudydemo.present.CustomViewMainPresenter;
import com.wjstudydemo.util.DividerItemDecoration;
import com.wjstudydemo.util.LayoutTop;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;

/**
 * @author wangjian
 * @title CustomViewMainActivity
 * @description
 * @modifier
 * @date
 * @since 2017/7/7 17:10
 **/
@RequiresPresenter(CustomViewMainPresenter.class)
public class CustomViewMainActivity extends BaseNucleusActivity {
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
        vRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, DividerItemDecoration.LINEAR_LAYOUT));
        mAdapter = new MainAdapter();
        vRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data) {
                try {
                    Intent intent = new Intent(CustomViewMainActivity.this, Class.forName(((StudyNameInfo)data).studyClassName));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initTop(){
        LayoutTop top = new LayoutTop(this);
        top.setTitle("自定义view导航");
    }

    public void setAdapterData(List<StudyNameInfo> list){
        mAdapter.setList(list);
    }
}
