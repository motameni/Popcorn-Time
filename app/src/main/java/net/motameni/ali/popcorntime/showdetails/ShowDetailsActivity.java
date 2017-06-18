package net.motameni.ali.popcorntime.showdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import net.motameni.ali.popcorntime.R;
import net.motameni.ali.popcorntime.data.source.remote.ShowsRemoteDataSource;
import net.motameni.ali.popcorntime.util.ActivityUtils;

public class ShowDetailsActivity extends AppCompatActivity {

    public static String EXTRA_SHOW_ID = "SHOW_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        ShowDetailsFragment showDetailsFragment =
                (ShowDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_wrapper_activity_show_details);

        if(showDetailsFragment == null) {
            // Create the fragment
            showDetailsFragment = ShowDetailsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    showDetailsFragment,
                    R.id.fragment_wrapper_activity_show_details);
        }

        int showId = getIntent().getIntExtra(EXTRA_SHOW_ID, 0);

        ShowsRemoteDataSource dataSource = ShowsRemoteDataSource.getInstance();
        new ShowDetailsPresenter(
                showId,
                showDetailsFragment,
                dataSource
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
