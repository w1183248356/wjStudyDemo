package com.wjstudydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjstudydemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangjian
 * @title RecyclerViewDemoAdapter
 * @description
 * @modifier
 * @date
 * @since 2016/12/23 10:03
 **/
public class RecyclerViewDemoAdapter extends RecyclerView.Adapter<RecyclerViewDemoAdapter.ViewHolder> {
    private List<String> list;

    public RecyclerViewDemoAdapter(){
        list = new ArrayList<>();
    }

    public void setList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(int positon){
        list.add(positon, "Insterfdsag");
        notifyItemInserted(5);
    }

    public void removeItem(int position){
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  v = View.inflate(parent.getContext(), R.layout.item_recyclerview_demo, null);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.id_num)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
