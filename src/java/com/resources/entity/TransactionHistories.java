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
@Table(name = "TransactionHistories")
public class TransactionHistories implements java.io.Serializable {

    private int id;
    private Customer customer;
    private DynamicCustomers dynamicCustomers;
    private ProvincialAgencies provincialAgencies;
    private String name;
    private Boolean isDeleted;
    private Boolean isShow;
    private String customerName;
    private BigDecimal price;
    private String description;
    private Date dateCreated;
    private BigDecimal pricePv;
    private String code;
    private Date dateEnd;

    public TransactionHistories() {
    }

    public TransactionHistories(int id) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DynamicId")
    public DynamicCustomers getDynamicCustomers() {
        return this.dynamicCustomers;
    }

    public void setDynamicCustomers(DynamicCustomers dynamicCustomers) {
        this.dynamicCustomers = dynamicCustomers;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProvincialAgencyID")
    public ProvincialAgencies getProvincialAgencies() {
        return this.provincialAgencies;
    }

    public void setProvincialAgencies(ProvincialAgencies provincialAgencies) {
        this.provincialAgencies = provincialAgencies;
    }

    @Column(name = "Name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Column(name = "CustomerName")
    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "Price", precision = 18, scale = 4)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "Description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", length = 23)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateEnd", length = 23)
    public Date getDateEnd() {
        return this.dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
