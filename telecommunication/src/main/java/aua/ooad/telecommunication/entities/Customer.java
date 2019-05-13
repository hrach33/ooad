package aua.ooad.telecommunication.entities;

import aua.ooad.telecommunication.instances.MobileInstance;
import aua.ooad.telecommunication.instances.PackageInstance;

import java.util.List;

public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passport;
    private String address;

    private List<MobileInstance> mobileInstances;
    private List<PackageInstance> packageInstances;

    public Customer(String id, String firstName, String lastName, String email, String phoneNumber, String passport, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passport = passport;
        this.address = address;
    }

    public void addMobileInstance(){};
    public void addPackageInstance(){};


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
