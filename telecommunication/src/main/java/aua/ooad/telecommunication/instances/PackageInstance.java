package aua.ooad.telecommunication.instances;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.entities.PhoneNumber;

import java.util.Date;

public class PackageInstance {

    private int id;
    private String registrationAddress;
    private Date activeFrom;
    private Date activeTo;
    private double balance;
    private int freeFixedMinutes;
    private boolean isActive;

    private Package packagee;
    private Customer customer;
    private PhoneNumber number;

    public boolean checkIfHasFixedTariff(){
        return true;
    }

    public double getTotalPrice(){
        return 0;
    }

    public void chargeForTalkedMinutes(Call call){

    }

    public void increaseBalance(double amount){

    }

}
