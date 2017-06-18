package net.motameni.ali.popcorntime.shows;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.motameni.ali.popcorntime.R;
import net.motameni.ali.popcorntime.data.source.Show;

import java.util.ArrayList;
import java.util.List;

public class ShowsFragment extends Fragment implements ShowsContract.View {

    ShowsContract.Presenter mPresenter;

    public ShowsFragment() {
        // Required empty public constructor
    }

    public static ShowsFragment newInstance() {
        return new ShowsFragment();
    }

    private RecyclerView mRecyclerView;
    private ShowsAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_shows, container, false);
        mRecyclerView = root.findViewById(R.id.recycler_view_fragment_shows);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        // mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(getContext(),2);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ShowsAdapter(new ArrayList<Show>(0), getContext(), mPresenter);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ShowsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showShows(List<Show> shows) {
        // specify an adapter (see also next example)
        mAdapter.updateDataset(shows);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideShows() {

        mAdapter.updateDataset(new ArrayList<Show>(0));
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPreLoader() {

    }

    @Override
    public void hidePreLoader() {

    }

    @Override
    public void showNoDataAvailable() {

    }

    @Override
    public void hideNoDataAvailable() {

    }

    @Override
    public FragmentActivity getViewActivity() {
        return getActivity();
    }


}
