package com.resources.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "trian")
public class Trian implements java.io.Serializable {

    private int id;
    private String username;
    private String peoplesIdentity;
    private Integer parentId;
    private Integer total;
    private Integer leveltree;
    private Date datecreated;

    public Trian() {
    }

    public Trian(int id) {
        this.id = id;
    }

    public Trian(int id, String username, String peoplesIdentity, Integer parentId, Integer total, Integer leveltree, Date datecreated) {
        this.id = id;
        this.username = username;
        this.peoplesIdentity = peoplesIdentity;
        this.parentId = parentId;
        this.total = total;
        this.leveltree = leveltree;
        this.datecreated = datecreated;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PeoplesIdentity")
    public String getPeoplesIdentity() {
        return this.peoplesIdentity;
    }

    public void setPeoplesIdentity(String peoplesIdentity) {
        this.peoplesIdentity = peoplesIdentity;
    }

    @Column(name = "parentID")
    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "total")
    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Column(name = "leveltree")
    public Integer getLeveltree() {
        return this.leveltree;
    }

    public void setLeveltree(Integer leveltree) {
        this.leveltree = leveltree;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datecreated", length = 23)
    public Date getDatecreated() {
        return this.datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

}
