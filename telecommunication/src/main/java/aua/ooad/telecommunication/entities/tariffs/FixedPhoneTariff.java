package aua.ooad.telecommunication.entities.tariffs;

import aua.ooad.telecommunication.entities.DirectionPrice;

import java.util.List;

public class FixedPhoneTariff extends Tariff{
    private int freeMinutes;
    private List<DirectionPrice> directionPrices;

    public int getFreeMinutes() {
        return freeMinutes;
    }

    public void setFreeMinutes(int freeMinutes) {
        this.freeMinutes = freeMinutes;
    }
}
