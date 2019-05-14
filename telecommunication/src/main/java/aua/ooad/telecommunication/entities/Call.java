package aua.ooad.telecommunication.entities;

import aua.ooad.telecommunication.instances.MobileInstance;
import aua.ooad.telecommunication.instances.PackageInstance;

import java.sql.Timestamp;

public class Call {
    private String customerId;
    private String fromNumber;
    private String toNumber;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private String type;

    public Call(MobileInstance mi, String toNumber, Timestamp startTimestamp) {
        customerId = mi.getCustomer().getId();
        fromNumber = mi.getNumber().getNumber();
        this.toNumber = toNumber;
        this.startTimestamp = startTimestamp;
        type = "mobile";
    }

    public Call(PackageInstance pi, String toNumber, Timestamp startTimestamp) {
        customerId = pi.getCustomer().getId();
        fromNumber = pi.getNumber().getNumber();
        this.toNumber = toNumber;
        this.startTimestamp = startTimestamp;
        type = "fixed";
    }

    public double getDuration(){
        return (endTimestamp.getTime() - startTimestamp.getTime())/ 60000.;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
