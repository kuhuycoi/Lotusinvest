package com.resources.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "System_District")
public class SystemDistrict implements java.io.Serializable {

    private int id;
    private SystemCity systemCity;
    private String name;
    private boolean show;
    private String description;
    private Integer groupLocation;

    public SystemDistrict() {
    }

    public SystemDistrict(int id, SystemCity systemCity, String name, boolean show) {
        this.id = id;
        this.systemCity = systemCity;
        this.name = name;
        this.show = show;
    }

    public SystemDistrict(int id, SystemCity systemCity, String name, boolean show, String description, Integer groupLocation) {
        this.id = id;
        this.systemCity = systemCity;
        this.name = name;
        this.show = show;
        this.description = description;
        this.groupLocation = groupLocation;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CityID", nullable = false)
    public SystemCity getSystemCity() {
        return this.systemCity;
    }

    public void setSystemCity(SystemCity systemCity) {
        this.systemCity = systemCity;
    }

    @Column(name = "Name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Show", nullable = false)
    public boolean isShow() {
        return this.show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Column(name = "Description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "GroupLocation")
    public Integer getGroupLocation() {
        return this.groupLocation;
    }

    public void setGroupLocation(Integer groupLocation) {
        this.groupLocation = groupLocation;
    }

}
