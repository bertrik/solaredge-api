package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SiteDetails(@JsonProperty("id") int id, @JsonProperty("name") String name,
                          @JsonProperty("status") String status, @JsonProperty("location") Location location,
                          @JsonProperty("peakPower") double peakPower) {
    // no-arg jackson constructor
    SiteDetails() {
        this(0, "", "", new Location(), Double.NaN);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Location(@JsonProperty("country") String country, @JsonProperty("timeZone") String timeZone) {
        Location() {
            this("", "");
        }
    }

}
