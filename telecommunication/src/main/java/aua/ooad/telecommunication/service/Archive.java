package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.Call;
import aua.ooad.telecommunication.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("archive")
public class Archive {
    List<Call> calls;
    List<Call> payments;


    public void addPayment(Payment payment){};
    public void addCall(Call call){};
}
