package nl.bertriksikken.solarmonitor;

import org.apache.log4j.PropertyConfigurator;

public final class SolarMonitorApp {

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        SolarMonitorApp application = new SolarMonitorApp();
        application.start();
    }

    private void start() {
    }

}