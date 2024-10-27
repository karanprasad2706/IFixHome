//CardAdapter.java

package com.example.ifixhome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifixhome.Product;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Product> productList;
    private OnItemClickListener listener;

    public CardAdapter(List<Product> productList) {
        this.productList = productList;
    }

    // Interface for item click events
    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    // Method to set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Getter for the product list
    public List<Product> getProductList() {
        return productList;
    }

    // Setter for the product list
    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged(); // Notify the adapter that the data set has changed
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_single_product, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.imageView.setImageResource(product.getImageResId());
        holder.titleTextView.setText(product.getTitle());
        holder.descriptionTextView.setText(product.getDescription());

        // Format the price in Rupees
        Locale locale = new Locale("en", "IN"); // India locale
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("INR"));
        String formattedPrice = currencyFormatter.format(product.getPrice());

        holder.priceTextView.setText(formattedPrice);

        // Set click listener for the entire item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(product);
                }
            }
        });

        // Set click listener for the addToCartButton
        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle addToCartButton click if needed
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView priceTextView;
        ImageView addToCartButton;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.eachserviceIv);
            titleTextView = itemView.findViewById(R.id.eachservicename);
            descriptionTextView = itemView.findViewById(R.id.eachserviceBrandNameTv);
            priceTextView = itemView.findViewById(R.id.eachShoePriceTv);
            addToCartButton = itemView.findViewById(R.id.eachserviceAddToCartBtn);
        }
    }
}
