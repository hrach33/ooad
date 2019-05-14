package aua.ooad.telecommunication.instances;

import aua.ooad.telecommunication.entities.*;
import aua.ooad.telecommunication.entities.Package;

import java.util.Date;
import java.util.UUID;

public class PackageInstance {

    private String id;
    private String registrationAddress;
    private Date activeFrom;
    private Date activeTo;
    private double balance;
    private int freeFixedMinutes;
    private boolean isActive;

    private Package packagee;
    private Customer customer;
    private PhoneNumber number;

    public PackageInstance(Package p, String registrationAddress, Customer customer) {
        id = UUID.randomUUID().toString();
        this.registrationAddress = registrationAddress;
        this.customer = customer;
        packagee = p;
        activeFrom = new Date(System.currentTimeMillis());
        activeTo = new Date(System.currentTimeMillis() + 30*24*60*60*1000);
        balance = 0;
        isActive = false;
        freeFixedMinutes = 0;

        customer.addPackageInstance(this);

    }

    public boolean checkIfHasFixedTariff(){
        return packagee.getFixedPhoneTariff() != null;
    }

    public double getTotalPrice(){

        double total = packagee.getPrice();
        if(number != null)
            total+= number.getPrice();
        return total;
    }

    public void chargeForTalkedMinutes(Call call){
        double duration = call.getDuration();
        String toNumber = call.getToNumber();
        double remainder = 0;
        if(freeFixedMinutes > 0 && freeFixedMinutes >= duration){
            freeFixedMinutes-= duration;
        } else if(freeFixedMinutes > 0 && freeFixedMinutes < duration){
            remainder = duration - freeFixedMinutes;
            freeFixedMinutes = 0;
        }

        DirectionPrice dp = packagee.getFixedPhoneTariff().getCallPriceForNumber(toNumber);

        addToBalance(dp.getPricePerUnit() * remainder);

    }

    public void addToBalance(double amount){
        balance+= amount;
    }

    public PhoneNumber getNumber() {
        return number;
    }

    public void setNumber(PhoneNumber number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(Date activeTo) {
        this.activeTo = activeTo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getFreeFixedMinutes() {
        return freeFixedMinutes;
    }

    public void setFreeFixedMinutes(int freeFixedMinutes) {
        this.freeFixedMinutes = freeFixedMinutes;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Package getPackagee() {
        return packagee;
    }

    public void setPackagee(Package packagee) {
        this.packagee = packagee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
