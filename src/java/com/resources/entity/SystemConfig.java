package com.resources.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "System_Config")
public class SystemConfig implements java.io.Serializable {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String phoneAdvice;
    private String fax;
    private String email;
    private String businessLicence;
    private String phoneAdvice1;
    private String phoneAdvice2;
    private Boolean isShow;
    private String email1;
    private String email2;
    private Date dateCreate;
    private String googleMap;

    public SystemConfig() {
    }

    public SystemConfig(int id) {
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

    @Column(name = "Address")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "Phone")
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "PhoneAdvice")
    public String getPhoneAdvice() {
        return this.phoneAdvice;
    }

    public void setPhoneAdvice(String phoneAdvice) {
        this.phoneAdvice = phoneAdvice;
    }

    @Column(name = "Fax")
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

    @Column(name = "BusinessLicence")
    public String getBusinessLicence() {
        return this.businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    @Column(name = "PhoneAdvice1")
    public String getPhoneAdvice1() {
        return this.phoneAdvice1;
    }

    public void setPhoneAdvice1(String phoneAdvice1) {
        this.phoneAdvice1 = phoneAdvice1;
    }

    @Column(name = "PhoneAdvice2")
    public String getPhoneAdvice2() {
        return this.phoneAdvice2;
    }

    public void setPhoneAdvice2(String phoneAdvice2) {
        this.phoneAdvice2 = phoneAdvice2;
    }

    @Column(name = "IsShow")
    public Boolean getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    @Column(name = "Email1")
    public String getEmail1() {
        return this.email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    @Column(name = "Email2")
    public String getEmail2() {
        return this.email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreate", length = 23)
    public Date getDateCreate() {
        return this.dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column(name = "GoogleMap")
    public String getGoogleMap() {
        return this.googleMap;
    }

    public void setGoogleMap(String googleMap) {
        this.googleMap = googleMap;
    }

}
