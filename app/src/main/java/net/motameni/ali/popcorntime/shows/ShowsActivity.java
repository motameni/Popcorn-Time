package net.motameni.ali.popcorntime.shows;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.motameni.ali.popcorntime.R;
import net.motameni.ali.popcorntime.data.source.remote.ShowsRemoteDataSource;
import net.motameni.ali.popcorntime.util.ActivityUtils;

public class ShowsActivity extends AppCompatActivity {

    private ShowsPresenter mShowsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ShowsFragment showsFragment =
                (ShowsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_wrapper_activity_shows);
        if (showsFragment == null) {
            // Create the fragment
            showsFragment = ShowsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), showsFragment, R.id.fragment_wrapper_activity_shows);
        }

        ShowsRemoteDataSource dataSource = ShowsRemoteDataSource.getInstance();
        mShowsPresenter = new ShowsPresenter(showsFragment, dataSource);

        /*// Create the presenter
        mTasksPresenter = new TasksPresenter(
                Injection.provideTasksRepository(getApplicationContext()), tasksFragment);

        // Load previously saved state, if available.
        if (savedInstanceState != null) {
            TasksFilterType currentFiltering =
                    (TasksFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            mTasksPresenter.setFiltering(currentFiltering);
        }
*/
    }

}
