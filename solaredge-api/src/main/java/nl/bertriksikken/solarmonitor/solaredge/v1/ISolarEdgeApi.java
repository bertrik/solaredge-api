package nl.bertriksikken.solarmonitor.solaredge.v1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * API v1, @see https://knowledge-center.solaredge.com/sites/kc/files/se_monitoring_api.pdf
 */
public interface ISolarEdgeApi {
    @GET("/site/{id}/overview")
    Call<SiteData> getSiteOverview(@Path("id") String site, @Query("api_key") String apiKey);

    @GET("/site/{id}/details")
    Call<SiteData> getSiteDetails(@Path("id") String site, @Query("api_key") String apiKey);

    @GET("/site/{id}/inventory")
    Call<String> getSiteInventory(@Path("id") String site, @Query("api_key") String apiKey);

    @GET("/equipment/{id}/sensors")
    Call<String> getEquipmentSensors(@Path("id") String site, @Query("api_key") String apiKey);

    @GET("/equipment/{id}/list")
    Call<String> getEquipmentList(@Path("id") String site, @Query("api_key") String apiKey);

}
