package net.motameni.ali.popcorntime.shows;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import net.motameni.ali.popcorntime.R;
import net.motameni.ali.popcorntime.data.source.Show;
import net.motameni.ali.popcorntime.showdetails.ShowDetailsActivity;

import java.util.List;

/**
 * PopcornTime
 * Created by ali on 2017.
 */

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ViewHolder> {

    private List<Show> shows;

    private Context context;

    private ShowsContract.Presenter presenter;

    private Point size;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ShowsAdapter(List<Show> shows, Context context, ShowsContract.Presenter presenter) {
        this.shows = shows;
        this.context = context;
        this.presenter = presenter;

        // Compute screen width and height
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.size = new Point();
        display.getSize(this.size);
    }

    public void updateDataset(List<Show> shows) {
        this.shows = shows;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(LinearLayout linearLayout) {
            super(linearLayout);

            imageView = linearLayout.findViewById(R.id.image_view_item_shows);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ShowsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_view_item_shows, parent, false);

        LinearLayout linearLayout = view.findViewById(R.id.linear_layout_item_shows);
        GridLayoutManager.LayoutParams params
                = (GridLayoutManager.LayoutParams) linearLayout.getLayoutParams();

        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());

        params.width  = this.size.x/2 - px;
        params.height = this.size.y/2 - px;
        linearLayout.setLayoutParams(params);

        ImageView imageView = linearLayout.findViewById(R.id.image_view_item_shows);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(linearLayout);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openShowDetails(shows.get(position));
            }
        });
        Picasso.with(context).load(shows.get(position).getMediumImage()).into(holder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return shows.size();
    }
}
