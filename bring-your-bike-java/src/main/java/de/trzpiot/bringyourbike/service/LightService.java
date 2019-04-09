package de.trzpiot.bringyourbike.service;

public class LightService {
    static {
        System.loadLibrary("bringyourbike");
    }

    public native void switchOn(String bridgeIp, String bridgeUsername, long lightId);
    public native void switchOff(String bridgeIp, String bridgeUsername, long lightId);
    public native void setColor(String bridgeIp, String bridgeUsername, long lightId, int r, int g, int b);
    public native void setBrightness(String bridgeIp, String bridgeUsername, long lightId, int brightness);
}
