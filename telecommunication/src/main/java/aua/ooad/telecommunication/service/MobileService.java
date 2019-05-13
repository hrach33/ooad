package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.entities.PhoneNumber;
import aua.ooad.telecommunication.entities.tariffs.MobileTariff;
import aua.ooad.telecommunication.instances.MobileInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MobileService {
    private List<MobileInstance> mobileInstances;
    private List<MobileTariff> mobileTariffs;

    @Autowired
    private NumberCatalog numberCatalog;

    @PostConstruct
    public void init() {
        mobileTariffs = new ArrayList<>();

        MobileTariff t1 = new MobileTariff();
        t1.setId(1);
        t1.setName("Tariff1");
        t1.setFreeInternet(1000);
        t1.setFreeMinutes(100);
        t1.setFreeSMS(10);
        t1.setPrice(1000);

        mobileTariffs.add(t1);

        MobileTariff t2 = new MobileTariff();
        t2.setId(2);
        t2.setName("Tariff2");
        t2.setFreeInternet(2000);
        t2.setFreeMinutes(200);
        t2.setFreeSMS(20);
        t2.setPrice(2000);

        mobileTariffs.add(t2);
    }

    public List<PhoneNumber> getMobileNumbers() {
        return numberCatalog.getMobileNumbers();
    }

    public MobileInstance buyMobileTariff(int tariffId, Customer customer) {
        MobileTariff mt = null;
        for (MobileTariff mobileTariff : mobileTariffs) {
            if (mobileTariff.getId() == tariffId) {
                mt = mobileTariff;
                break;
            }
        }

        MobileInstance mi = new MobileInstance(mt, customer);
        return mi;


    }

    public void save(MobileInstance mobileInstance) {
    }

    public void getMobileInstance(int number) {
    }

    public void findMobileInstance(int id) {
    }

    public void useInterenet() {
    }

    public void useSMS() {
    }


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
