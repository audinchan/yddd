package com.yodoo.rent.model;
// Generated 2007-9-22 21:07:25 by Hibernate Tools 3.2.0.b9


import java.util.Date;

/**
 * VisitLog generated by hbm2java
 */
public class VisitLog  implements java.io.Serializable {


     private Integer id;
     private String houseId;
     private Integer userId;
     private Date visitTime;

    public VisitLog() {
    }

    public VisitLog(Integer id, String houseId, Integer userId, Date visitTime) {
       this.id = id;
       this.houseId = houseId;
       this.userId = userId;
       this.visitTime = visitTime;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getHouseId() {
        return this.houseId;
    }
    
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Date getVisitTime() {
        return this.visitTime;
    }
    
    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }




}

