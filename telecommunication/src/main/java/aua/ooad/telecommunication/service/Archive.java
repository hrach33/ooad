package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Payment;
import aua.ooad.telecommunication.instances.MobileInstance;
import aua.ooad.telecommunication.instances.PackageInstance;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service("archive")
public class Archive {
    List<Call> calls;
    List<Payment> payments;


    @PostConstruct
    public void init(){
        calls = new ArrayList<>();
        payments = new ArrayList<>();
    }
    public void addPayment(MobileInstance mi){
        Payment p = new Payment();
        p.setServiceId(mi.getId());
        p.setAmount(mi.getTotalPrice());
        p.setServiceType("mobile");
        p.setTimestamp(new Timestamp(System.currentTimeMillis()));
        p.setCustomerId(mi.getCustomer().getId());

        payments.add(p);
    }

    public void addPayment(MobileInstance mi, double amount){
        Payment p = new Payment();
        p.setServiceId(mi.getId());
        p.setAmount(amount);
        p.setServiceType("mobile");
        p.setTimestamp(new Timestamp(System.currentTimeMillis()));
        p.setCustomerId(mi.getCustomer().getId());

        payments.add(p);
    }

    public void addPayment(PackageInstance pi){
        Payment p = new Payment();
        p.setServiceId(pi.getId());
        p.setAmount(pi.getTotalPrice());
        p.setServiceType("package");
        p.setTimestamp(new Timestamp(System.currentTimeMillis()));
        p.setCustomerId(pi.getCustomer().getId());

        payments.add(p);
    }

    public void addPayment(PackageInstance pi, double amount){
        Payment p = new Payment();
        p.setServiceId(pi.getId());
        p.setAmount(amount);
        p.setServiceType("package");
        p.setTimestamp(new Timestamp(System.currentTimeMillis()));
        p.setCustomerId(pi.getCustomer().getId());

        payments.add(p);
    }
    public void addCall(Call call){
        calls.add(call);
    }


    public List<Payment> getPayments(String customerId){
        List<Payment> res = new ArrayList<>();
        for(Payment p : payments){
            if(p.getCustomerId().equals(customerId))
                res.add(p);
        }
        return res;
    }

    public List<Call> getCalls(String customerId){
        List<Call> res = new ArrayList<>();
        for(Call c : calls){
            if(c.getCustomerId().equals(customerId))
                res.add(c);
        }
        return res;
    }
}
