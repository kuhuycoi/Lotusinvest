package com.resources.entity;

import java.math.BigDecimal;
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
@Table(name = "Customer_RankCustomer")
public class CustomerRankCustomer implements java.io.Serializable {

    private int id;
    private Customer customer;
    private ProvincialAgencies provincialAgencies;
    private RankCustomes rankCustomes;
    private Date dateCreated;
    private BigDecimal price;
    private Boolean isDeleted;
    private Boolean isShow;
    private Boolean isCustomerType;
    private BigDecimal pricePv;
    private String code;
    private Integer typeCustomer;
    private String listCustomerId;
    private Date dateEnd;

    public CustomerRankCustomer() {
    }

    public CustomerRankCustomer(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, insertable = false, updatable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProvincialAgencyID")
    public ProvincialAgencies getProvincialAgencies() {
        return this.provincialAgencies;
    }

    public void setProvincialAgencies(ProvincialAgencies provincialAgencies) {
        this.provincialAgencies = provincialAgencies;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RankCustomerId")
    public RankCustomes getRankCustomes() {
        return this.rankCustomes;
    }

    public void setRankCustomes(RankCustomes rankCustomes) {
        this.rankCustomes = rankCustomes;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", length = 23)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    @Column(name = "IsCustomerType")
    public Boolean getIsCustomerType() {
        return this.isCustomerType;
    }

    public void setIsCustomerType(Boolean isCustomerType) {
        this.isCustomerType = isCustomerType;
    }

    @Column(name = "PricePV", precision = 18, scale = 4)
    public BigDecimal getPricePv() {
        return this.pricePv;
    }

    public void setPricePv(BigDecimal pricePv) {
        this.pricePv = pricePv;
    }

    @Column(name = "Code")
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "TypeCustomer")
    public Integer getTypeCustomer() {
        return this.typeCustomer;
    }

    public void setTypeCustomer(Integer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    @Column(name = "ListCustomerId")
    public String getListCustomerId() {
        return this.listCustomerId;
    }

    public void setListCustomerId(String listCustomerId) {
        this.listCustomerId = listCustomerId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateEnd", length = 23)
    public Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
