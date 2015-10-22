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
@Table(name = "RankNow")
public class RankNow implements java.io.Serializable {

    private int id;
    private Customer customer;
    private Integer cycleNumber;
    private String levelId;
    private Integer userId;
    private BigDecimal pricePvuser;
    private Integer userId1;
    private BigDecimal pricePvuser1;
    private Date dateCreated;
    private Integer level;
    private BigDecimal pvuser;
    private BigDecimal pvuser1;
    private Integer activeRank;
    private BigDecimal pvmonth;
    private BigDecimal pvmonth1;
    private Boolean isAward;
    private Integer checkLimit;
    private Boolean isNew;
    private Integer customerRankId;
    private BigDecimal pricePvx;
    private BigDecimal pricePvnow;
    private BigDecimal pvold;
    private BigDecimal pvold1;
    private Boolean isPay;
    private Date dateUpRank;
    private Integer levelAgency;
    private Integer award10;
    private Integer award20;
    private Integer award30;
    private Integer award39;
    private Boolean isPayAward10;
    private Boolean isPayAward20;
    private Boolean isPayAward30;
    private Boolean isPayAward39;
    private Integer award112;
    private Boolean isPayAward112;

    public RankNow() {
    }

    public RankNow(int id) {
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
    @JoinColumn(name = "CustomerID")
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "CycleNumber", insertable = false)
    public Integer getCycleNumber() {
        return this.cycleNumber;
    }

    public void setCycleNumber(Integer cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    @Column(name = "LevelId", insertable = false)
    public String getLevelId() {
        return this.levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    @Column(name = "UserId", insertable = false)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "PricePVUser", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPricePvuser() {
        return this.pricePvuser;
    }

    public void setPricePvuser(BigDecimal pricePvuser) {
        this.pricePvuser = pricePvuser;
    }

    @Column(name = "UserId1", insertable = false)
    public Integer getUserId1() {
        return this.userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    @Column(name = "PricePVUser1", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPricePvuser1() {
        return this.pricePvuser1;
    }

    public void setPricePvuser1(BigDecimal pricePvuser1) {
        this.pricePvuser1 = pricePvuser1;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", length = 23, insertable = false)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "Level", insertable = false)
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "PVUser", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPvuser() {
        return this.pvuser;
    }

    public void setPvuser(BigDecimal pvuser) {
        this.pvuser = pvuser;
    }

    @Column(name = "PVUser1", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPvuser1() {
        return this.pvuser1;
    }

    public void setPvuser1(BigDecimal pvuser1) {
        this.pvuser1 = pvuser1;
    }

    @Column(name = "ActiveRank", insertable = false)
    public Integer getActiveRank() {
        return this.activeRank;
    }

    public void setActiveRank(Integer activeRank) {
        this.activeRank = activeRank;
    }

    @Column(name = "PVMonth", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPvmonth() {
        return this.pvmonth;
    }

    public void setPvmonth(BigDecimal pvmonth) {
        this.pvmonth = pvmonth;
    }

    @Column(name = "PVMonth1", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPvmonth1() {
        return this.pvmonth1;
    }

    public void setPvmonth1(BigDecimal pvmonth1) {
        this.pvmonth1 = pvmonth1;
    }

    @Column(name = "IsAward", insertable = false)
    public Boolean getIsAward() {
        return this.isAward;
    }

    public void setIsAward(Boolean isAward) {
        this.isAward = isAward;
    }

    @Column(name = "CheckLimit", insertable = false)
    public Integer getCheckLimit() {
        return this.checkLimit;
    }

    public void setCheckLimit(Integer checkLimit) {
        this.checkLimit = checkLimit;
    }

    @Column(name = "IsNew")
    public Boolean getIsNew() {
        return this.isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    @Column(name = "CustomerRankId", insertable = false)
    public Integer getCustomerRankId() {
        return this.customerRankId;
    }

    public void setCustomerRankId(Integer customerRankId) {
        this.customerRankId = customerRankId;
    }

    @Column(name = "PricePVX", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPricePvx() {
        return this.pricePvx;
    }

    public void setPricePvx(BigDecimal pricePvx) {
        this.pricePvx = pricePvx;
    }

    @Column(name = "PricePVNow", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPricePvnow() {
        return this.pricePvnow;
    }

    public void setPricePvnow(BigDecimal pricePvnow) {
        this.pricePvnow = pricePvnow;
    }

    @Column(name = "PVOld", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPvold() {
        return this.pvold;
    }

    public void setPvold(BigDecimal pvold) {
        this.pvold = pvold;
    }

    @Column(name = "PVOld1", precision = 18, scale = 4, insertable = false)
    public BigDecimal getPvold1() {
        return this.pvold1;
    }

    public void setPvold1(BigDecimal pvold1) {
        this.pvold1 = pvold1;
    }

    @Column(name = "IsPay", insertable = false)
    public Boolean getIsPay() {
        return this.isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateUpRank", length = 23, insertable = false)
    public Date getDateUpRank() {
        return this.dateUpRank;
    }

    public void setDateUpRank(Date dateUpRank) {
        this.dateUpRank = dateUpRank;
    }

    @Column(name = "LevelAgency", insertable = false)
    public Integer getLevelAgency() {
        return this.levelAgency;
    }

    public void setLevelAgency(Integer levelAgency) {
        this.levelAgency = levelAgency;
    }

    @Column(name = "Award10", insertable = false)
    public Integer getAward10() {
        return this.award10;
    }

    public void setAward10(Integer award10) {
        this.award10 = award10;
    }

    @Column(name = "Award20", insertable = false)
    public Integer getAward20() {
        return this.award20;
    }

    public void setAward20(Integer award20) {
        this.award20 = award20;
    }

    @Column(name = "Award30", insertable = false)
    public Integer getAward30() {
        return this.award30;
    }

    public void setAward30(Integer award30) {
        this.award30 = award30;
    }

    @Column(name = "Award39", insertable = false)
    public Integer getAward39() {
        return this.award39;
    }

    public void setAward39(Integer award39) {
        this.award39 = award39;
    }

    @Column(name = "IsPayAward10", insertable = false)
    public Boolean getIsPayAward10() {
        return this.isPayAward10;
    }

    public void setIsPayAward10(Boolean isPayAward10) {
        this.isPayAward10 = isPayAward10;
    }

    @Column(name = "IsPayAward20", insertable = false)
    public Boolean getIsPayAward20() {
        return this.isPayAward20;
    }

    public void setIsPayAward20(Boolean isPayAward20) {
        this.isPayAward20 = isPayAward20;
    }

    @Column(name = "IsPayAward30", insertable = false)
    public Boolean getIsPayAward30() {
        return this.isPayAward30;
    }

    public void setIsPayAward30(Boolean isPayAward30) {
        this.isPayAward30 = isPayAward30;
    }

    @Column(name = "IsPayAward39", insertable = false)
    public Boolean getIsPayAward39() {
        return this.isPayAward39;
    }

    public void setIsPayAward39(Boolean isPayAward39) {
        this.isPayAward39 = isPayAward39;
    }

    @Column(name = "Award112", insertable = false)
    public Integer getAward112() {
        return this.award112;
    }

    public void setAward112(Integer award112) {
        this.award112 = award112;
    }

    @Column(name = "IsPayAward112", insertable = false)
    public Boolean getIsPayAward112() {
        return this.isPayAward112;
    }

    public void setIsPayAward112(Boolean isPayAward112) {
        this.isPayAward112 = isPayAward112;
    }

}
