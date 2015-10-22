package com.resources.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HistoryCustomerRank implements Serializable{
    @Id
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String rankName;
    private BigDecimal price;
    private BigDecimal pricePv;
    private Integer provincialAgencyId;
    private String provincialAgencyName;
    private Date dateCreated;

    public HistoryCustomerRank() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPricePv() {
        return pricePv;
    }

    public void setPricePv(BigDecimal pricePv) {
        this.pricePv = pricePv;
    }

    public String getProvincialAgencyName() {
        return provincialAgencyName;
    }

    public void setProvincialAgencyName(String provincialAgencyName) {
        this.provincialAgencyName = provincialAgencyName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getProvincialAgencyId() {
        return provincialAgencyId;
    }

    public void setProvincialAgencyId(Integer provincialAgencyId) {
        this.provincialAgencyId = provincialAgencyId;
    }    

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }    
}
