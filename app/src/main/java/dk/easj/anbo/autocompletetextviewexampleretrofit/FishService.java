package dk.easj.anbo.autocompletetextviewexampleretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface FishService {
    @GET("fish")
    Call<List<String>> getAllFishNames();
}
