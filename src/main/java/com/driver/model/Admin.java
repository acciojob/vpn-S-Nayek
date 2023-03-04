package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String passWord;

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
     private List<ServiceProvider> serviceProviderList =new ArrayList<>();

    public Admin() {
    }

    public Admin(int id, String userName, String passWord, List<ServiceProvider> serviceProviderList) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.serviceProviderList = serviceProviderList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }
}
