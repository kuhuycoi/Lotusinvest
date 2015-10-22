package com.resources.entity;

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

@Entity
@Table(name = "System_Menu")
public class SystemMenu implements java.io.Serializable {

    private int menuId;
    private SystemMenu systemMenu;
    private String menuTitle;
    private String menuLink;
    private boolean menuTaget;
    private boolean menuShow;
    private int menuOrder;
    private String menuDescription;
    private Set<SystemMenu> systemMenus = new HashSet<>(0);

    public SystemMenu() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MenuID", unique = true, nullable = false,insertable = false)
    public int getMenuId() {
        return this.menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MenuParentID", nullable = false)
    public SystemMenu getSystemMenu() {
        return this.systemMenu;
    }

    public void setSystemMenu(SystemMenu systemMenu) {
        this.systemMenu = systemMenu;
    }

    @Column(name = "MenuTitle", nullable = false)
    public String getMenuTitle() {
        return this.menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    @Column(name = "MenuLink")
    public String getMenuLink() {
        return this.menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    @Column(name = "MenuTaget", nullable = false)
    public boolean isMenuTaget() {
        return this.menuTaget;
    }

    public void setMenuTaget(boolean menuTaget) {
        this.menuTaget = menuTaget;
    }

    @Column(name = "MenuShow", nullable = false)
    public boolean isMenuShow() {
        return this.menuShow;
    }

    public void setMenuShow(boolean menuShow) {
        this.menuShow = menuShow;
    }

    @Column(name = "MenuOrder", nullable = false)
    public int getMenuOrder() {
        return this.menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }

    @Column(name = "MenuDescription")
    public String getMenuDescription() {
        return this.menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "systemMenu")
    public Set<SystemMenu> getSystemMenus() {
        return this.systemMenus;
    }

    public void setSystemMenus(Set<SystemMenu> systemMenus) {
        this.systemMenus = systemMenus;
    }

}
