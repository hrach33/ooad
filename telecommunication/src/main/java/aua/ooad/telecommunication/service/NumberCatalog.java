package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberCatalog {
    private List<PhoneNumber> phoneNumbers;

    public void getMobileNumbers(){};
    public void getFixedNumbers(){};

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
