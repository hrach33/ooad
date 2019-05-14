package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.PhoneNumber;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class NumberCatalog {
    private List<PhoneNumber> phoneNumbers;

    @PostConstruct
    public void init(){
        phoneNumbers = new ArrayList<>();

        PhoneNumber p1 = new PhoneNumber();
        p1.setFree(true);
        p1.setNumber("098111111");
        p1.setType("mobile");
        p1.setPrice(1000);

        phoneNumbers.add(p1);

        PhoneNumber p2 = new PhoneNumber();
        p2.setFree(true);
        p2.setNumber("098222222");
        p2.setType("mobile");
        p2.setPrice(2000);

        phoneNumbers.add(p2);

        PhoneNumber p3 = new PhoneNumber();
        p3.setFree(true);
        p3.setNumber("060333333");
        p3.setType("fixed");
        p3.setPrice(2000);

        phoneNumbers.add(p3);

        PhoneNumber p4 = new PhoneNumber();
        p4.setFree(true);
        p4.setNumber("060444444");
        p4.setType("fixed");
        p4.setPrice(2000);

        phoneNumbers.add(p4);

    }
    public List<PhoneNumber> getMobileNumbers(){
        List<PhoneNumber> res = new ArrayList<>();
        for(PhoneNumber pn: phoneNumbers){
            if(pn.isFree() && pn.getType().equals("mobile"))
                res.add(pn);
        }

        return res;

    }
    public List<PhoneNumber> getFixedNumbers(){
        List<PhoneNumber> res = new ArrayList<>();
        for(PhoneNumber pn: phoneNumbers){
            if(pn.isFree() && pn.getType().equals("fixed"))
                res.add(pn);
        }

        return res;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public PhoneNumber findNumber(String number){
        for(PhoneNumber pn: phoneNumbers){
            if(pn.getNumber().equals(number))
                return pn;
        }
        return null;
    }
}
