package net.motameni.ali.popcorntime.shows;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import net.motameni.ali.popcorntime.data.ShowsDataSource;
import net.motameni.ali.popcorntime.data.source.Show;
import net.motameni.ali.popcorntime.data.source.remote.ShowsRemoteDataSource;
import net.motameni.ali.popcorntime.showdetails.ShowDetailsActivity;

import java.util.List;

/**
 * PopcornTime
 * Created by ali on 2017.
 */

public class ShowsPresenter implements ShowsContract.Presenter {

    private final ShowsContract.View mShowsView;

    private final ShowsRemoteDataSource mShowsDataSource;

    ShowsPresenter(@NonNull ShowsContract.View view, @NonNull ShowsRemoteDataSource dataSource) {

        mShowsView = view;
        mShowsDataSource = dataSource;

        mShowsView.setPresenter(this);
    }

    @Override
    public void start() {
        loadShows();
    }

    @Override
    public void loadShows() {
        final ShowsRemoteDataSource showsDataSource = ShowsRemoteDataSource.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                showsDataSource.getShows(new ShowsDataSource.GetShowsCallBack() {
                    @Override
                    public void onShowsLoaded(final List<Show> shows) {
                        mShowsView.getViewActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mShowsView.showShows(shows);
                            }
                        });
                    }

                    @Override
                    public void onDataNotAvailable() {
                        mShowsView.getViewActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mShowsView.hideShows();
                                mShowsView.showNoDataAvailable();
                            }
                        });
                    }
                });
            }
        }).start();
    }

    @Override
    public void openShowDetails(@NonNull Show show) {

        Intent intent = new Intent(mShowsView.getViewActivity(), ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW_ID, show.getId());
        mShowsView.getViewActivity().startActivity(intent);
    }
}
