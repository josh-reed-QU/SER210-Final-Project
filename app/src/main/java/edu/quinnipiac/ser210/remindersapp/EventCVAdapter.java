package edu.quinnipiac.ser210.remindersapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventCVAdapter extends RecyclerView.Adapter<EventCVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<EventModal> eventArrayList;
    private Context context;

    // constructor
    public EventCVAdapter(ArrayList<EventModal> eventArrayList, Context context) {
        this.eventArrayList = eventArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        EventModal modal = eventArrayList.get(position);
        holder.eventNameCV.setText(modal.getEventName());
        holder.eventDescCV.setText(modal.getEventDescription());
        holder.eventDateCV.setText(modal.getEventDate());
        holder.eventTimeCV.setText(modal.getEventTime());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateEventActivity.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getEventName());
                i.putExtra("description", modal.getEventDescription());
                i.putExtra("date", modal.getEventDate());
                i.putExtra("time", modal.getEventTime());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return eventArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView eventNameCV, eventDescCV, eventDateCV, eventTimeCV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            eventNameCV = itemView.findViewById(R.id.idCVCategoryName);
            eventDescCV = itemView.findViewById(R.id.idCVEventDescription);
            eventDateCV = itemView.findViewById(R.id.idCVEventDate);
            eventTimeCV = itemView.findViewById(R.id.idCVEventTime);
        }
    }
}
