package pe.edu.upc.lindley;

import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewMessageActivity extends AppCompatActivity {

    private static final String TAG = "SAVE";
    private static final int REQUEST_SIGNUP = 0;
    private static final String URL_SERVICE= "http://rich3dev-001-site1.atempurl.com/api/incidence/";

    Button btnCamera;
    EditText txtComentario;

    // System Permissions Request
    private static final int CAMERIFY_PERMISSIONS_REQUEST = 100;
    // Resources Availability Indicator
    private boolean cameraAvilable = false;
    // Activity Request constants
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    // Media Type constants
    private static final int MEDIA_TYPE_IMAGE = 1;

    // File related objects
    private Uri fileUri;
    private Uri lastOutputMediaFileUri = null;
    private String idContact;
    private String state ="3";
    private String comentario;
    private String nombreFoto;
    private JsonObjectRequest req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCamera = (Button) findViewById(R.id.photoButton);
        txtComentario=(EditText)findViewById(R.id.messageEditText) ;

        Intent intent =  getIntent();
        if (intent != null){
            idContact = intent.getStringExtra("idcontact");
            Toast.makeText(this, idContact, Toast.LENGTH_SHORT).show();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((Lindley2Aplication) getApplication()).getPhotoService().addPhoto(idContact,txtComentario.getText().toString());
                comentario=txtComentario.getText().toString();
                nombreFoto=lastOutputMediaFileUri.getPath();

                try {
                    String json = "{\"idContact\":\"" + idContact + "\", \"IdContactState\":\"" + state + "\", \"Comment\":\"" + comentario + "\", \"FilePath\":\"" + nombreFoto + "\"  }";
                    req = new JsonObjectRequest(Request.Method.POST, URL_SERVICE,
                            new JSONObject(json),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
// Handle response
                                    Log.d(TAG, "res:" + response.toString());
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
// Handle Error
                                }
                            }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json; charset=utf-8");
                            return headers;
                        }
                    };


                }catch (Exception exc)
                {
                    Log.d(TAG, "TestLogin Exception:" + exc.getMessage());
                }
                Snackbar.make(view, "FOTO GUARDADA EN MOVIL //  ATENCION TERMINADA", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                finish();


            }

        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureMedia();
            }

        });
        validatePermissions();

        Volley.newRequestQueue(this).add(req);

    }


    private void captureMedia() {
        // Verify video capture switch state

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getLastOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        lastOutputMediaFileUri = fileUri;
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            // Result for image carptur esquest
            if(resultCode == RESULT_OK){
                Log.d("Cameridy","ResultCode: RESULT_OK");

                String fileName = data != null ? data.getData().getPath() : lastOutputMediaFileUri.getPath();

                Toast.makeText(this, "Image saved to : "+fileName, Toast.LENGTH_LONG).show();
            } else if(resultCode == RESULT_CANCELED){
                Log.d("Camerify", "ResultCode: RESULT_CANCELED");
            }else{
                Log.d("Camerify","ResultCode: "+Integer.toString(resultCode));
            }
        }
    }

    private Uri getLastOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type){
        File mediaStorageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        if( !mediaStorageDir.exists() ){
            if( !mediaStorageDir.mkdirs() ){
                Log.d("Camerify", "Failed to create directory");
                return null;
            }
        }else{
            Log.d("Camerify","Directory found");
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if( type == MEDIA_TYPE_IMAGE ){
            mediaFile = new File(
                    mediaStorageDir.getPath()+
                            File.separator + "IMG_" + timeStamp + ".jpg"
            );
        }
        else { return null; }

        try{
            Log.d("Camerify", mediaFile.getCanonicalPath());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return mediaFile;
    }

    private void validatePermissions(){
        if(permissionsGranted()){
            cameraAvilable = true;
            return;
        }
        ActivityCompat.requestPermissions(this,
                new String[]{
                        permission.WRITE_EXTERNAL_STORAGE,
                        permission.CAMERA
                }, CAMERIFY_PERMISSIONS_REQUEST);
    }

    private boolean permissionsGranted(){
        boolean grantedCameraPermission =
                (ContextCompat.checkSelfPermission(this,
                        permission.CAMERA) ==
                        PackageManager.PERMISSION_GRANTED);

        boolean grantedStoragePermission =
                (ContextCompat.checkSelfPermission(this,
                        permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED);

        Log.d("Camerify", "Permission for CAMERA: "+
                String.valueOf(grantedCameraPermission));
        Log.d("Camerify", "Permission for STORAGE: "+
                String.valueOf(grantedStoragePermission));

        return (grantedCameraPermission && grantedStoragePermission);
    }

}


