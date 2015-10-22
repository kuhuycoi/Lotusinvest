package com.resources.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "System_Country", schema = "dbo", catalog = "lotusinvest_db")
public class SystemCountry implements java.io.Serializable {

    private int id;
    private String code;
    private String name;
    private String description;
    private boolean show;

    public SystemCountry() {
    }

    public SystemCountry(int id, String code, String name, boolean show) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.show = show;
    }

    public SystemCountry(int id, String code, String name, String description, boolean show) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.show = show;
    }

    @Id

    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Code", nullable = false, length = 10)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "Name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "Show", nullable = false)
    public boolean isShow() {
        return this.show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

}
