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

public final class SolarEdgeClient implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(SolarEdgeClient.class);
    private final OkHttpClient httpClient;
    private final ISolarEdgeApi restApi;
    private final String siteId;
    private final String apiKey;

    public SolarEdgeClient(OkHttpClient httpClient, ISolarEdgeApi restApi, String siteId, String apiKey) {
        this.httpClient = httpClient;
        this.restApi = Objects.requireNonNull(restApi);
        this.siteId = siteId;
        this.apiKey = apiKey;
    }

    public static SolarEdgeClient create(SolarEdgeClientConfig config) {
        Duration timeout = config.getTimeout();
        LOG.info("Creating new REST client for host '{}' with timeout {}", config.getHost(), timeout);
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(timeout).readTimeout(timeout).writeTimeout(timeout).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(config.getHost()).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(JacksonConverterFactory.create()).client(client).build();
        ISolarEdgeApi restApi = retrofit.create(ISolarEdgeApi.class);
        return new SolarEdgeClient(client, restApi, config.getSiteId(), config.getApikey());
    }

    @Override
    public void close()  {
        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();
    }

    public SiteOverview getSiteOverview() throws IOException {
        Response<SiteData> response = restApi.getSiteOverview(siteId, apiKey).execute();
        if (!response.isSuccessful()) {
            LOG.warn("Call getSiteOverview() failed: {}-{}", response.code(), response.errorBody().source());
            return null;
        }
        return response.body().overview();
    }

    public SiteDetails getSiteDetails() throws IOException {
        Response<SiteData> response = restApi.getSiteDetails(siteId, apiKey).execute();
        if (!response.isSuccessful()) {
            LOG.warn("Call getSiteDetails() failed: {}-{}", response.code(), response.errorBody().source());
            return null;
        }
        return response.body().details();
    }

    public String getSiteInventory() throws IOException {
        Response<String> response = restApi.getSiteInventory(siteId, apiKey).execute();
        if (!response.isSuccessful()) {
            LOG.warn("Call getSiteDetails() failed: {}-{}", response.code(), response.errorBody().source());
            return null;
        }
        return response.body();
    }

    public String getEquipmentSensors() throws IOException {
        Response<String> response = restApi.getEquipmentSensors(siteId, apiKey).execute();
        if (!response.isSuccessful()) {
            LOG.warn("Call getSensors() failed: {}-{}", response.code(), response.errorBody().source());
            return null;
        }
        return response.body();
    }

    public String getEquipmentList() throws IOException {
        Response<String> response = restApi.getEquipmentList(siteId, apiKey).execute();
        if (!response.isSuccessful()) {
            LOG.warn("Call getSiteEquipment() failed: {}-{}", response.code(), response.errorBody().source());
            return null;
        }
        return response.body();
    }

}
