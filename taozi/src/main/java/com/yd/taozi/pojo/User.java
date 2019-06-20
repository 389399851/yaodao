package com.yd.taozi.pojo;

import lombok.Data;

/**
 * Created by xiaotaozi on 2019/5/27.
 */
@Data
public class User {
    private int id;
    private String uname;
    private String upw;
    private String email;
    private String type;
    private String idcard;
    private String iphone;
    private String name;
    private String img;
    private String leixing;
    private String shiming;

    public User() {
    }
    public User(String uname, String email, String name) {
        this.uname = uname;
        this.email = email;
        this.name = name;
    }
    public User( String idcard, String iphone, String name, String img, String leixing) {
        this.idcard = idcard;
        this.iphone = iphone;
        this.name = name;
        this.img = img;
        this.leixing = leixing;
    }
    public User(String leixing) {
        this.leixing = leixing;
    }
    public User(String uname, String upw, String email, String type) {
        this.uname = uname;
        this.upw = upw;
        this.email = email;
        this.type = type;
    }
    public User(int id, String uname, String upw, String email, String type, String idcard, String iphone, String name, String img, String leixing, String shiming) {
        this.id = id;
        this.uname = uname;
        this.upw = upw;
        this.email = email;
        this.type = type;
        this.idcard = idcard;
        this.iphone = iphone;
        this.name = name;
        this.img = img;
        this.leixing = leixing;
        this.shiming = shiming;
    }
}
