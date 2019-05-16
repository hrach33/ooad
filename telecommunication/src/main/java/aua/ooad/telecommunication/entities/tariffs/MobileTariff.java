package aua.ooad.telecommunication.entities.tariffs;

import aua.ooad.telecommunication.entities.DirectionPrice;

import java.util.List;

public class MobileTariff extends Tariff {
    private int freeMinutes;
    private int freeInternet;
    private int freeSMS;

    private List<DirectionPrice> directionPrices;

    public DirectionPrice getCallPriceForNumber(String number) {
        String extension = number.substring(0, 3);
        for (DirectionPrice dp : directionPrices) {
            if (dp.getOperationType().equals("call") && dp.getToExtension().equals(extension))
                return dp;
        }
        return null;
    }

    public DirectionPrice getSmsPriceForNumber(String number) {
        String extension = number.substring(0, 3);
        for (DirectionPrice dp : directionPrices) {
            if (dp.getOperationType().equals("sms") && dp.getToExtension().equals(extension))
                return dp;
        }
        return null;
    }

    public double getInternetPrice() {
        for (DirectionPrice dp : directionPrices) {
            if (dp.getOperationType().equals("internet"))
                return dp.getPricePerUnit();
        }
        return 0;
    }

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

    public List<DirectionPrice> getDirectionPrices() {
        return directionPrices;
    }

    public void setDirectionPrices(List<DirectionPrice> directionPrices) {
        this.directionPrices = directionPrices;
    }
}
