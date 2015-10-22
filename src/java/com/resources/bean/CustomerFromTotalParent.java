package com.resources.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerFromTotalParent implements Serializable {

    @Id
    private Integer ID;
    private String UserName;
    private Boolean IsPublish;
    private Integer CustomerID;
    private Integer ParentID;
    private String ListCustomerId;
    private Integer Level;
    private Integer Clevel;
    private Integer rnUserId;
    private BigDecimal rnPricePVUser;
    private BigDecimal rnPVUser;
    private Integer rnUserId1;
    private BigDecimal rnPricePVUser1;
    private BigDecimal rnPVUser1;
    private String LevelId;
    private Integer ActiveRank;
    private Integer Award10;
    private Integer Award20;
    private Integer Award30;
    private Integer Award39;
    private BigDecimal PVMonth;
    private BigDecimal PVMonth1;
    private Integer CircleNumber;
    private Boolean IsPay;
    private Date DateEnd;
    private Integer Circle;
    private Integer CircleOld;
    private BigDecimal PVANow;
    private BigDecimal PVBNow;
    private Integer CurrentCircle;

    public CustomerFromTotalParent() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public Boolean getIsPublish() {
        return IsPublish;
    }

    public void setIsPublish(Boolean IsPublish) {
        this.IsPublish = IsPublish;
    }

    public Integer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Integer CustomerID) {
        this.CustomerID = CustomerID;
    }

    public Integer getParentID() {
        return ParentID;
    }

    public void setParentID(Integer ParentID) {
        this.ParentID = ParentID;
    }

    public String getListCustomerId() {
        return ListCustomerId;
    }

    public void setListCustomerId(String ListCustomerId) {
        this.ListCustomerId = ListCustomerId;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer Level) {
        this.Level = Level;
    }

    public Integer getClevel() {
        return Clevel;
    }

    public void setClevel(Integer Clevel) {
        this.Clevel = Clevel;
    }

    public Integer getRnUserId() {
        return rnUserId;
    }

    public void setRnUserId(Integer rnUserId) {
        this.rnUserId = rnUserId;
    }

    public BigDecimal getRnPricePVUser() {
        return rnPricePVUser;
    }

    public void setRnPricePVUser(BigDecimal rnPricePVUser) {
        this.rnPricePVUser = rnPricePVUser;
    }

    public BigDecimal getRnPVUser() {
        return rnPVUser;
    }

    public void setRnPVUser(BigDecimal rnPVUser) {
        this.rnPVUser = rnPVUser;
    }

    public Integer getRnUserId1() {
        return rnUserId1;
    }

    public void setRnUserId1(Integer rnUserId1) {
        this.rnUserId1 = rnUserId1;
    }

    public BigDecimal getRnPricePVUser1() {
        return rnPricePVUser1;
    }

    public void setRnPricePVUser1(BigDecimal rnPricePVUser1) {
        this.rnPricePVUser1 = rnPricePVUser1;
    }

    public BigDecimal getRnPVUser1() {
        return rnPVUser1;
    }

    public void setRnPVUser1(BigDecimal rnPVUser1) {
        this.rnPVUser1 = rnPVUser1;
    }

    public String getLevelId() {
        return LevelId;
    }

    public void setLevelId(String LevelId) {
        this.LevelId = LevelId;
    }

    public Integer getActiveRank() {
        return ActiveRank;
    }

    public void setActiveRank(Integer ActiveRank) {
        this.ActiveRank = ActiveRank;
    }

    public Integer getAward10() {
        return Award10;
    }

    public void setAward10(Integer Award10) {
        this.Award10 = Award10;
    }

    public Integer getAward20() {
        return Award20;
    }

    public void setAward20(Integer Award20) {
        this.Award20 = Award20;
    }

    public Integer getAward30() {
        return Award30;
    }

    public void setAward30(Integer Award30) {
        this.Award30 = Award30;
    }

    public Integer getAward39() {
        return Award39;
    }

    public void setAward39(Integer Award39) {
        this.Award39 = Award39;
    }

    public BigDecimal getPVMonth() {
        return PVMonth;
    }

    public void setPVMonth(BigDecimal PVMonth) {
        this.PVMonth = PVMonth;
    }

    public BigDecimal getPVMonth1() {
        return PVMonth1;
    }

    public void setPVMonth1(BigDecimal PVMonth1) {
        this.PVMonth1 = PVMonth1;
    }

    public Integer getCircleNumber() {
        return CircleNumber;
    }

    public void setCircleNumber(Integer CircleNumber) {
        this.CircleNumber = CircleNumber;
    }

    public Boolean getIsPay() {
        return IsPay;
    }

    public void setIsPay(Boolean IsPay) {
        this.IsPay = IsPay;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date DateEnd) {
        this.DateEnd = DateEnd;
    }

    public Integer getCircle() {
        return Circle;
    }

    public void setCircle(Integer Circle) {
        this.Circle = Circle;
    }

    public Integer getCircleOld() {
        return CircleOld;
    }

    public void setCircleOld(Integer CircleOld) {
        this.CircleOld = CircleOld;
    }

    public BigDecimal getPVANow() {
        return PVANow;
    }

    public void setPVANow(BigDecimal PVANow) {
        this.PVANow = PVANow;
    }

    public BigDecimal getPVBNow() {
        return PVBNow;
    }

    public void setPVBNow(BigDecimal PVBNow) {
        this.PVBNow = PVBNow;
    }

    public Integer getCurrentCircle() {
        return CurrentCircle;
    }

    public void setCurrentCircle(Integer CurrentCircle) {
        this.CurrentCircle = CurrentCircle;
    }
    
}
