package com.wjstudydemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjstudydemo.bean.Sample;
import com.wjstudydemo.databinding.ItemRowSampleBinding;
import com.wjstudydemo.util.TransitionHelper;

import java.util.List;

public class SamplesRecyclerAdapter extends RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder> {
    private final Activity activity;
    private final List<Sample> samples;
    private OnItemClickListener l;

    public SamplesRecyclerAdapter(Activity activity, List<Sample> samples) {
        this.activity = activity;
        this.samples = samples;
    }

    public void setOnItemClickListener(OnItemClickListener l){
        this.l = l;
    }

    @Override
    public SamplesViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        ItemRowSampleBinding binding = ItemRowSampleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SamplesViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final SamplesViewHolder viewHolder, final int position) {
        final Sample sample = samples.get(viewHolder.getAdapterPosition());
        viewHolder.binding.setSample(sample);
        viewHolder.itemView.setOnClickListener(v->{
            l.onClick(v, viewHolder);
        });
    }

    private void transitionToActivity(Class target, Sample sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, true);
        startActivity(target, pairs, sample);
    }
//
//
//    private void transitionToActivity(Class target, SamplesViewHolder viewHolder, Sample sample, int transitionName) {
//        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
//                new Pair<>(viewHolder.binding.sampleIcon, activity.getString(transitionName)));
//        startActivity(target, pairs, sample);
//    }
//
//    private void transitionToActivity(Class target, SamplesViewHolder viewHolder, Sample sample) {
//        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
//                new Pair<>(viewHolder.binding.sampleIcon, activity.getString(R.string.square_blue_name)),
//                new Pair<>(viewHolder.binding.sampleName, activity.getString(R.string.sample_blue_title)));
//        startActivity(target, pairs, sample);
//    }
//
    private void startActivity(Class target, Pair<View, String>[] pairs, Sample sample) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
        i.putExtra("sample", sample);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivity(i, transitionActivityOptions.toBundle());
        }
    }

    @Override
    public int getItemCount() {
        return samples.size();
    }


    public class SamplesViewHolder extends RecyclerView.ViewHolder {
        final ItemRowSampleBinding binding;

        public SamplesViewHolder(View rootView) {
            super(rootView);
            binding = DataBindingUtil.bind(rootView);
        }
    }

    public interface OnItemClickListener{
        void onClick(View v, SamplesViewHolder viewHolder);
    }
}
