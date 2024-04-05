package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public final class RunSolarEdgeClient {

    private static final File configFile = new File("solaredgeclient.yaml");

    private static final Logger LOG = LoggerFactory.getLogger(RunSolarEdgeClient.class);

    public static void main(String[] args) throws IOException {
        SolarEdgeClientConfig config = new SolarEdgeClientConfig();
        YAMLMapper yamlMapper = new YAMLMapper();
        if (configFile.exists()) {
            config = yamlMapper.readValue(configFile, SolarEdgeClientConfig.class);
        } else {
            LOG.info("Writing defaults");
            yamlMapper.writeValue(configFile, config);
        }

        SolarEdgeClient client = SolarEdgeClient.create(config);
        SiteData overview = client.getSiteOverview();
        LOG.info("Overview: {}", overview);

        String details = client.getSiteDetails();
        LOG.info("Details: {}", overview);
    }

}