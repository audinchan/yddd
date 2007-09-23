package com.yodoo.rent.model;
// Generated 2007-9-22 10:24:22 by Hibernate Tools 3.2.0.b9


import java.util.Date;

/**
 * OnlineUser generated by hbm2java
 */
public class OnlineUser  implements java.io.Serializable {


     private Integer id;
     private String nickname;
     private String ipaddr;
     private Date activeTime;
     private Integer gender;

    public OnlineUser() {
    }

	
    public OnlineUser(Integer id) {
        this.id = id;
    }
    public OnlineUser(Integer id, String nickname, String ipaddr, Date activeTime, Integer gender) {
       this.id = id;
       this.nickname = nickname;
       this.ipaddr = ipaddr;
       this.activeTime = activeTime;
       this.gender = gender;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getIpaddr() {
        return this.ipaddr;
    }
    
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }
    public Date getActiveTime() {
        return this.activeTime;
    }
    
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }
    public Integer getGender() {
        return this.gender;
    }
    
    public void setGender(Integer gender) {
        this.gender = gender;
    }




}

