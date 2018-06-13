package samples.com.android01_task.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retrofit is ued to make a request call and gson converter is used to parse the data
 * **/
public class ApiClient
{
    public static final String BASE_URL = "https://learncodeonline.in/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getRetrofitClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
