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
@Table(name = "HistoryAwards")
public class HistoryAwards implements java.io.Serializable {

    private int id;
    private CheckAwards checkAwards;
    private Customer customer;
    private String name;
    private Integer level;
    private Boolean isDeleted;
    private Boolean isShow;
    private Date dateCreated;
    private BigDecimal price;
    private BigDecimal pricePv;
    private Integer cycleNumber;
    private Integer currentCycle;
    private Integer customerRankId;
    private Boolean isPublish;
    private Boolean isRank;
    private BigDecimal pvanow;
    private BigDecimal pvbnow;

    public HistoryAwards() {
    }

    public HistoryAwards(int id) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CheckAwardkId")
    public CheckAwards getCheckAwards() {
        return this.checkAwards;
    }

    public void setCheckAwards(CheckAwards checkAwards) {
        this.checkAwards = checkAwards;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "Name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "[Level]")
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "IsDeleted", insertable = false)
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "IsShow", insertable = false)
    public Boolean getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", length = 23, insertable = false)
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

    @Column(name = "PricePV", precision = 18, scale = 4)
    public BigDecimal getPricePv() {
        return this.pricePv;
    }

    public void setPricePv(BigDecimal pricePv) {
        this.pricePv = pricePv;
    }

    @Column(name = "CycleNumber")
    public Integer getCycleNumber() {
        return this.cycleNumber;
    }

    public void setCycleNumber(Integer cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    @Column(name = "CurrentCycle")
    public Integer getCurrentCycle() {
        return this.currentCycle;
    }

    public void setCurrentCycle(Integer currentCycle) {
        this.currentCycle = currentCycle;
    }

    @Column(name = "CustomerRankId")
    public Integer getCustomerRankId() {
        return this.customerRankId;
    }

    public void setCustomerRankId(Integer customerRankId) {
        this.customerRankId = customerRankId;
    }

    @Column(name = "IsPublish", insertable = false)
    public Boolean getIsPublish() {
        return this.isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

    @Column(name = "IsRank")
    public Boolean getIsRank() {
        return this.isRank;
    }

    public void setIsRank(Boolean isRank) {
        this.isRank = isRank;
    }

    @Column(name = "PVANow", precision = 18, scale = 4)
    public BigDecimal getPvanow() {
        return this.pvanow;
    }

    public void setPvanow(BigDecimal pvanow) {
        this.pvanow = pvanow;
    }

    @Column(name = "PVBNow", precision = 18, scale = 4)
    public BigDecimal getPvbnow() {
        return this.pvbnow;
    }

    public void setPvbnow(BigDecimal pvbnow) {
        this.pvbnow = pvbnow;
    }
}
