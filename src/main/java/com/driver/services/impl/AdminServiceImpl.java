package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;


    public Admin register(String username, String password) {
        Admin admin =new Admin();
        admin.setUserName(username);
        admin.setPassWord(password);
        adminRepository1.save(admin);
        return admin;
    }


    public Admin addServiceProvider(int adminId, String providerName) {
        Admin admin = adminRepository1.findById(adminId).get();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(providerName);
        serviceProvider.setAdmin(admin);

        List<ServiceProvider> serviceProviderList = admin.getServiceProviderList();
        serviceProviderList.add(serviceProvider);
        admin.setServiceProviderList(serviceProviderList);

        adminRepository1.save(admin);

        return admin;
    }


    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
        try {
            ServiceProvider serviceProvider = serviceProviderRepository1.findById(serviceProviderId).get();

            Country country = new Country();
            String name = countryName.toUpperCase();
            country.setCountryName(CountryName.valueOf(countryName));
            country.setCode(CountryName.valueOf(countryName).toCode());
            country.setServiceProvider(serviceProvider);

            List<Country> countryList = serviceProvider.getCountryList();
            countryList.add(country);
            serviceProvider.setCountryList(countryList);

            serviceProviderRepository1.save(serviceProvider);

            return serviceProvider;
        }
        catch (Exception e){
            throw  new Exception("Country not found");
        }
    }
}
