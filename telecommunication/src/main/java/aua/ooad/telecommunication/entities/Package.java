package aua.ooad.telecommunication.entities;

import aua.ooad.telecommunication.entities.tariffs.FixedPhoneTariff;
import aua.ooad.telecommunication.entities.tariffs.InternetTariff;
import aua.ooad.telecommunication.entities.tariffs.TVTariff;

public class Package {
    private int id;
    private String name;
    private double price;
    private String description;

    private TVTariff tvTariff;
    private FixedPhoneTariff fixedPhoneTariff;
    private InternetTariff internetTariff;

    public boolean checkIfHasFixedTariff(){
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TVTariff getTvTariff() {
        return tvTariff;
    }

    public void setTvTariff(TVTariff tvTariff) {
        this.tvTariff = tvTariff;
    }

    public FixedPhoneTariff getFixedPhoneTariff() {
        return fixedPhoneTariff;
    }

    public void setFixedPhoneTariff(FixedPhoneTariff fixedPhoneTariff) {
        this.fixedPhoneTariff = fixedPhoneTariff;
    }

    public InternetTariff getInternetTariff() {
        return internetTariff;
    }

    public void setInternetTariff(InternetTariff internetTariff) {
        this.internetTariff = internetTariff;
    }
}
