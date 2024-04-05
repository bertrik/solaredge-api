package nl.bertriksikken.solarmonitor.solaredge.v1;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;

@JsonIgnoreProperties()
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public final class SolarEdgeClientConfig {

    @JsonProperty("host")
    private String host = "https://monitoringapi.solaredge.com";

    @JsonProperty("timeout")
    private int timeout = 10;

    @JsonProperty("siteId")
    private String siteId = "";

    @JsonProperty("apiKey")
    private String apiKey = "";

    public String getHost() {
        return host;
    }

    public Duration getTimeout() {
        return Duration.ofSeconds(timeout);
    }

    public String getSiteId() {
        return siteId;
    }

    public String getApikey() {
        return apiKey;
    }

}
