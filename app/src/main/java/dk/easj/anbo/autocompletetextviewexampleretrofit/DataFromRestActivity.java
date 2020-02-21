package dk.easj.anbo.autocompletetextviewexampleretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataFromRestActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MYFISH";
    private static final String BASE_URI = "http://anbo-fish.azurewebsites.net/Service1.svc/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            // https://futurestud.io/tutorials/retrofit-2-adding-customizing-the-gson-converter
            // Gson is no longer the default converter
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_from_rest);

        TextView messageView = findViewById(R.id.datafromrestMessageTextView);

        FishService fishService = retrofit.create(FishService.class);
        Call<List<String>> getFishNamesCall = fishService.getAllFishNames();
        getFishNamesCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> fishNames = response.body();
                    Log.d(LOG_TAG, fishNames.toString());
                    // AutoCompleteTextView wants an old-fashioned adapter (unlike RecyclerView)
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                            android.R.layout.simple_list_item_1, fishNames);
                    AutoCompleteTextView view = findViewById(R.id.datafromrestFishnameAutocompletetextview);
                    view.setAdapter(adapter);
                } else {
                    messageView.setText("Problem: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                messageView.setText("Problem: " + t.getMessage());
            }
        });
    }
}
