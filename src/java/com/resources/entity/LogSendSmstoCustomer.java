package com.resources.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LogSendSMSToCustomer")
public class LogSendSmstoCustomer implements java.io.Serializable {

    private int id;
    private String contentSendToSms;
    private String mobile;
    private Date dateCreated;
    private String returnCodeFromSms;

    public LogSendSmstoCustomer() {
    }

    public LogSendSmstoCustomer(int id) {
        this.id = id;
    }

    public LogSendSmstoCustomer(int id, String contentSendToSms, String mobile, Date dateCreated, String returnCodeFromSms) {
        this.id = id;
        this.contentSendToSms = contentSendToSms;
        this.mobile = mobile;
        this.dateCreated = dateCreated;
        this.returnCodeFromSms = returnCodeFromSms;
    }

    @Id

    @Column(name = "ID", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ContentSendToSMS")
    public String getContentSendToSms() {
        return this.contentSendToSms;
    }

    public void setContentSendToSms(String contentSendToSms) {
        this.contentSendToSms = contentSendToSms;
    }

    @Column(name = "Mobile")
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreated", length = 23)
    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "ReturnCodeFromSMS")
    public String getReturnCodeFromSms() {
        return this.returnCodeFromSms;
    }

    public void setReturnCodeFromSms(String returnCodeFromSms) {
        this.returnCodeFromSms = returnCodeFromSms;
    }

}
