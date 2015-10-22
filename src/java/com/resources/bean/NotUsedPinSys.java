package com.resources.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NotUsedPinSys implements Serializable{

    @Id
    private int id;
    private String pinNumber;
    private Date createdDate;
    private String createdAdm;
    private String pinType;
    private BigDecimal pricePv;

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

    public String getCreatedAdm() {
        return createdAdm;
    }

    public void setCreatedAdm(String createdAdm) {
        this.createdAdm = createdAdm;
    }

    public String getPinType() {
        return pinType;
    }

    public void setPinType(String pinType) {
        this.pinType = pinType;
    }

    public BigDecimal getPricePv() {
        return pricePv;
    }

    public void setPricePv(BigDecimal pricePv) {
        this.pricePv = pricePv;
    }
}
