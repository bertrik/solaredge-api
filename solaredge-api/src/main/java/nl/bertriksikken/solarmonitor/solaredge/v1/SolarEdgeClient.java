package nl.bertriksikken.solarmonitor.solaredge.v1;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

public final class SolarEdgeClient {

    private static final Logger LOG = LoggerFactory.getLogger(SolarEdgeClient.class);
    private final ISolarEdgeApi restApi;
    private final String siteId;
    private final String apiKey;

    public SolarEdgeClient(ISolarEdgeApi restApi, String siteId, String apiKey) {
        this.restApi = Objects.requireNonNull(restApi);
        this.siteId = siteId;
        this.apiKey = apiKey;
    }

    public static SolarEdgeClient create(SolarEdgeClientConfig config) {
        Duration timeout = config.getTimeout();
        LOG.info("Creating new REST client for host '{}' with timeout {}", config.getHost(), timeout);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(timeout).readTimeout(timeout).writeTimeout(timeout).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(config.getHost())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client).build();
        ISolarEdgeApi restApi = retrofit.create(ISolarEdgeApi.class);
        return new SolarEdgeClient(restApi, config.getSiteId(), config.getApikey());
    }

    public SiteOverview getSiteOverview() throws IOException {
        Response<SiteData> response = restApi.getSiteOverview(siteId, apiKey).execute();
        if (!response.isSuccessful()) {
            LOG.warn("Call getSiteOverview() failed: {}-{}", response.code(), response.errorBody().source());
            return null;
        }
        return response.body().getOverview();
    }

    public SiteDetails getSiteDetails() throws IOException {
        Response<SiteData> response = restApi.getSiteDetails(siteId, apiKey).execute();
        if (!response.isSuccessful()) {
            LOG.warn("Call getSiteDetails() failed: {}-{}", response.code(), response.errorBody().source());
            return null;
        }
        return response.body().getDetails();
    }
}
