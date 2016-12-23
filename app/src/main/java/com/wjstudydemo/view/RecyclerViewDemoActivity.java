package com.wjstudydemo.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wjstudydemo.BaseNucleusActivity;
import com.wjstudydemo.R;
import com.wjstudydemo.adapter.RecyclerViewDemoAdapter;
import com.wjstudydemo.present.RecyclerViewPresenter;
import com.wjstudydemo.util.DividerGridItemDecoration;
import com.wjstudydemo.util.DividerItemDecoration;
import com.wjstudydemo.util.LayoutTop;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;

/**
 * @author wangjian
 * @title RecyclerViewDemo
 * @description
 * @modifier
 * @date
 * @since 2016/12/23 9:12
 * 添加动画  https://github.com/gabrielemariotti/RecyclerViewItemAnimators
 **/
@RequiresPresenter(RecyclerViewPresenter.class)
public class RecyclerViewDemoActivity extends BaseNucleusActivity<RecyclerViewPresenter> {
    private static final int LinearLayout = 0;
    private static final int GridLayout = 1;
    private static final int StaggerenGridLayout = 2;

    @Bind(R.id.recyclerView)
    RecyclerView vRecyclerView;
    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.tv_sub)
    TextView tvSub;

    private int type = 0;
    private int i = 0;

    private RecyclerViewDemoAdapter mAdapter;
    private DividerItemDecoration decoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recyclerview_demo);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.tv_add)
    public void onAddClick(View v){
        mAdapter.addItem(5);
    }

    @OnClick(R.id.tv_sub)
    public void onSubClick(View v){
        mAdapter.removeItem(6);
    }

    public void initView() {
        initTop();
        vRecyclerView.setHasFixedSize(true);
        vRecyclerView.setItemAnimator(new DefaultItemAnimator());
        setManager();
        mAdapter = new RecyclerViewDemoAdapter();
        vRecyclerView.setAdapter(mAdapter);
    }

    public void setManager(){
        if(type == LinearLayout){
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            vRecyclerView.setLayoutManager(layoutManager);
            decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, DividerItemDecoration.LINEAR_LAYOUT);
            vRecyclerView.addItemDecoration(decoration);
        } else if(type == GridLayout){
            GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
            vRecyclerView.setLayoutManager(layoutManager);
            DividerGridItemDecoration decoration = new DividerGridItemDecoration(this);
            vRecyclerView.addItemDecoration(decoration);
        } else if(type == StaggerenGridLayout){

        }
    }

    public void initTop() {
        LayoutTop top = new LayoutTop(this);
        top.setTitle("RecyclerViewDemo");
        top.setLeftButton("返回");
        top.setOnLeftListener(new LayoutTop.OnLeftListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        top.setRightButton("转换");
        top.setOnRightListener(new LayoutTop.OnRightListener() {
            @Override
            public void onClick() {
                setType(i);
                setManager();
            }
        });
    }

    public void setType(int i) {
        type = i % 3;
        this.i++;
    }

    public void setList(List<String> list) {
        mAdapter.setList(list);
    }
}
