package com.wjstudydemo.present;

import android.os.Bundle;
import android.support.annotation.NonNull;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;
import nucleus.view.ViewWithPresenter;

/**
 * @author jin
 * @title BasePresenter
 * @description
 * @modifier
 * @date
 * @since 2016/6/7 12:06
 **/
public class BasePresenter<V extends ViewWithPresenter> extends RxPresenter<V> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    @Override
    protected void onSave(@NonNull Bundle state) {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }

}
