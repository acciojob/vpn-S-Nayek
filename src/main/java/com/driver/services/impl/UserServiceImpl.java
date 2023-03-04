package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;


    public User register(String username, String password, String countryName) throws Exception{
        User user=new User();
        user.setUserName(username);
        user.setPassword(password);


        Country country = new Country();
        String name = countryName.toUpperCase();
        country.setCountryName(CountryName.valueOf(countryName));
        country.setCode(CountryName.valueOf(countryName).toCode());
        country.setUser(user);

        user.setCountry(country);
        user.setCountry(country);
        user.setOriginalIp(country.getCode()+"."+user.getId());

        userRepository3.save(user);

        return user;

    }


    public User subscribe(Integer userId, Integer serviceProviderId) {
        User user = userRepository3.findById(userId).get();

        ServiceProvider serviceProvider = serviceProviderRepository3.findById(serviceProviderId).get();

        List<ServiceProvider> serviceProviderList = user.getServiceProviders();
        serviceProviderList.add(serviceProvider);
        user.setServiceProviders(serviceProviderList);

        List<User> userList = serviceProvider.getUsers();
        userList.add(user);
        serviceProvider.setUsers(userList);

        userRepository3.save(user);

        return user;
    }
}
