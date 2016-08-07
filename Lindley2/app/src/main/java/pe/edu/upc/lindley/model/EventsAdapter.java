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
import pe.edu.upc.lindley.TicketActivity;

/**
 * Created by lmoralea on 31/07/2016.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    ArrayList<Contact> contacts;

    public EventsAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_ticket, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder holder, final int position) {
        holder.numeroTextView.setText("Ticket: "+ String.valueOf(contacts.get(position).idContact));
        holder.dateTextView.setText("Fecha: " + contacts.get(position).createdAt.toString());
        holder.detailTextView.setText("Estado: " + contacts.get(position).contactState.getContactState());
        holder.statusTextView.setText("Tipo: " + contacts.get(position).getType());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.printf("Selected position: %d%n", position);
                String ticket = "";
                ticket = "Ticket: " + String.valueOf(contacts.get(position).idContact);
                Intent eventItemIntent = new Intent(view.getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ticket", ticket );
                bundle.putString("idContact", String.valueOf(contacts.get(position).idContact) );
                bundle.putString("CreatedAt", contacts.get(position).createdAt.toString());
                bundle.putString("Status", contacts.get(position).contactState.getContactState());
                bundle.putString("Type", contacts.get(position).getType());
                eventItemIntent.putExtras(bundle);
                view.getContext().startActivity(eventItemIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numeroTextView;
        TextView dateTextView;
        TextView detailTextView;
        TextView statusTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            numeroTextView = (TextView) itemView.findViewById(R.id.numeroTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            detailTextView = (TextView) itemView.findViewById(R.id.detailTextView);
            statusTextView = (TextView) itemView.findViewById(R.id.typeTextView);
        }
    }

}
