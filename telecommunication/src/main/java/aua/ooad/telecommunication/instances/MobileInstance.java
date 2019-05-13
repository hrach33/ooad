package aua.ooad.telecommunication.instances;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.entities.PhoneNumber;
import aua.ooad.telecommunication.entities.tariffs.MobileTariff;

import java.util.Date;

public class MobileInstance {
    private int id;
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

    public double getTotalPrice(){
        return 0.0;
    }

    public void chargeForTalkedMinutes(Call call){

    };

    public void increaseBalance(double amount){
        this.balance +=amount;
    }

    public void chargeForInternet(int amountOfMB){}

    public void chargeForSMS(int toNumber){}




}
