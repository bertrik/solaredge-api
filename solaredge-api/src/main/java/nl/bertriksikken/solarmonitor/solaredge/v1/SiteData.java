package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class SiteData {

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @JsonProperty("overview")
    private SiteOverview overview = new SiteOverview();
    @JsonProperty("details")
    private SiteDetails details = new SiteDetails();

    public SiteOverview getOverview() {
        return overview;
    }

    public SiteDetails getDetails() {
        return details;
    }

    public static final class Energy {
        @JsonProperty("energy")
        public double energy = Double.NaN;

        @Override
        public String toString() {
            return Double.toString(energy);
        }
    }

    public static final class Power {
        @JsonProperty("power")
        public double power = Double.NaN;

        @Override
        public String toString() {
            return Double.toString(power);
        }
    }

}

