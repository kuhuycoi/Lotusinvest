package com.resources.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "System_Tag")
public class SystemTag implements java.io.Serializable {

    private int id;
    private String name;
    private Boolean isDelete;
    private Boolean isShow;
    private String nameAscii;
    private Boolean isHome;
    private Integer weight;

    public SystemTag() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Name", nullable = false)
    public Serializable getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "IsDelete")
    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "IsShow")
    public Boolean getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    @Column(name = "NameAscii")
    public Serializable getNameAscii() {
        return this.nameAscii;
    }

    public void setNameAscii(String nameAscii) {
        this.nameAscii = nameAscii;
    }

    @Column(name = "IsHome")
    public Boolean getIsHome() {
        return this.isHome;
    }

    public void setIsHome(Boolean isHome) {
        this.isHome = isHome;
    }

    @Column(name = "Weight")
    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
