package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.Customer;
import aua.ooad.telecommunication.entities.DirectionPrice;
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


        List<DirectionPrice> directionPrices = new ArrayList<>();
        DirectionPrice dp1 = new DirectionPrice("098", 5, "call");
        DirectionPrice dp2 = new DirectionPrice("055", 10, "call");
        DirectionPrice dp3 = new DirectionPrice("060", 15, "call");
        DirectionPrice dp4 = new DirectionPrice("010", 15, "call");
        DirectionPrice dp5 = new DirectionPrice(null, 15, "internet");

        directionPrices.add(dp1);
        directionPrices.add(dp2);
        directionPrices.add(dp3);
        directionPrices.add(dp4);
        directionPrices.add(dp5);

        MobileTariff t1 = new MobileTariff();
        t1.setId(1);
        t1.setName("Tariff1");
        t1.setFreeInternet(1000);
        t1.setFreeMinutes(100);
        t1.setFreeSMS(10);
        t1.setPrice(1000);
        t1.setDirectionPrices(directionPrices);

        mobileTariffs.add(t1);

        MobileTariff t2 = new MobileTariff();
        t2.setId(2);
        t2.setName("Tariff2");
        t2.setFreeInternet(2000);
        t2.setFreeMinutes(1);
        t2.setFreeSMS(20);
        t2.setPrice(2000);
        t2.setDirectionPrices(directionPrices);

        mobileTariffs.add(t2);


        mobileInstances = new ArrayList<>();
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

    public void save(MobileInstance mi) {
        mi.setActive(true);
        mobileInstances.add(mi);
    }

    public MobileInstance getMobileInstance(String number) {
        for (MobileInstance mobileInstance: mobileInstances){
            if(mobileInstance.getNumber().getNumber().equals(number))
                return mobileInstance;
        }
        return null;
    }

    public MobileInstance findMobileInstance(String id) {
        for(MobileInstance mi: mobileInstances){
            if(mi.getId().equals(id))
                return mi;
        }
        return null;
    }

    public void useInternet(String number, double amountOfMb) {
        MobileInstance mi = getMobileInstance(number);
        mi.chargeForInternet(amountOfMb);
    }

    public void useSMS() {
    }

    public PhoneNumber getPhoneNumber(String number){
        PhoneNumber res = numberCatalog.findNumber(number);
        return res.getType().equals("mobile") ? res : null;
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
