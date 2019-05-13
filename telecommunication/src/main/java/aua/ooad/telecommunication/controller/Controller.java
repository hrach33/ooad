package aua.ooad.telecommunication.controller;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.service.Archive;
import aua.ooad.telecommunication.service.MobileService;
import aua.ooad.telecommunication.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest")
public class Controller {

    private List<Call> calls;
    private List<Customer> customers;

    @Autowired
    private Archive archive;

    @Autowired
    private MobileService mobileService;

    @Autowired
    private PackageService packageService;


    @PostConstruct
    public void init(){
        customers = new ArrayList<>();
    }
    private static class CreateCustomerRequest{
        public String firstName;
        public String lastName;
        public String email;
        public String phoneNumber;
        public String passport;
        public String address;
    }

    @PostMapping("/createCustomer")
    public String createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                createCustomerRequest.firstName,
                createCustomerRequest.lastName,
                createCustomerRequest.email,
                createCustomerRequest.phoneNumber,
                createCustomerRequest.passport,
                createCustomerRequest.address);
        customers.add(customer);

        return customer.getId();
    }
}
