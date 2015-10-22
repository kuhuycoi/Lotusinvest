package com.resources.entity;

import com.resources.bean.CustomerFromTotalParent;
import com.resources.bean.CustomerTree;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@Table(name = "Customer")
@NamedNativeQueries({
    @NamedNativeQuery(
            name = "TreeCustomer",
            query = "{CALL TreeCustomer(:first_id,:max_level)}",
            resultClass = CustomerTree.class
    ),
    @NamedNativeQuery(
            name = "TotalParent",
            query = "{CALL TotalParent(:listCustomerId)}",
            resultClass = CustomerFromTotalParent.class
    )
})
public class Customer implements java.io.Serializable {

    private Integer id;
    private Customer customerByParentId;
    private Customer customerByCustomerId;
    private CustomerType customerType;
    private ProvincialAgencies provincialAgencies;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Boolean gender;
    private Date birthday;
    private String peoplesIdentity;
    private String password;
    private String passwordSalt;
    private String mobile;
    private String address;
    private Boolean isAddress;
    private String addressCompany;
    private String lastIpAddress;
    private String macAddress;
    private Date createdOnUtc;
    private Date lastLoginDateUtc;
    private Date lastActivityDateUtc;
    private String nameCompany;
    private String taxCode;
    private String billingAddress;
    private String shippingAddress;
    private Integer districtId;
    private Integer cityId;
    private Integer countryId;
    private Boolean isActive;
    private Boolean isDelete;
    private Boolean isReceiveEmail;
    private Boolean isReceiveSms;
    private Integer cityCompanyId;
    private Integer deviceCompanyId;
    private Integer districtCompanyId;
    private Integer sourceId;
    private Boolean isAward;
    private String customerIdcrm;
    private Boolean isAdmin;
    private Boolean isPublish;
    private String listCustomerId;
    private String title;
    private Integer level;
    private Boolean isIndex;
    private Integer type;
    private Integer identityType;
    private Set<CustomerRankCustomer> customerRankCustomers = new HashSet<>(0);
    private Set<HistoryAwards> historyAwardses = new HashSet<>(0);
    private Set<TransactionHistories> transactionHistorieses = new HashSet<>(0);
    private Set<RankNow> rankNows = new HashSet<>(0);
    private Set<Customer> customersForParentId = new HashSet<>(0);
    private Set<Customer> customersForCustomerId = new HashSet<>(0);
    private Set<Triandot2> triandot2s = new HashSet<>(0);

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ParentID", updatable = false)
    public Customer getCustomerByParentId() {
        return this.customerByParentId;
    }

    public void setCustomerByParentId(Customer customerByParentId) {
        this.customerByParentId = customerByParentId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID", updatable = false)
    public Customer getCustomerByCustomerId() {
        return this.customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerTypeID")
    public CustomerType getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProvincialAgencyID", updatable = false)
    public ProvincialAgencies getProvincialAgencies() {
        return this.provincialAgencies;
    }

    public void setProvincialAgencies(ProvincialAgencies provincialAgencies) {
        this.provincialAgencies = provincialAgencies;
    }

    @Column(name = "FirstName", updatable = false)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LastName", updatable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "UserName", updatable = false)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "Email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "Gender")
    public Boolean getGender() {
        return this.gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Birthday", length = 23, updatable = false)
    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "PeoplesIdentity", length = 50)
    public String getPeoplesIdentity() {
        return this.peoplesIdentity;
    }

    public void setPeoplesIdentity(String peoplesIdentity) {
        this.peoplesIdentity = peoplesIdentity;
    }

    @Column(name = "Password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "PasswordSalt", updatable = false)
    public String getPasswordSalt() {
        return this.passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Column(name = "Mobile")
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "Address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "IsAddress", updatable = false)
    public Boolean getIsAddress() {
        return this.isAddress;
    }

    public void setIsAddress(Boolean isAddress) {
        this.isAddress = isAddress;
    }

    @Column(name = "AddressCompany", updatable = false)
    public String getAddressCompany() {
        return this.addressCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    @Column(name = "LastIpAddress", updatable = false)
    public String getLastIpAddress() {
        return this.lastIpAddress;
    }

    public void setLastIpAddress(String lastIpAddress) {
        this.lastIpAddress = lastIpAddress;
    }

    @Column(name = "MacAddress", updatable = false)
    public String getMacAddress() {
        return this.macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedOnUtc", length = 23, insertable = false, updatable = false)
    public Date getCreatedOnUtc() {
        return this.createdOnUtc;
    }

    public void setCreatedOnUtc(Date createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastLoginDateUtc", length = 23, updatable = false)
    public Date getLastLoginDateUtc() {
        return this.lastLoginDateUtc;
    }

    public void setLastLoginDateUtc(Date lastLoginDateUtc) {
        this.lastLoginDateUtc = lastLoginDateUtc;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastActivityDateUtc", length = 23, updatable = false)
    public Date getLastActivityDateUtc() {
        return this.lastActivityDateUtc;
    }

    public void setLastActivityDateUtc(Date lastActivityDateUtc) {
        this.lastActivityDateUtc = lastActivityDateUtc;
    }

    @Column(name = "NameCompany", updatable = false)
    public String getNameCompany() {
        return this.nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    @Column(name = "TaxCode", length = 128)
    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Column(name = "BillingAddress")
    public String getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Column(name = "ShippingAddress", updatable = false)
    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Column(name = "DistrictID", updatable = false)
    public Integer getDistrictId() {
        return this.districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Column(name = "CityID", updatable = false)
    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Column(name = "CountryID", updatable = false)
    public Integer getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Column(name = "IsActive", insertable = false, updatable = false)
    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Column(name = "IsDelete", insertable = false)
    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "IsReceiveEmail", updatable = false)
    public Boolean getIsReceiveEmail() {
        return this.isReceiveEmail;
    }

    public void setIsReceiveEmail(Boolean isReceiveEmail) {
        this.isReceiveEmail = isReceiveEmail;
    }

    @Column(name = "IsReceiveSms", updatable = false)
    public Boolean getIsReceiveSms() {
        return this.isReceiveSms;
    }

    public void setIsReceiveSms(Boolean isReceiveSms) {
        this.isReceiveSms = isReceiveSms;
    }

    @Column(name = "CityCompanyID", updatable = false)
    public Integer getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(Integer cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    @Column(name = "DeviceCompanyID", updatable = false)
    public Integer getDeviceCompanyId() {
        return this.deviceCompanyId;
    }

    public void setDeviceCompanyId(Integer deviceCompanyId) {
        this.deviceCompanyId = deviceCompanyId;
    }

    @Column(name = "DistrictCompanyID", updatable = false)
    public Integer getDistrictCompanyId() {
        return this.districtCompanyId;
    }

    public void setDistrictCompanyId(Integer districtCompanyId) {
        this.districtCompanyId = districtCompanyId;
    }

    @Column(name = "SourceID", updatable = false)
    public Integer getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    @Column(name = "IsAward", updatable = false)
    public Boolean getIsAward() {
        return this.isAward;
    }

    public void setIsAward(Boolean isAward) {
        this.isAward = isAward;
    }

    @Column(name = "CustomerIDCRM", updatable = false)
    public String getCustomerIdcrm() {
        return this.customerIdcrm;
    }

    public void setCustomerIdcrm(String customerIdcrm) {
        this.customerIdcrm = customerIdcrm;
    }

    @Column(name = "IsAdmin", updatable = false)
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Column(name = "IsPublish", insertable = false, updatable = false)
    public Boolean getIsPublish() {
        return this.isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

    @Column(name = "ListCustomerId", updatable = false)
    public String getListCustomerId() {
        return this.listCustomerId;
    }

    public void setListCustomerId(String listCustomerId) {
        this.listCustomerId = listCustomerId;
    }

    @Column(name = "Title", updatable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "Level", updatable = false)
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "IsIndex", updatable = false)
    public Boolean getIsIndex() {
        return this.isIndex;
    }

    public void setIsIndex(Boolean isIndex) {
        this.isIndex = isIndex;
    }

    @Column(name = "Type", updatable = false)
    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "IdentityType", updatable = false)
    public Integer getIdentityType() {
        return this.identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<CustomerRankCustomer> getCustomerRankCustomers() {
        return this.customerRankCustomers;
    }

    public void setCustomerRankCustomers(Set<CustomerRankCustomer> customerRankCustomers) {
        this.customerRankCustomers = customerRankCustomers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<HistoryAwards> getHistoryAwardses() {
        return this.historyAwardses;
    }

    public void setHistoryAwardses(Set<HistoryAwards> historyAwardses) {
        this.historyAwardses = historyAwardses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<TransactionHistories> getTransactionHistorieses() {
        return this.transactionHistorieses;
    }

    public void setTransactionHistorieses(Set<TransactionHistories> transactionHistorieses) {
        this.transactionHistorieses = transactionHistorieses;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<RankNow> getRankNows() {
        return this.rankNows;
    }

    public void setRankNows(Set<RankNow> rankNows) {
        this.rankNows = rankNows;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByParentId")
    public Set<Customer> getCustomersForParentId() {
        return this.customersForParentId;
    }

    public void setCustomersForParentId(Set<Customer> customersForParentId) {
        this.customersForParentId = customersForParentId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
    public Set<Customer> getCustomersForCustomerId() {
        return this.customersForCustomerId;
    }

    public void setCustomersForCustomerId(Set<Customer> customersForCustomerId) {
        this.customersForCustomerId = customersForCustomerId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<Triandot2> getTriandot2s() {
        return this.triandot2s;
    }

    public void setTriandot2s(Set<Triandot2> triandot2s) {
        this.triandot2s = triandot2s;
    }
}
