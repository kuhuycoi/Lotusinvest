package com.resources.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "System_MessageTemplate")
public class SystemMessageTemplate implements java.io.Serializable {

    private int id;
    private String name;
    private String subject;
    private String bodyContent;
    private String bccEmail;
    private Boolean isActive;
    private Integer emailAccountId;
    private Boolean isDeleted;

    public SystemMessageTemplate() {
    }

    public SystemMessageTemplate(int id) {
        this.id = id;
    }

    public SystemMessageTemplate(int id, String name, String subject, String bodyContent, String bccEmail, Boolean isActive, Integer emailAccountId, Boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.bodyContent = bodyContent;
        this.bccEmail = bccEmail;
        this.isActive = isActive;
        this.emailAccountId = emailAccountId;
        this.isDeleted = isDeleted;
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

    @Column(name = "Subject")
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "BodyContent")
    public String getBodyContent() {
        return this.bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    @Column(name = "BccEmail")
    public String getBccEmail() {
        return this.bccEmail;
    }

    public void setBccEmail(String bccEmail) {
        this.bccEmail = bccEmail;
    }

    @Column(name = "IsActive")
    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Column(name = "EmailAccountId")
    public Integer getEmailAccountId() {
        return this.emailAccountId;
    }

    public void setEmailAccountId(Integer emailAccountId) {
        this.emailAccountId = emailAccountId;
    }

    @Column(name = "IsDeleted")
    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
