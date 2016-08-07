package pe.edu.upc.lindley.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.upc.lindley.R;
import pe.edu.upc.lindley.TicketActivity;

/**
 * Created by lmoralea on 05/08/2016.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    ArrayList<Contact> contacts;

    public ContactsAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_ticket, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, final int position) {
        holder.numeroTextView.setText(contacts.get(position).idContact);
        holder.dateTextView.setText(contacts.get(position).createdAt.toString());
        holder.detailTextView.setText(contacts.get(position).comment);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.printf("Selected position: %d%n", position);
                Intent eventItemIntent = new Intent(view.getContext(), TicketActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idContact", contacts.get(position).idContact);
                bundle.putString("CreatedAt", contacts.get(position).createdAt.toString());
                bundle.putString("Comment", contacts.get(position).comment);
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
        public ViewHolder(View itemView) {
            super(itemView);
            numeroTextView = (TextView) itemView.findViewById(R.id.numeroTextView);
            dateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            detailTextView = (TextView) itemView.findViewById(R.id.detailTextView);
        }
    }
}
