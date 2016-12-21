package com.network.network.dialog;

/**
 * Created by wlw-97 on 2016/5/19.
 */
public class UpdateModel {
    private boolean needUpdate;
    private Object data;

    public UpdateModel(boolean needUpdate, Object data) {
        this.needUpdate = needUpdate;
        this.data = data;
    }

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
