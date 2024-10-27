package com.example.ifixhome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    private ArrayList<FeaturedHelperClass> featuredLocations;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_comment_design, parent, false);
        return new FeaturedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);

        holder.featuredImageView.setImageResource(featuredHelperClass.getImage());
        holder.featuredTitle.setText(featuredHelperClass.getTitle());
        holder.featuredRating.setRating(3.5f); // Set a default rating for demonstration
        holder.featuredDescription.setText(featuredHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {
        ImageView featuredImageView;
        TextView featuredTitle;
        RatingBar featuredRating;
        TextView featuredDescription;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            featuredImageView = itemView.findViewById(R.id.featured_image);
            featuredTitle = itemView.findViewById(R.id.featured_title);
            featuredRating = itemView.findViewById(R.id.featured_rating);
            featuredDescription = itemView.findViewById(R.id.featured_desc);
        }
    }
}
