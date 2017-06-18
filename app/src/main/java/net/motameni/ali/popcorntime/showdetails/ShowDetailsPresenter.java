package net.motameni.ali.popcorntime.showdetails;

import android.support.annotation.NonNull;
import android.util.Log;

import net.motameni.ali.popcorntime.data.ShowsDataSource;
import net.motameni.ali.popcorntime.data.source.Show;
import net.motameni.ali.popcorntime.data.source.remote.ShowsRemoteDataSource;

import java.util.List;

/**
 * PopcornTime
 * Created by ali on 2017.
 */

public class ShowDetailsPresenter implements ShowDetailsContract.Presenter {

    private final ShowDetailsContract.View mShowDetailsView;

    private final ShowsRemoteDataSource mShowsDataSource;

    private int mShowId;

    ShowDetailsPresenter(int showId,
                         @NonNull ShowDetailsContract.View view,
                         @NonNull ShowsRemoteDataSource dataSource) {

        mShowId          = showId;
        mShowDetailsView = view;
        mShowsDataSource = dataSource;

        mShowDetailsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadShow(mShowId);
    }

    @Override
    public void loadShow(final int showId) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                mShowsDataSource.getShow(showId, new ShowsDataSource.GetShowCallBack() {
                    @Override
                    public void onShowLoaded(final Show show) {
                        mShowDetailsView.getViewActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mShowDetailsView.showImage(show.getOriginalImage());
                                mShowDetailsView.showName(show.getName());
                                mShowDetailsView.showDescription(
                                        show.getPremiered(),
                                        show.getRuntime(),
                                        show.getRate());
                                mShowDetailsView.showInfoIcon();
                                mShowDetailsView.showSummary(show.getSummary());
                            }
                        });
                    }

                    @Override
                    public void onDataNotAvailable() {
                        mShowDetailsView.getViewActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                });
            }
        }).start();
    }
}
