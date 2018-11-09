package com.zs.entity;

import java.util.Date;

/**
 * Created with IDEA
 * author:LiuYiBo
 * Date:2018/7/18
 * Time:21:16
 */
public class LocalAnth {

    private Long localAnthId;
    private String username;
    private String password;
    private Date createTime;
    private Date lastEditTime;
    private PersonInfo personInfo;


    public Long getLocalAnthId() {
        return localAnthId;
    }

    public void setLocalAnthId(Long localAnthId) {
        this.localAnthId = localAnthId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
