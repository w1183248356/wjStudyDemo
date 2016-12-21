package com.network.network.model;

/**
 * Created by wlw-97 on 2016/9/6.
 */

public class LegworkInfo {
    private String name;
    private String phone;

    public LegworkInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
