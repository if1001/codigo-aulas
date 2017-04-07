package br.ufpe.cin.if1001.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


    //Inclua checagens e solicitação de permissão para armazenamento.
    // no caso de Internet, coloque uma checagem de permissão para habilitar ou desabilitar o botão

    private Button btn_location;
    private Button btn_camera;
    private Button btn_internet;
    private Button btn_contacts;
    private Button btn_storage;

    private static final String[] INITIAL_PERMISSIONS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] CAMERA_PERMISSIONS={
            Manifest.permission.CAMERA
    };
    private static final String[] CONTACTS_PERMISSIONS={
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] LOCATION_PERMISSIONS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int ON_CREATE_REQUEST = 1001;
    private static final int CAMERA_REQUEST = ON_CREATE_REQUEST+1;
    private static final int CONTACTS_REQUEST = ON_CREATE_REQUEST+2;
    private static final int LOCATION_REQUEST = ON_CREATE_REQUEST+3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_location = (Button)findViewById(R.id.btn_location);
        btn_camera = (Button)findViewById(R.id.btn_camera);
        btn_internet = (Button)findViewById(R.id.btn_internet);
        btn_contacts = (Button)findViewById(R.id.btn_contacts);
        btn_storage = (Button)findViewById(R.id.btn_storage);


        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (podeLocation()) {
                    acessarLocation();
                }
                else {
                    requestPermissions(LOCATION_PERMISSIONS, LOCATION_REQUEST);
                }
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (podeCamera()) {
                    acessarCamera();
                }
                else {
                    requestPermissions(CAMERA_PERMISSIONS, CAMERA_REQUEST);
                }
            }
        });

        btn_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (podeContacts()) {
                    acessarContacts();
                }
                else {
                    requestPermissions(CONTACTS_PERMISSIONS, CONTACTS_REQUEST);
                }
            }
        });

        if (!podeLocation() || !podeContacts()) {
            requestPermissions(INITIAL_PERMISSIONS, ON_CREATE_REQUEST);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarInfo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        atualizarInfo();

        switch(requestCode) {
            case CAMERA_REQUEST:
                if (podeCamera()) {
                    acessarCamera();
                }
                else {
                    sem_permissao();
                }
                break;

            case CONTACTS_REQUEST:
                if (podeContacts()) {
                    acessarContacts();
                }
                else {
                    sem_permissao();
                }
                break;

            case LOCATION_REQUEST:
                if (podeLocation()) {
                    acessarLocation();
                }
                else {
                    sem_permissao();
                }
                break;
        }
        /**/
    }

    private void atualizarInfo() {
        btn_location.setText(getResources().getText(R.string.btn_txt_location) + ": " + String.valueOf(podeLocation()));
        btn_camera.setText(getResources().getText(R.string.btn_txt_camera) + ": " + String.valueOf(podeCamera()));
        btn_internet.setText(getResources().getText(R.string.btn_txt_internet) + ": " + String.valueOf(hasPermission(Manifest.permission.INTERNET)));
        btn_contacts.setText(getResources().getText(R.string.btn_txt_contacts) + ": " + String.valueOf(podeContacts()));
        btn_storage.setText(getResources().getText(R.string.btn_txt_storage) + ": " + String.valueOf(hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)));
    }

    private boolean podeLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean podeCamera() {
        return(hasPermission(Manifest.permission.CAMERA));
    }

    private boolean podeContacts() {
        return(hasPermission(Manifest.permission.READ_CONTACTS));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }

    private void sem_permissao() {
        Toast.makeText(this, R.string.msg_sem_permissao, Toast.LENGTH_LONG).show();
    }

    private void acessarCamera() {
        Toast.makeText(this, R.string.msg_camera, Toast.LENGTH_SHORT).show();
    }

    private void acessarContacts() {
        Toast.makeText(this, R.string.msg_contacts, Toast.LENGTH_SHORT).show();
    }

    private void acessarLocation() {
        Toast.makeText(this, R.string.msg_location, Toast.LENGTH_SHORT).show();
    }
}
