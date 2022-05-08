package edu.quinnipiac.ser210.remindersapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryCVAdapter extends RecyclerView.Adapter<CategoryCVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CategoryModal> categoryArrayList;
    private Context context;

    // constructor
    public CategoryCVAdapter(ArrayList<CategoryModal> categoryArrayList, Context context) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CategoryModal modal = categoryArrayList.get(position);
        holder.categoryNameCV.setText(modal.getEventCategory());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);

                Bundle bundle = new Bundle();
                bundle.putString("category", modal.getEventCategory());

                navController.navigate(R.id.action_homeScreenFragment_to_eventListFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView categoryNameCV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            categoryNameCV = itemView.findViewById(R.id.idCVCategoryName);
        }
    }
}
