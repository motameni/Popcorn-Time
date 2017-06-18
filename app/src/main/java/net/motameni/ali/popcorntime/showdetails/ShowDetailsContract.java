package net.motameni.ali.popcorntime.showdetails;

import android.app.Activity;
import android.support.annotation.NonNull;

import net.motameni.ali.popcorntime.BasePresenter;
import net.motameni.ali.popcorntime.BaseView;
import net.motameni.ali.popcorntime.data.source.Show;

import java.util.Date;

/**
 * PopcornTime
 * Created by ali on 2017.
 */

public interface ShowDetailsContract {

    interface View extends BaseView<Presenter> {

        void showImage(String image);

        void hideName();

        void showName(String name);

        void hideDescription();

        void showDescription(Date premiered, int runtime, float rate);

        void hideSummary();

        void showSummary(String summary);

        void hideInfoIcon();

        void showInfoIcon();

        Activity getViewActivity();
    }

    interface Presenter extends BasePresenter {

        void loadShow(int showId);
    }
}
