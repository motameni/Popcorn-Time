package net.motameni.ali.popcorntime.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.motameni.ali.popcorntime.data.source.Show;

import java.util.List;

/**
 * Created by ali on 6/15/2017 AD.
 */

public interface ShowsDataSource {

    interface GetShowsCallBack {

        void onShowsLoaded(List<Show> shows);

        void onDataNotAvailable();
    }

    interface GetShowCallBack {

        void onShowLoaded(Show show);

        void onDataNotAvailable();
    }

    void getShows(@NonNull GetShowsCallBack callback);

    void getShow(int id, @NonNull GetShowCallBack callback);

}
