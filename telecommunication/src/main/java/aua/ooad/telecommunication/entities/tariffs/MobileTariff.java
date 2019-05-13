package aua.ooad.telecommunication.entities.tariffs;

import aua.ooad.telecommunication.entities.DirectionPrice;

import java.util.List;

public class MobileTariff extends Tariff{
    private int freeMinutes;
    private int freeInternet;
    private int freeSMS;

    private List<DirectionPrice> directionPrices;

    public void getCallPriceForNumber(){};

    public int getFreeMinutes() {
        return freeMinutes;
    }

    public void setFreeMinutes(int freeMinutes) {
        this.freeMinutes = freeMinutes;
    }

    public int getFreeInternet() {
        return freeInternet;
    }

    public void setFreeInternet(int freeInternet) {
        this.freeInternet = freeInternet;
    }

    public int getFreeSMS() {
        return freeSMS;
    }

    public void setFreeSMS(int freeSMS) {
        this.freeSMS = freeSMS;
    }
}
