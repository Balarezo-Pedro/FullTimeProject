package pe.edu.upc.lindley;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import pe.edu.upc.lindley.model.Client;
import pe.edu.upc.lindley.model.Contact;
import pe.edu.upc.lindley.model.ContactEventAdapter;
import pe.edu.upc.lindley.model.ContactState;
import pe.edu.upc.lindley.model.Event;

public class DetailActivity extends AppCompatActivity {

    private static String EVENTS_SEARCH_URL = "http://rich3dev-001-site1.atempurl.com/api/incidence/event/";

    TextView dateTextView;
    TextView typeTextView;
    TextView aTextView;
    Button newMessageButton;
    private RecyclerView eventsRecyclerView;
    private RecyclerView.Adapter eventsAdapter;
    private RecyclerView.LayoutManager eventsLayoutManager;

    private static ArrayList<Event> events = new ArrayList<>();

    public static ArrayList<Event> getContacts() {
        return events;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle =  getIntent().getExtras();

        final String idContact = bundle.getString("idContact");
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        typeTextView = (TextView) findViewById(R.id.typeTextView);
        aTextView =(TextView) findViewById(R.id.aaatextView);

        aTextView.setText(bundle.getString("ticket"));
        dateTextView.setText("Fecha: " + bundle.getString("CreatedAt"));
        typeTextView.setText("Tipo: " +bundle.getString("Type"));
        eventsRecyclerView = (RecyclerView) findViewById(R.id.messageReportView);
        eventsLayoutManager = new LinearLayoutManager(this);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setLayoutManager(eventsLayoutManager);

        newMessageButton = (Button) findViewById(R.id.addNewMessageButton);
        newMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(DetailActivity.this, NewMessageActivity.class);
                    intent.putExtra("idcontact", idContact);
                    startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent eventItemIntent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(eventItemIntent);
            }
        });
        searchTitles(EVENTS_SEARCH_URL + idContact);
    }


    public void searchTitles(String searchTitleUrl) {
        System.out.println("URL = " + searchTitleUrl);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, searchTitleUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!
                try {

                    System.out.println("URL 2: " + EVENTS_SEARCH_URL);
                    events.clear();
                    int limit = response.length();
                    for (int position = 0; position<limit ; position++){
                        JSONObject result = (JSONObject) response.get(position);
                        int idEvent = result.getInt("idEvent");
                        int idContact = result.getInt("idContact");
                        String comment = result.getString("Comment");

                        Event event = new Event(String.valueOf(idEvent),String.valueOf(idContact),"1","08/08/2016",comment);
                        events.add(event);

                    }

                    System.out.println("BookCatalogActivity: Array Size "+ Integer.toString(events.size()));
                    eventsAdapter = new ContactEventAdapter(events);
                    eventsRecyclerView.setAdapter(eventsAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonRequest);

    }
}
