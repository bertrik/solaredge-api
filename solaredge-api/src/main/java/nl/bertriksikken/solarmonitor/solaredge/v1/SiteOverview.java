package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Locale;

public final class SiteOverview {
    @JsonProperty("lastUpdateTime")
    public String lastUpdateTime = "";
    @JsonProperty("lifeTimeData")
    public SiteData.Energy lifeTimeData = new SiteData.Energy();
    @JsonProperty("lastYearData")
    public SiteData.Energy lastYearData = new SiteData.Energy();
    @JsonProperty("lastMonthData")
    public SiteData.Energy lastMonthData = new SiteData.Energy();
    @JsonProperty("lastDayData")
    public SiteData.Energy lastDayData = new SiteData.Energy();
    @JsonProperty("currentPower")
    public SiteData.Power currentPower = new SiteData.Power();
    @JsonProperty("measuredBy")
    public String measuredBy = "";

    public LocalDateTime getLastUpdateTime() {
        return LocalDateTime.parse(lastUpdateTime, SiteData.DATE_TIME_FORMAT);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{lastUpdate=%s,lifeTime=%s,lastYear=%s,lastMonth=%s,lastDay=%s,current=%s,measuredBy=%s", lastUpdateTime, lifeTimeData, lastYearData, lastMonthData, lastDayData, currentPower, measuredBy);
    }
}