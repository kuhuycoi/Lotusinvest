package com.resources.bean;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HistoryAward implements java.io.Serializable {

    @Id
    private Integer id;
    private String name;
    private String userName;
    private Integer cusId;
    private String firstName;
    private String lastName;
    private BigDecimal price;
    private BigDecimal pricePv;
    private Date dateCreated;

    public HistoryAward() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getPricePv() {
        return pricePv;
    }

    public void setPricePv(BigDecimal pricePv) {
        this.pricePv = pricePv;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
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
}
