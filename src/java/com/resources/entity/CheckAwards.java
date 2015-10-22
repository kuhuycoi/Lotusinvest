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
@Table(name = "CheckAwards")
public class CheckAwards implements java.io.Serializable {

    private int id;
    private String name;
    private Integer time;
    private Boolean isDeleted;
    private BigDecimal price;
    private Boolean isShow;
    private Integer percent;
    private BigDecimal pricePv;
    private Integer type;
    private Boolean isDay;
    private Set<HistoryAwards> historyAwardses = new HashSet<>(0);

    public CheckAwards() {
    }

    public CheckAwards(int id) {
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

    @Column(name = "Time")
    public Integer getTime() {
        return this.time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Column(name = "IsDeleted")
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "Price", precision = 18, scale = 4)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    @Column(name = "IsDay")
    public Boolean getIsDay() {
        return this.isDay;
    }

    public void setIsDay(Boolean isDay) {
        this.isDay = isDay;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "checkAwards")
    public Set<HistoryAwards> getHistoryAwardses() {
        return this.historyAwardses;
    }

    public void setHistoryAwardses(Set<HistoryAwards> historyAwardses) {
        this.historyAwardses = historyAwardses;
    }
}
