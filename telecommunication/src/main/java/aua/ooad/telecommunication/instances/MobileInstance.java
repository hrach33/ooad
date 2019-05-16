package aua.ooad.telecommunication.instances;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.entities.DirectionPrice;
import aua.ooad.telecommunication.entities.PhoneNumber;
import aua.ooad.telecommunication.entities.tariffs.MobileTariff;

import java.util.Date;
import java.util.UUID;

public class MobileInstance {
    private String id;
    private int freeMinutes;
    private int freeInternet;
    private int freeSMS;
    private Date activeFrom;
    private Date activeTo;
    private double balance;
    private boolean isActive;

    private MobileTariff tariff;
    private PhoneNumber number;
    private Customer customer;

    public MobileInstance() {
    }

    public MobileInstance(MobileInstance mi){
        this.id = mi.id;
        this.freeInternet = mi.freeInternet;
        this.freeMinutes = mi.freeMinutes;
        this.freeSMS = mi.freeSMS;
        this.activeFrom = mi.activeFrom;
        this.activeTo = mi.activeTo;
        this.balance = mi.balance;
        this.isActive = mi.isActive;
        this.tariff = mi.tariff;
        this.number = mi.number;
    }
    public MobileInstance(MobileTariff mt, Customer customer) {
        id = UUID.randomUUID().toString();
        freeMinutes = mt.getFreeMinutes();
        freeInternet = mt.getFreeInternet();
        freeSMS = mt.getFreeSMS();
        activeFrom = new Date(System.currentTimeMillis());
        activeTo = new Date(System.currentTimeMillis() + 30 *24*60*60*1000);
        balance = 0;
        isActive = false;
        tariff = mt;
        this.customer = customer;
        customer.addMobileInstance(this);
    }

    public double getTotalPrice(){
        return tariff.getPrice() + number.getPrice();
    }

    public void chargeForTalkedMinutes(Call call){
        double duration = call.getDuration();
        String toNumber = call.getToNumber();
        double remainder = 0;
        if(freeMinutes > 0 && freeMinutes >= duration){
            freeMinutes-= duration;
        } else if(freeMinutes > 0 && freeMinutes < duration){
            remainder = duration - freeMinutes;
            freeMinutes = 0;
        } else{
            remainder = duration;
        }

        DirectionPrice dp = tariff.getCallPriceForNumber(toNumber);

        if(remainder >0)
            addToBalance(-dp.getPricePerUnit() * remainder);

    }

    public void addToBalance(double amount){
        this.balance +=amount;
    }

    public void chargeForInternet(double amountOfMB){

        double remainder = 0;
        if(freeInternet > 0 && freeInternet >= amountOfMB){
            freeInternet-= amountOfMB;
        } else if(freeInternet > 0 && freeInternet < amountOfMB){
            remainder = amountOfMB - freeInternet;
            freeInternet = 0;
        } else {
            remainder = amountOfMB;
        }

        if(remainder >0) {
            double total =  tariff.getInternetPrice() * remainder;
            addToBalance(-total);
        }
    }

    public void chargeForSMS(String toNumber){
        if(freeSMS > 0){
            freeSMS--;
        } else {
            DirectionPrice dp = tariff.getSmsPriceForNumber(toNumber);
            addToBalance(-dp.getPricePerUnit());
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFreeMinutes() {
        return freeMinutes;
    }

    public void setFreeMinutes(int freeMinutes) {
        this.freeMinutes = freeMinutes;
    }

    public int getFreeInternet() {
        return freeInternet;
    }

    public void setFreeInternet(int freeInternet) {
        this.freeInternet = freeInternet;
    }

    public int getFreeSMS() {
        return freeSMS;
    }

    public void setFreeSMS(int freeSMS) {
        this.freeSMS = freeSMS;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public MobileTariff getTariff() {
        return tariff;
    }

    public void setTariff(MobileTariff tariff) {
        this.tariff = tariff;
    }

    public PhoneNumber getNumber() {
        return number;
    }

    public void setNumber(PhoneNumber number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
