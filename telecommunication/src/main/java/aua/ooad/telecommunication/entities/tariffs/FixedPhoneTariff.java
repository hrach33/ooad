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

    public DirectionPrice getCallPriceForNumber(String number) {
        String extension = number.substring(0, 2);
        for (DirectionPrice dp : directionPrices) {
            if (dp.getOperationType().equals("call") && dp.getToExtension().equals(extension))
                return dp;
        }
        return null;
    }

    public List<DirectionPrice> getDirectionPrices() {
        return directionPrices;
    }

    public void setDirectionPrices(List<DirectionPrice> directionPrices) {
        this.directionPrices = directionPrices;
    }
}
