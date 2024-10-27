package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SiteData(@JsonProperty("overview") SiteOverview overview, @JsonProperty("details") SiteDetails details) {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    SiteData() {
        this(new SiteOverview(), new SiteDetails());
    }

    public record Energy(@JsonProperty("energy") double energy) {
        Energy() {
            this(Double.NaN);
        }
    }

    public record Power(@JsonProperty("power") double power) {
        Power() {
            this(Double.NaN);
        }
    }
}

