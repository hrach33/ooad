package aua.ooad.telecommunication.entities;

import aua.ooad.telecommunication.instances.MobileInstance;
import aua.ooad.telecommunication.instances.PackageInstance;

import java.util.ArrayList;
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

    public Customer(){
        mobileInstances = new ArrayList<>();
        packageInstances = new ArrayList<>();
    }
    public Customer(String id, String firstName, String lastName, String email, String phoneNumber, String passport, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passport = passport;
        this.address = address;

        mobileInstances = new ArrayList<>();
        packageInstances = new ArrayList<>();
    }

    public void addMobileInstance(MobileInstance mi){
        mobileInstances.add(mi);
    }

    public void addPackageInstance(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MobileInstance> getMobileInstances() {
        return mobileInstances;
    }

    public void setMobileInstances(List<MobileInstance> mobileInstances) {
        this.mobileInstances = mobileInstances;
    }

    public List<PackageInstance> getPackageInstances() {
        return packageInstances;
    }

    public void setPackageInstances(List<PackageInstance> packageInstances) {
        this.packageInstances = packageInstances;
    }
}
