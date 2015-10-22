package com.resources.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "triandot2")
public class Triandot2 implements java.io.Serializable {

    private int vitri;
    private Customer customer;
    private Integer dem;
    private Integer levelup;
    private Date datetimecreated;
    private Integer check1;
    private Integer check2;
    private Integer check3;

    public Triandot2() {
    }

    public Triandot2(int vitri) {
        this.vitri = vitri;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vitri", unique = true, nullable = false)
    public int getVitri() {
        return this.vitri;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }

    @Column(name = "dem")
    public Integer getDem() {
        return this.dem;
    }

    public void setDem(Integer dem) {
        this.dem = dem;
    }

    @Column(name = "levelup")
    public Integer getLevelup() {
        return this.levelup;
    }

    public void setLevelup(Integer levelup) {
        this.levelup = levelup;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datetimecreated", length = 23)
    public Date getDatetimecreated() {
        return this.datetimecreated;
    }

    public void setDatetimecreated(Date datetimecreated) {
        this.datetimecreated = datetimecreated;
    }

    @Column(name = "check1")
    public Integer getCheck1() {
        return this.check1;
    }

    public void setCheck1(Integer check1) {
        this.check1 = check1;
    }

    @Column(name = "check2")
    public Integer getCheck2() {
        return this.check2;
    }

    public void setCheck2(Integer check2) {
        this.check2 = check2;
    }

    @Column(name = "check3")
    public Integer getCheck3() {
        return this.check3;
    }

    public void setCheck3(Integer check3) {
        this.check3 = check3;
    }

}
