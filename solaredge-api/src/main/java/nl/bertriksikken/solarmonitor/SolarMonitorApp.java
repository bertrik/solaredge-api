package nl.bertriksikken.solarmonitor;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SolarMonitorApp {

    private static final Logger LOG = LoggerFactory.getLogger(SolarMonitorApp.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        SolarMonitorApp application = new SolarMonitorApp();
        application.start();
    }

    private void start() {
    }

}