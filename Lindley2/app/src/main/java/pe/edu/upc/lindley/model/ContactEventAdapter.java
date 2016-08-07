package pe.edu.upc.lindley.model;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.upc.lindley.DetailActivity;
import pe.edu.upc.lindley.R;

/**
 * Created by lmoralea on 07/08/2016.
 */
public class ContactEventAdapter extends RecyclerView.Adapter<ContactEventAdapter.ViewHolder>{


        ArrayList<Event> events;

    public ContactEventAdapter(ArrayList<Event> events) {
        this.events = events;
        }

    @Override
    public ContactEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_comentary, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
        }

    @Override
    public void onBindViewHolder(ContactEventAdapter.ViewHolder holder, final int position) {
        holder.messageTextView.setText(events.get(position).comment);
        }

    @Override
    public int getItemCount() {
        return events.size();
        }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView messageTextView;
    TextView dateTextView;


    public ViewHolder(View itemView) {
        super(itemView);
        messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
    }
    }

}
