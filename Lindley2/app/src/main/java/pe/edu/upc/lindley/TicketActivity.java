package pe.edu.upc.lindley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.upc.lindley.model.Contact;
import pe.edu.upc.lindley.model.Event;
import pe.edu.upc.lindley.model.EventsAdapter;

public class TicketActivity extends AppCompatActivity {
    ArrayList<Contact> contact;
    private RecyclerView mTicketsRecyclerView;
    private RecyclerView.Adapter mTicketsAdapter;
    private RecyclerView.LayoutManager mTicketsLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        mTicketsRecyclerView = (RecyclerView) findViewById(R.id.ticketsView);
        mTicketsRecyclerView.setHasFixedSize(true);
        contact = new ArrayList<>();
        contact.addAll(MainActivity.getContacts());
        mTicketsLayoutManager = new LinearLayoutManager(this);
        mTicketsRecyclerView.setLayoutManager(mTicketsLayoutManager);
        mTicketsAdapter = new EventsAdapter(contact);
        System.out.print("Lincoln: " + contact.size());
        mTicketsRecyclerView.setAdapter(mTicketsAdapter);

    }
}
