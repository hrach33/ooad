package aua.ooad.telecommunication.service;

import aua.ooad.telecommunication.entities.Package;
import aua.ooad.telecommunication.instances.PackageInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    private List<PackageInstance> packageInstances;
    private List<Package> packages;

    @Autowired
    private NumberCatalog numberCatalog;

    public void buyPackage(){};
    public void getFixedNumbers(){};
    public void addToPackageInstanceCatalog(){};
    public void getPackageInstance(int number){};
    public void findPackageInstance(int id){};

    public List<PackageInstance> getPackageInstances() {
        return packageInstances;
    }

    public void setPackageInstances(List<PackageInstance> packageInstances) {
        this.packageInstances = packageInstances;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

}
