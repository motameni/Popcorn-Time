package net.motameni.ali.popcorntime.shows;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import net.motameni.ali.popcorntime.BasePresenter;
import net.motameni.ali.popcorntime.BaseView;
import net.motameni.ali.popcorntime.data.source.Show;

import java.util.List;

/**
 * PopcornTime
 * Created by ali on 2017.
 */

public interface ShowsContract {

    interface View extends BaseView<Presenter> {

        void showShows(List<Show> shows);

        void hideShows();

        void showPreLoader();

        void hidePreLoader();

        void showNoDataAvailable();

        void hideNoDataAvailable();

        FragmentActivity getViewActivity();
    }

    interface Presenter extends BasePresenter {

        void loadShows();

        void openShowDetails(@NonNull Show show);
    }

}
