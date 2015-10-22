package com.resources.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerTree implements Serializable {

    @Id
    private int key;
    private String name;
    private String userName;
    private Integer boss;
    private String parentName;
    private String levelName;
    private Integer levelId;
    private Integer circle;
    private BigDecimal pVLeft;
    private BigDecimal pVRight;
    private int level;

    public CustomerTree() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBoss() {
        return boss;
    }

    public void setBoss(Integer boss) {
        this.boss = boss;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getCircle() {
        return circle;
    }

    public void setCircle(Integer circle) {
        this.circle = circle;
    }

    public BigDecimal getpVLeft() {
        return pVLeft;
    }

    public void setpVLeft(BigDecimal pVLeft) {
        this.pVLeft = pVLeft;
    }

    public BigDecimal getpVRight() {
        return pVRight;
    }

    public void setpVRight(BigDecimal pVRight) {
        this.pVRight = pVRight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}
