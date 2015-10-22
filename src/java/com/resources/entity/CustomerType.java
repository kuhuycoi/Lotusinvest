package com.resources.entity;
// Generated Aug 25, 2015 2:06:03 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Customer_Type")
public class CustomerType implements java.io.Serializable {

    private int id;
    private String name;
    private Boolean isDelete;
    private Set<Customer> customers = new HashSet<>(0);

    public CustomerType() {
    }

    public CustomerType(int id) {
        this.id = id;
    }

    @Id
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

    @Column(name = "IsDelete")
    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerType")
    public Set<Customer> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

}
