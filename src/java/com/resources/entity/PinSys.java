package com.resources.entity;

import java.io.Serializable;
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
@Table(name = "PinSys")
public class PinSys implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "PinNumber", updatable = false)
    private String pinNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate", length = 23, insertable = false, updatable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedAdm", updatable = false)
    private Admin createdAdm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PinType", updatable = false)
    private RankCustomes pinType;

    @Column(name = "isDeleted", insertable = false)
    private Boolean isDeleted;

    @Column(name = "isUsed", insertable = false)
    private Boolean isUsed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsedCustomer", insertable = false)
    private Customer usedCustomer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UsedDate", length = 23, insertable = false)
    private Date usedDate;

    public PinSys() {
    }

    public PinSys(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Admin getCreatedAdm() {
        return createdAdm;
    }

    public void setCreatedAdm(Admin createdAdm) {
        this.createdAdm = createdAdm;
    }

    public RankCustomes getPinType() {
        return pinType;
    }

    public void setPinType(RankCustomes pinType) {
        this.pinType = pinType;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Customer getUsedCustomer() {
        return usedCustomer;
    }

    public void setUsedCustomer(Customer usedCustomer) {
        this.usedCustomer = usedCustomer;
    }

    public Date getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(Date usedDate) {
        this.usedDate = usedDate;
    }
}
