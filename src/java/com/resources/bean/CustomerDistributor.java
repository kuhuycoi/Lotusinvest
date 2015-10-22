package com.resources.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerDistributor implements Serializable {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private Integer parentId;
    private String parentName;
    private String customerId;
    private String customerName;
    private String rankNow;
    private Integer level;
    private String listCustomerId;
    private Boolean gender;
    private String mobile;
    private String email;
    private Integer provincialAgencyId;
    private String provincialAgencyName;
    private String title;
    private String customerTypeName;
    private Integer customerTypeId;
    private Boolean isActive;
    private Date createdOnUtc;

    public CustomerDistributor(Integer id) {
        this.id = id;
    }

    public CustomerDistributor() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRankNow() {
        return rankNow;
    }

    public void setRankNow(String rankNow) {
        this.rankNow = rankNow;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getListCustomerId() {
        return listCustomerId;
    }

    public void setListCustomerId(String listCustomerId) {
        this.listCustomerId = listCustomerId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getProvincialAgencyId() {
        return provincialAgencyId;
    }

    public void setProvincialAgencyId(Integer provincialAgencyId) {
        this.provincialAgencyId = provincialAgencyId;
    }

    public String getProvincialAgencyName() {
        return provincialAgencyName;
    }

    public void setProvincialAgencyName(String provincialAgencyName) {
        this.provincialAgencyName = provincialAgencyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }    

    public Date getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(Date createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }
}
