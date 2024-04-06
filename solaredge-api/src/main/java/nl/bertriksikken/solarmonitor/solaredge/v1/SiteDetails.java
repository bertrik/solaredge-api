package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class SiteDetails {
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    @JsonProperty("status")
    String status;
    @JsonProperty("location")
    Location location;
    @JsonProperty("peakPower")
    double peakPower;

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{id=%d,name=%s,status=%s,location=%s,peakPower=%.1f", id, name, status, location, peakPower);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Location {
        @JsonProperty("country")
        String country;
        @JsonProperty("timeZone")
        String timeZone;
        @Override
        public String toString() {
            return String.format(Locale.ROOT, "{country=%s,timeZone=%s}", country, timeZone);
        }
    }
}
