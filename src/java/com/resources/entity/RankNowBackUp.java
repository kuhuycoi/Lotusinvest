package com.resources.entity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RankNow_BackUp")
public class RankNowBackUp implements java.io.Serializable {

    private int id;
    private Integer customerId;
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
    private Boolean isPay10;
    private Boolean isPay20;
    private Boolean isPay30;
    private Boolean isPay39;
    private Integer award112;
    private Integer isPayAward112;

    public RankNowBackUp() {
    }

    public RankNowBackUp(int id) {
        this.id = id;
    }

    public RankNowBackUp(int id, Integer customerId, Integer cycleNumber, String levelId, Integer userId, BigDecimal pricePvuser, Integer userId1, BigDecimal pricePvuser1, Date dateCreated, Integer level, BigDecimal pvuser, BigDecimal pvuser1, Integer activeRank, BigDecimal pvmonth, BigDecimal pvmonth1, Boolean isAward, Integer checkLimit, Boolean isNew, Integer customerRankId, BigDecimal pricePvx, BigDecimal pricePvnow, BigDecimal pvold, BigDecimal pvold1, Boolean isPay, Date dateUpRank, Integer levelAgency, Integer award10, Integer award20, Integer award30, Integer award39, Boolean isPay10, Boolean isPay20, Boolean isPay30, Boolean isPay39, Integer award112, Integer isPayAward112) {
        this.id = id;
        this.customerId = customerId;
        this.cycleNumber = cycleNumber;
        this.levelId = levelId;
        this.userId = userId;
        this.pricePvuser = pricePvuser;
        this.userId1 = userId1;
        this.pricePvuser1 = pricePvuser1;
        this.dateCreated = dateCreated;
        this.level = level;
        this.pvuser = pvuser;
        this.pvuser1 = pvuser1;
        this.activeRank = activeRank;
        this.pvmonth = pvmonth;
        this.pvmonth1 = pvmonth1;
        this.isAward = isAward;
        this.checkLimit = checkLimit;
        this.isNew = isNew;
        this.customerRankId = customerRankId;
        this.pricePvx = pricePvx;
        this.pricePvnow = pricePvnow;
        this.pvold = pvold;
        this.pvold1 = pvold1;
        this.isPay = isPay;
        this.dateUpRank = dateUpRank;
        this.levelAgency = levelAgency;
        this.award10 = award10;
        this.award20 = award20;
        this.award30 = award30;
        this.award39 = award39;
        this.isPay10 = isPay10;
        this.isPay20 = isPay20;
        this.isPay30 = isPay30;
        this.isPay39 = isPay39;
        this.award112 = award112;
        this.isPayAward112 = isPayAward112;
    }

    @Id

    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CustomerID")
    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "CycleNumber")
    public Integer getCycleNumber() {
        return this.cycleNumber;
    }

    public void setCycleNumber(Integer cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    @Column(name = "LevelId")
    public String getLevelId() {
        return this.levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    @Column(name = "UserId")
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "PricePVUser", precision = 18, scale = 4)
    public BigDecimal getPricePvuser() {
        return this.pricePvuser;
    }

    public void setPricePvuser(BigDecimal pricePvuser) {
        this.pricePvuser = pricePvuser;
    }

    @Column(name = "UserId1")
    public Integer getUserId1() {
        return this.userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    @Column(name = "PricePVUser1", precision = 18, scale = 4)
    public BigDecimal getPricePvuser1() {
        return this.pricePvuser1;
    }

    public void setPricePvuser1(BigDecimal pricePvuser1) {
        this.pricePvuser1 = pricePvuser1;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", length = 23)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "Level")
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "PVUser", precision = 18, scale = 4)
    public BigDecimal getPvuser() {
        return this.pvuser;
    }

    public void setPvuser(BigDecimal pvuser) {
        this.pvuser = pvuser;
    }

    @Column(name = "PVUser1", precision = 18, scale = 4)
    public BigDecimal getPvuser1() {
        return this.pvuser1;
    }

    public void setPvuser1(BigDecimal pvuser1) {
        this.pvuser1 = pvuser1;
    }

    @Column(name = "ActiveRank")
    public Integer getActiveRank() {
        return this.activeRank;
    }

    public void setActiveRank(Integer activeRank) {
        this.activeRank = activeRank;
    }

    @Column(name = "PVMonth", precision = 18, scale = 4)
    public BigDecimal getPvmonth() {
        return this.pvmonth;
    }

    public void setPvmonth(BigDecimal pvmonth) {
        this.pvmonth = pvmonth;
    }

    @Column(name = "PVMonth1", precision = 18, scale = 4)
    public BigDecimal getPvmonth1() {
        return this.pvmonth1;
    }

    public void setPvmonth1(BigDecimal pvmonth1) {
        this.pvmonth1 = pvmonth1;
    }

    @Column(name = "IsAward")
    public Boolean getIsAward() {
        return this.isAward;
    }

    public void setIsAward(Boolean isAward) {
        this.isAward = isAward;
    }

    @Column(name = "CheckLimit")
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

    @Column(name = "CustomerRankId")
    public Integer getCustomerRankId() {
        return this.customerRankId;
    }

    public void setCustomerRankId(Integer customerRankId) {
        this.customerRankId = customerRankId;
    }

    @Column(name = "PricePVX", precision = 18, scale = 4)
    public BigDecimal getPricePvx() {
        return this.pricePvx;
    }

    public void setPricePvx(BigDecimal pricePvx) {
        this.pricePvx = pricePvx;
    }

    @Column(name = "PricePVNow", precision = 18, scale = 4)
    public BigDecimal getPricePvnow() {
        return this.pricePvnow;
    }

    public void setPricePvnow(BigDecimal pricePvnow) {
        this.pricePvnow = pricePvnow;
    }

    @Column(name = "PVOld", precision = 18, scale = 4)
    public BigDecimal getPvold() {
        return this.pvold;
    }

    public void setPvold(BigDecimal pvold) {
        this.pvold = pvold;
    }

    @Column(name = "PVOld1", precision = 18, scale = 4)
    public BigDecimal getPvold1() {
        return this.pvold1;
    }

    public void setPvold1(BigDecimal pvold1) {
        this.pvold1 = pvold1;
    }

    @Column(name = "IsPay")
    public Boolean getIsPay() {
        return this.isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateUpRank", length = 23)
    public Date getDateUpRank() {
        return this.dateUpRank;
    }

    public void setDateUpRank(Date dateUpRank) {
        this.dateUpRank = dateUpRank;
    }

    @Column(name = "LevelAgency")
    public Integer getLevelAgency() {
        return this.levelAgency;
    }

    public void setLevelAgency(Integer levelAgency) {
        this.levelAgency = levelAgency;
    }

    @Column(name = "Award10")
    public Integer getAward10() {
        return this.award10;
    }

    public void setAward10(Integer award10) {
        this.award10 = award10;
    }

    @Column(name = "Award20")
    public Integer getAward20() {
        return this.award20;
    }

    public void setAward20(Integer award20) {
        this.award20 = award20;
    }

    @Column(name = "Award30")
    public Integer getAward30() {
        return this.award30;
    }

    public void setAward30(Integer award30) {
        this.award30 = award30;
    }

    @Column(name = "Award39")
    public Integer getAward39() {
        return this.award39;
    }

    public void setAward39(Integer award39) {
        this.award39 = award39;
    }

    @Column(name = "IsPay10")
    public Boolean getIsPay10() {
        return this.isPay10;
    }

    public void setIsPay10(Boolean isPay10) {
        this.isPay10 = isPay10;
    }

    @Column(name = "IsPay20")
    public Boolean getIsPay20() {
        return this.isPay20;
    }

    public void setIsPay20(Boolean isPay20) {
        this.isPay20 = isPay20;
    }

    @Column(name = "IsPay30")
    public Boolean getIsPay30() {
        return this.isPay30;
    }

    public void setIsPay30(Boolean isPay30) {
        this.isPay30 = isPay30;
    }

    @Column(name = "IsPay39")
    public Boolean getIsPay39() {
        return this.isPay39;
    }

    public void setIsPay39(Boolean isPay39) {
        this.isPay39 = isPay39;
    }

    @Column(name = "Award112")
    public Integer getAward112() {
        return this.award112;
    }

    public void setAward112(Integer award112) {
        this.award112 = award112;
    }

    @Column(name = "IsPayAward112")
    public Integer getIsPayAward112() {
        return this.isPayAward112;
    }

    public void setIsPayAward112(Integer isPayAward112) {
        this.isPayAward112 = isPayAward112;
    }

}
