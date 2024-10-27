// Inside CartItemAdapter.java

package com.example.ifixhome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private List<Product> cartItemList;
    private OnItemClickListener listener;
    private CartActivity cartActivity;  // Add a reference to the CartActivity

    public CartItemAdapter(List<Product> cartItemList, CartActivity cartActivity) {
        this.cartItemList = cartItemList;
        this.cartActivity = cartActivity;
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public List<Product> getCartItemList() {
        return cartItemList;
    }


    public int getQuantityAtPosition(int position) {
        if (position >= 0 && position < cartItemList.size()) {
            return cartItemList.get(position).getQuantity();
        }
        return 0; // Default value if position is out of bounds
    }

    public double getPrice(int position) {
        if (position >= 0 && position < cartItemList.size()) {
            return cartItemList.get(position).getPrice();
        }
        return 0.0;
    }

    public int getQuantity(int position) {
        if (position >= 0 && position < cartItemList.size()) {
            return cartItemList.get(position).getQuantity();
        }
        return 0;
    }



    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_item_layout, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Product product = cartItemList.get(position);

        holder.itemImageView.setImageResource(product.getImageResId());
        holder.titleTextView.setText(product.getTitle());
        holder.subtitleTextView.setText(product.getDescription());
        holder.priceTextView.setText(formatPrice(product.getPrice()));
        holder.quantityTextView.setText(String.valueOf(product.getQuantity()));



        // Set click listener for the removeButton
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    // Notify the listener that the remove button is clicked, and pass the corresponding product
                    listener.onItemClick(product);
                    Toast.makeText(view.getContext(), "1 item removed from cart", Toast.LENGTH_SHORT).show();
                    double newTotalPrice = updateTotalPrice();
                    cartActivity.displayTotalPrice(newTotalPrice);
                }
            }
        });

        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrease the quantity and update the quantityTextView
                int newQuantity = product.getQuantity() - 1;
                if (newQuantity >= 1) {
                    product.setQuantity(newQuantity);
                    holder.quantityTextView.setText(String.valueOf(newQuantity));
                    double newTotalPrice = updateTotalPrice();
                    cartActivity.displayTotalPrice(newTotalPrice);
                }
            }
        });

        // Set click listener for the incrementButton
        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increase the quantity and update the quantityTextView
                int newQuantity = product.getQuantity() + 1;
                product.setQuantity(newQuantity);
                holder.quantityTextView.setText(String.valueOf(newQuantity));
                double newTotalPrice = updateTotalPrice();
                cartActivity.displayTotalPrice(newTotalPrice);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : cartItemList) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    public double updateTotalPrice() {
        double totalPrice = calculateTotalPrice();
        notifyDataSetChanged();
        return totalPrice;
    }


    private String formatPrice(double price) {
        return "₹" + price;
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView titleTextView;
        TextView subtitleTextView;
        TextView priceTextView;
        TextView quantityTextView;
        Button removeButton, decrementButton, incrementButton;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            decrementButton = itemView.findViewById(R.id.decrementButton);
            incrementButton = itemView.findViewById(R.id.incrementButton);
        }
    }
}
