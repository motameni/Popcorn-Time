package net.motameni.ali.popcorntime.showdetails;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.motameni.ali.popcorntime.R;

import java.util.Calendar;
import java.util.Date;

public class ShowDetailsFragment extends Fragment implements ShowDetailsContract.View {

    private ShowDetailsContract.Presenter mPresenter;

    private ImageView imageView;

    private ImageView infoImageView;

    private TextView showNameTextView;

    private TextView descriptionTextView;

    private TextView summaryTextView;

    public ShowDetailsFragment() {
        // Required empty public constructor
    }

    public static ShowDetailsFragment newInstance() {
        return new ShowDetailsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_show_details, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar_show_details_activity);
        ((ShowDetailsActivity)getActivity()).setSupportActionBar(toolbar);
        ((ShowDetailsActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((ShowDetailsActivity)getActivity()).getSupportActionBar().setTitle("");

        imageView           = root.findViewById(R.id.image_view_fragment_show_details);
        infoImageView       = root.findViewById(R.id.info_image_view_fragment_show_details);
        showNameTextView    = root.findViewById(R.id.show_name_text_view_fragment_show_details);
        descriptionTextView = root.findViewById(R.id.description_text_view_fragment_show_details);
        summaryTextView     = root.findViewById(R.id.summary_text_view_fragment_show_details);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        hideName();
        hideDescription();
        hideInfoIcon();
        hideSummary();

        mPresenter.start();
    }

    @Override
    public void setPresenter(ShowDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showImage(String image) {
        Picasso.with(getContext()).load(image).into(imageView);
    }

    @Override
    public void hideName() {
        showNameTextView.setText("");
    }

    @Override
    public void showName(String name) {
        showNameTextView.setText(name);
    }

    @Override
    public void hideDescription() {
        descriptionTextView.setText("");
    }

    @Override
    public void showDescription(Date premiered, int runtime, float rate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(premiered);
        descriptionTextView.setText(
                calendar.get(Calendar.YEAR) + " • " + String.valueOf(runtime) + " min • "
                        + String.valueOf(rate) + "/10");
    }

    @Override
    public void hideSummary() {
        summaryTextView.setText("");
    }

    @Override
    public void showSummary(String summary) {
        summaryTextView.setText(stripHtml(summary));
    }

    @Override
    public void hideInfoIcon() {
        infoImageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showInfoIcon() {
        infoImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public Activity getViewActivity() {
        return getActivity();
    }

    private String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }
}
