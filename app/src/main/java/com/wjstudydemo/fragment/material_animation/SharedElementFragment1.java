package com.wjstudydemo.fragment.material_animation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wjstudydemo.R;
import com.wjstudydemo.bean.Sample;

/**
 * @author wangjian
 * @title SharedElementFragment1
 * @description
 * @modifier
 * @date
 * @since 2017/1/19 10:40
 **/
public class SharedElementFragment1 extends Fragment {
    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElementFragment1 newInstance(Sample sample) {

        Bundle args = new Bundle();

        args.putSerializable(EXTRA_SAMPLE, sample);
        SharedElementFragment1 fragment = new SharedElementFragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sharedelement_fragment1, container, false);
        final Sample sample = (Sample) getArguments().getSerializable(EXTRA_SAMPLE);

        final ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.getColor());

        view.findViewById(R.id.sample2_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(sample, squareBlue, false);
            }
        });

        view.findViewById(R.id.sample2_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNextFragment(sample, squareBlue, true);
            }
        });

        return view;
    }

    private void addNextFragment(Sample sample, ImageView squareBlue, boolean overlap) {
        SharedElementFragment2 sharedElementFragment2 = SharedElementFragment2.newInstance(sample);

        Slide slideTransition = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slideTransition = new Slide(Gravity.RIGHT);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
            ChangeBounds changeBoundsTransition = new ChangeBounds();
            changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
            sharedElementFragment2.setEnterTransition(slideTransition);
            sharedElementFragment2.setAllowEnterTransitionOverlap(overlap);
            sharedElementFragment2.setAllowReturnTransitionOverlap(overlap);
            sharedElementFragment2.setSharedElementEnterTransition(changeBoundsTransition);
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment2)
                .addToBackStack(null)
                .addSharedElement(squareBlue, getString(R.string.square_blue_name))
                .commit();
    }
}
