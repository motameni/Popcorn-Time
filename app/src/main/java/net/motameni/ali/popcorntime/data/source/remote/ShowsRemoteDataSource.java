package net.motameni.ali.popcorntime.data.source.remote;

import android.support.annotation.NonNull;

import net.motameni.ali.popcorntime.data.ShowsDataSource;
import net.motameni.ali.popcorntime.data.source.Show;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * PopcornTime
 * Created by ali on 2017.
 */

public class ShowsRemoteDataSource implements ShowsDataSource {

    private static final String API_URL = "http://api.tvmaze.com";

    private interface Tvmaze {

        @GET("/shows")
        Call<List<Show>> getShows(@Query("page") int page);

        @GET("/shows/{id}")
        Call<Show> getShow(@Path("id") int id);
    }

    private static ShowsRemoteDataSource INSTANCE;

    public static ShowsRemoteDataSource getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new ShowsRemoteDataSource();
        }

        return INSTANCE;
    }

    private static Tvmaze tvmaze;

    private ShowsRemoteDataSource() {

        if(tvmaze == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            tvmaze = retrofit.create(Tvmaze.class);
        }
    }

    @Override
    public void getShows(@NonNull GetShowsCallBack callback) {

        try {
            List<Show> showItems = tvmaze.getShows(0).execute().body();
            if(showItems == null) {
                callback.onDataNotAvailable();
            } else {
                callback.onShowsLoaded(showItems);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getShow(int id, @NonNull GetShowCallBack callback) {

        try {
            Show showItem = tvmaze.getShow(id).execute().body();
            if(showItem == null) {
                callback.onDataNotAvailable();
            } else {
                callback.onShowLoaded(showItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
