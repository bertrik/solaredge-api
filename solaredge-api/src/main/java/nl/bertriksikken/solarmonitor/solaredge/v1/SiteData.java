package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class SiteData {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @JsonProperty("overview")
    private Overview overview = new Overview();

    @JsonProperty("details")
    private Details details = new Details();

    public static final class Overview {
        @JsonProperty("lastUpdateTime")
        public String lastUpdateTime = "";
        @JsonProperty("lifeTimeData")
        public Energy lifeTimeData = new Energy();
        @JsonProperty("lastYearData")
        public Energy lastYearData = new Energy();
        @JsonProperty("lastMonthData")
        public Energy lastMonthData = new Energy();
        @JsonProperty("lastDayData")
        public Energy lastDayData = new Energy();
        @JsonProperty("currentPower")
        public Power currentPower = new Power();
        @JsonProperty("measuredBy")
        public String measuredBy = "";

        public LocalDateTime getLastUpdateTime() {
            return LocalDateTime.parse(lastUpdateTime, DATE_TIME_FORMAT);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Details {

    }

    public Overview getOverview() {
        return overview;
    }

    public Details getDetails() {
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

