package aua.ooad.telecommunication.entities;

import java.sql.Timestamp;

public class Call {
    private int customerId;
    private int fromNumber;
    private int toNumber;
    private Timestamp startTimestamp;
    private Timestamp endTimestamp;
    private int type;

    public void getDuration(){};

}
