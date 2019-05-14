package aua.ooad.telecommunication.entities;

public class DirectionPrice {
    private String toExtension;
    private double pricePerUnit;
    private String operationType;

    public DirectionPrice(String toExtension, double pricePerUnit, String operationType) {
        this.toExtension = toExtension;
        this.pricePerUnit = pricePerUnit;
        this.operationType = operationType;
    }

    public String getToExtension() {
        return toExtension;
    }

    public void setToExtension(String toExtension) {
        this.toExtension = toExtension;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
