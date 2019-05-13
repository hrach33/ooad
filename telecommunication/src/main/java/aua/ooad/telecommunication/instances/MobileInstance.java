package aua.ooad.telecommunication.instances;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Customer;
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
        return 0.0;
    }

    public void chargeForTalkedMinutes(Call call){

    }

    public void increaseBalance(double amount){
        this.balance +=amount;
    }

    public void chargeForInternet(int amountOfMB){}

    public void chargeForSMS(int toNumber){}




}
