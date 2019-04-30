package com.matematikadetik.tosm.model;

import java.util.Date;

/**
 * Created by ewin on 13/02/18.
 */

public class User {
    private String alias, prov, sex, kab, dob;
    public User(String dob, String alias, String sex, String prov, String kab) {
        this.dob = dob;
        this.alias = alias;
        this.prov = prov;
        this.sex = sex;
        this.kab = kab;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getKab() {
        return kab;
    }

    public void setKab(String kab) {
        this.kab = kab;
    }
}
