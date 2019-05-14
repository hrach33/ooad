package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.*;
import aua.ooad.telecommunication.entities.Package;
import aua.ooad.telecommunication.entities.tariffs.FixedPhoneTariff;
import aua.ooad.telecommunication.entities.tariffs.InternetTariff;
import aua.ooad.telecommunication.entities.tariffs.TVTariff;
import aua.ooad.telecommunication.instances.PackageInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PackageService {
    private List<PackageInstance> packageInstances;
    private List<Package> packages;

    @Autowired
    private NumberCatalog numberCatalog;


    @PostConstruct
    public void init(){

        packages = new ArrayList<>();
        packageInstances = new ArrayList<>();

        Package p1 = new Package();
        p1.setId(1);
        p1.setName("Package 1");
        p1.setPrice(10000);

        TVTariff tvTariff = new TVTariff();
        tvTariff.setId(20);
        tvTariff.setHasCatchUp(true);
        tvTariff.setName("TvTariff1");
        tvTariff.setPrice(5000);

        List<Channel> channels = new ArrayList<>();
        Channel ch1 = new Channel("ch1", 20, 200);
        Channel ch2 = new Channel("ch2", 10, 200);
        channels.add(ch1);
        channels.add(ch2);
        tvTariff.setChannels(channels);

        p1.setTvTariff(tvTariff);

        FixedPhoneTariff fixedPhoneTariff = new FixedPhoneTariff();
        fixedPhoneTariff.setId(21);
        fixedPhoneTariff.setFreeMinutes(100);
        fixedPhoneTariff.setName("FixedPhone1");
        fixedPhoneTariff.setPrice(1000);

        List<DirectionPrice> directionPrices = new ArrayList<>();
        DirectionPrice dp1 = new DirectionPrice("098", 5, "call");
        DirectionPrice dp2 = new DirectionPrice("055", 10, "call");
        DirectionPrice dp3 = new DirectionPrice("060", 15, "call");
        DirectionPrice dp4 = new DirectionPrice("010", 15, "call");
        DirectionPrice dp5 = new DirectionPrice(null, 15, "internet");

        fixedPhoneTariff.setDirectionPrices(directionPrices);

        p1.setFixedPhoneTariff(fixedPhoneTariff);

        InternetTariff internetTariff = new InternetTariff();
        internetTariff.setId(22);
        internetTariff.setSpeed(30);
        internetTariff.setName("Internet1");
        internetTariff.setPrice(6000);

        p1.setInternetTariff(internetTariff);

        packages.add(p1);
    }

    public PackageInstance buyPackage(int packageId, String registrationAddress, Customer customer){
        Package p = null;
        for (Package packagee : packages) {
            if (packagee.getId() == packageId) {
                p = packagee;
                break;
            }
        }

        PackageInstance pi = new PackageInstance(p, registrationAddress, customer);
        return pi;
    }

    public List<PhoneNumber> getFixedNumbers(){
        return numberCatalog.getFixedNumbers();
    }


    public PackageInstance getPackageInstance(String number){
        for(PackageInstance packageInstance: packageInstances){
            if(packageInstance.getNumber() != null && packageInstance.getNumber().getNumber().equals(number))
                return packageInstance;
        }
        return null;
    }

    public PackageInstance findPackageInstance(String id) {
        for(PackageInstance pi: packageInstances){
            if(pi.getId().equals(id))
                return pi;
        }
        return null;
    }

    public PhoneNumber getPhoneNumber(String number){
        PhoneNumber res = numberCatalog.findNumber(number);
        return res.getType().equals("fixed") ? res : null;
    }
    public List<PackageInstance> getPackageInstances() {
        return packageInstances;
    }

    public void setPackageInstances(List<PackageInstance> packageInstances) {
        this.packageInstances = packageInstances;
    }

    public List<Package> getPackages() {
        return packages;
    }
    public void save(PackageInstance pi) {
        pi.setActive(true);
        packageInstances.add(pi);
    }
    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }


}
