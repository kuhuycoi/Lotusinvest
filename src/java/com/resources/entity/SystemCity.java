package com.resources.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "System_City")
public class SystemCity implements java.io.Serializable {

    private int id;
    private Serializable name;
    private boolean show;
    private Serializable description;
    private Integer displayOrder;
    private Serializable groupLocation;
    private Set<SystemDistrict> systemDistricts = new HashSet<SystemDistrict>(0);

    public SystemCity() {
    }

    public SystemCity(int id, Serializable name, boolean show) {
        this.id = id;
        this.name = name;
        this.show = show;
    }

    public SystemCity(int id, Serializable name, boolean show, Serializable description, Integer displayOrder, Serializable groupLocation, Set<SystemDistrict> systemDistricts) {
        this.id = id;
        this.name = name;
        this.show = show;
        this.description = description;
        this.displayOrder = displayOrder;
        this.groupLocation = groupLocation;
        this.systemDistricts = systemDistricts;
    }

    @Id

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

    public void setName(Serializable name) {
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
    public Serializable getDescription() {
        return this.description;
    }

    public void setDescription(Serializable description) {
        this.description = description;
    }

    @Column(name = "DisplayOrder")
    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Column(name = "GroupLocation")
    public Serializable getGroupLocation() {
        return this.groupLocation;
    }

    public void setGroupLocation(Serializable groupLocation) {
        this.groupLocation = groupLocation;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "systemCity")
    public Set<SystemDistrict> getSystemDistricts() {
        return this.systemDistricts;
    }

    public void setSystemDistricts(Set<SystemDistrict> systemDistricts) {
        this.systemDistricts = systemDistricts;
    }

}
