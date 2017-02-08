package com.wjstudydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.StudyNameInfo;
import com.wjstudydemo.interfaces.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangjian
 * @title MainAdapter
 * @description
 * @modifier
 * @date
 * @since 2016/12/22 15:53
 **/
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private OnRecyclerViewItemClickListener l;
    private List<StudyNameInfo> list;

    public MainAdapter(){
        list = new ArrayList<>();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener l){
        this.l = l;
    }

    public void setList(List<StudyNameInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_main, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StudyNameInfo info = list.get(position);
        holder.tvName.setText(info.studyName);
        holder.itemView.setTag(info);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.onItemClick(v, v.getTag());
                }
            });
        }
    }
}
