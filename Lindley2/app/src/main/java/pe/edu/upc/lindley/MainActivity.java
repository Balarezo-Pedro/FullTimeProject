package pe.edu.upc.lindley;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.upc.lindley.R;
import pe.edu.upc.lindley.model.Client;
import pe.edu.upc.lindley.model.Contact;
import pe.edu.upc.lindley.model.ContactState;
import pe.edu.upc.lindley.model.Event;

public class MainActivity extends AppCompatActivity {

    private static String INCIDENT_SEARCH_URL = "http://rich3dev-001-site1.atempurl.com/api/incidence/seller/1";


    private static ArrayList<Contact> contactArray = new ArrayList<>();

    public static ArrayList<Contact> getContacts() {
        return contactArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contactArray = new ArrayList<>();

        searchTitles(INCIDENT_SEARCH_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchTitles(String searchTitleUrl) {
        System.out.println("URL = " + searchTitleUrl);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, searchTitleUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // the response is already constructed as a JSONObject!
                try {

                     System.out.println("URL: " + INCIDENT_SEARCH_URL);

                    int limit = response.length();
                    for (int position = 0; position<limit ; position++){
                        JSONObject result = (JSONObject) response.get(position);
                        int idContact = result.getInt("IdContact");
                        String createdAt = result.getString("CreatedAt");
                        int idClient = result.getInt("IdClient");
                        String nameClient = result.getString("Client");
                        String legalName = result.getString("Client");
                        int idContactState = result.getInt("IdContactState");
                        String contactState = result.getString("ContactState");
                        String comment = result.getString("Comment");
                        String subject = result.getString("Subject");
                        Client cliente = new Client(idClient,nameClient,legalName);
                        ContactState contactStateObject = new ContactState(idContactState,contactState);

                        Contact contact = new Contact(idContact, cliente, createdAt, contactStateObject, comment, subject);
                        contactArray.add(contact);
                        System.out.println("Ticket = " + contact.getIdContact());
                    }
                    System.out.println("searchTitles: Array Size "+ Integer.toString(contactArray.size()));

                    if(contactArray.size() > 0) {
                        Intent intent = new Intent(MainActivity.this, TicketActivity.class);
                        startActivity(intent);
                    }


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
