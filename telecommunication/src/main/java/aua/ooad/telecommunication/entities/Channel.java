package aua.ooad.telecommunication.entities;

public class Channel {
    private String name;
    private int catchUPPeriod;
    private double price;

    public Channel(String name, int catchUPPeriod, double price) {
        this.name = name;
        this.catchUPPeriod = catchUPPeriod;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatchUPPeriod() {
        return catchUPPeriod;
    }

    public void setCatchUPPeriod(int catchUPPeriod) {
        this.catchUPPeriod = catchUPPeriod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
