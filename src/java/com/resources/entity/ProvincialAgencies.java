package com.resources.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ProvincialAgencies")
public class ProvincialAgencies implements java.io.Serializable {

    private int id;
    private String name;
    private String address;
    private String userName;
    private Boolean isDeleted;
    private Boolean isShow;
    private Date dateCreated;
    private String mobile;
    private String fax;
    private String email;
    private Integer orderBy;
    private String accountNumber;
    private String informationAccount;
    private String code;
    private Integer customerId;
    private Set<TransactionHistories> transactionHistorieses = new HashSet<>(0);
    private Set<Customer> customers = new HashSet<>(0);
    private Set<CustomerRankCustomer> customerRankCustomers = new HashSet<>(0);

    public ProvincialAgencies() {
    }

    public ProvincialAgencies(int id) {
        this.id = id;
    }

    @Id
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

    @Column(name = "Address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "UserName")
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", length = 23)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "Mobile")
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "Fax", length = 32)
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Column(name = "Email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "OrderBy")
    public Integer getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    @Column(name = "AccountNumber")
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "InformationAccount")
    public String getInformationAccount() {
        return this.informationAccount;
    }

    public void setInformationAccount(String informationAccount) {
        this.informationAccount = informationAccount;
    }

    @Column(name = "Code", length = 5)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "CustomerId")
    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provincialAgencies")
    public Set<TransactionHistories> getTransactionHistorieses() {
        return this.transactionHistorieses;
    }

    public void setTransactionHistorieses(Set<TransactionHistories> transactionHistorieses) {
        this.transactionHistorieses = transactionHistorieses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provincialAgencies")
    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provincialAgencies")
    public Set<CustomerRankCustomer> getCustomerRankCustomers() {
        return this.customerRankCustomers;
    }

    public void setCustomerRankCustomers(Set<CustomerRankCustomer> customerRankCustomers) {
        this.customerRankCustomers = customerRankCustomers;
    }

}
