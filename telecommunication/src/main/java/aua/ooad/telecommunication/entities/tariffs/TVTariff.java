package aua.ooad.telecommunication.entities.tariffs;

import aua.ooad.telecommunication.entities.Channel;

import java.util.List;

public class TVTariff extends Tariff{
    private boolean hasCatchUp;
    private List<Channel> channels;

    public boolean isHasCatchUp() {
        return hasCatchUp;
    }

    public void setHasCatchUp(boolean hasCatchUp) {
        this.hasCatchUp = hasCatchUp;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
