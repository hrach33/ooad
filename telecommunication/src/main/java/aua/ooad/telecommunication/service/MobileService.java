package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.PhoneNumber;
import aua.ooad.telecommunication.entities.tariffs.MobileTariff;
import aua.ooad.telecommunication.instances.MobileInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileService {
    private List<MobileInstance> mobileInstances;
    private List<MobileTariff> mobileTariffs;

    @Autowired
    private NumberCatalog numberCatalog;

    public void getMobileNumbers(){};
    public void buyMobileTariff(){};
    public void save(MobileInstance mobileInstance){};
    public void getMobileInstance(int number){};
    public void findMobileInstance(int id){};
    public void useInterenet(){};
    public void useSMS(){};


    public List<MobileInstance> getMobileInstances() {
        return mobileInstances;
    }

    public List<MobileTariff> getMobileTariffs() {
        return mobileTariffs;
    }

    public void setMobileTariffs(List<MobileTariff> mobileTariffs) {
        this.mobileTariffs = mobileTariffs;
    }


    public void setMobileInstances(List<MobileInstance> mobileInstances) {
        this.mobileInstances = mobileInstances;
    }
}
