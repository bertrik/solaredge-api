package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class SiteDataTest {

    private static final Logger LOG = LoggerFactory.getLogger(SiteDataTest.class);
    private static final ObjectMapper MAPPER  = new ObjectMapper();
    private static final ZoneId TIME_ZONE = ZoneId.of("Europe/Amsterdam");

    @Test
    public void testOverview() throws IOException {
        URL url = getClass().getClassLoader().getResource("site_overview.json");
        SiteData siteData = MAPPER.readValue(url, SiteData.class);
        SiteOverview overview = siteData.overview();
        LocalDateTime dateTime = overview.getLastUpdateTime();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, TIME_ZONE);
        LOG.info("Date/time = {}", zonedDateTime);
    }

    @Test
    public void testDetails() throws IOException {
        URL url = getClass().getClassLoader().getResource("site_details.json");
        SiteData siteData = MAPPER.readValue(url, SiteData.class);
        SiteDetails details = siteData.details();
        Assertions.assertNotNull(details);
    }

}
