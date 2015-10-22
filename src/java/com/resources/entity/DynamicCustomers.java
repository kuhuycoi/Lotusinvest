package com.resources.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DynamicCustomers")
public class DynamicCustomers implements java.io.Serializable {

    private int id;
    private String name;
    private BigDecimal price;
    private Boolean isDeleted;
    private Boolean isShow;
    private String description;
    private BigDecimal pricePv;
    private Integer month;
    private Set<TransactionHistories> transactionHistorieses = new HashSet<>(0);

    public DynamicCustomers() {
    }

    public DynamicCustomers(int id) {
        this.id = id;
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

    @Column(name = "Name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Price", precision = 18, scale = 4)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "IsDeleted")
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "IsShow")
    public Boolean getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    @Column(name = "Description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "PricePV", precision = 18, scale = 4)
    public BigDecimal getPricePv() {
        return this.pricePv;
    }

    public void setPricePv(BigDecimal pricePv) {
        this.pricePv = pricePv;
    }

    @Column(name = "Month")
    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dynamicCustomers")
    public Set<TransactionHistories> getTransactionHistorieses() {
        return this.transactionHistorieses;
    }

    public void setTransactionHistorieses(Set<TransactionHistories> transactionHistorieses) {
        this.transactionHistorieses = transactionHistorieses;
    }

}
