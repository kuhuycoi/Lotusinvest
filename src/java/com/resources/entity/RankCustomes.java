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
@Table(name = "RankCustomes")
public class RankCustomes implements java.io.Serializable {

    private int id;
    private String name;
    private Integer orderBy;
    private String description;
    private Boolean isDeleted;
    private Boolean isShow;
    private Integer percent;
    private String color;
    private Integer time;
    private BigDecimal price;
    private BigDecimal pricePv;
    private Integer type;
    private Set<CustomerRankCustomer> customerRankCustomers = new HashSet<>(0);

    public RankCustomes() {
    }

    public RankCustomes(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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

    @Column(name = "OrderBy")
    public Integer getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @Column(name = "Description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Column(name = "[Percent]")
    public Integer getPercent() {
        return this.percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    @Column(name = "Color", length = 50)
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "Time")
    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Column(name = "Price", precision = 18, scale = 4)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "PricePV", precision = 18, scale = 4)
    public BigDecimal getPricePv() {
        return this.pricePv;
    }

    public void setPricePv(BigDecimal pricePv) {
        this.pricePv = pricePv;
    }

    @Column(name = "Type")
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rankCustomes")
    public Set<CustomerRankCustomer> getCustomerRankCustomers() {
        return this.customerRankCustomers;
    }

    public void setCustomerRankCustomers(Set<CustomerRankCustomer> customerRankCustomers) {
        this.customerRankCustomers = customerRankCustomers;
    }

}
