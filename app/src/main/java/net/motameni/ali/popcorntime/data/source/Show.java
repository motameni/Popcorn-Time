package net.motameni.ali.popcorntime.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.util.Date;

/**
 * Created by ali on 6/15/2017 AD.
 */

public final class Show {

    private int id;

    private String name;

    private String summary;

    private int runtime;

    private Rating rating;

    private class Rating {

        private float average;
    }

    private Date premiered;

    private Image image;

    private class Image {

        String medium;

        String original;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getSummary() {
        return summary;
    }

    public int getRuntime() {
        return runtime;
    }

    public float getRate() {
        return rating != null ? rating.average : 0;
    }

    @Nullable
    public Date getPremiered() {
        return premiered;
    }

    @Nullable
    public String getMediumImage() {
        return image != null ? image.medium : null;
    }

    @Nullable
    public String getOriginalImage() {
        return image != null ? image.original : null;
    }

}
