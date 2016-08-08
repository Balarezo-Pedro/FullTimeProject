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
import java.util.List;

public class NewMessageActivity extends AppCompatActivity {

    Button btnCamera;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCamera = (Button) findViewById(R.id.photoButton);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureMedia();
            }

        });
        validatePermissions();
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


