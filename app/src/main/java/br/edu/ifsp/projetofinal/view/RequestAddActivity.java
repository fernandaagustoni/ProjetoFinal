package br.edu.ifsp.projetofinal.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.Manifest;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestAddMVP;
import br.edu.ifsp.projetofinal.presenter.RequestAddPresenter;
import br.edu.ifsp.projetofinal.utils.Constant;


public class RequestAddActivity extends AppCompatActivity implements RequestAddMVP.View, View.OnClickListener{
    private RequestAddMVP.Presenter presenter;
    private EditText fromEditText;
    private EditText toEditText;
    private TextView dateEditText;
    private Button attachmentEditText;
    private Button attachmentKmBEditText;
    private Button attachmentKmAEditText;
    private Request request;
    private ImageView photoImageView;
    private ImageView photoImageViewKmB;
    private ImageView photoImageViewKmA;
    private String base64Photo;
    private String base64Photo_2;
    private String base64Photo_3;
    private Button confirmButton;
    public int id = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE_2 = 2;
    private static final int CAMERA_REQUEST_CODE_3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_add);
        findViews();
        setListener();
        presenter = new RequestAddPresenter(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            request = (Request) bundle.getSerializable(Constant.ORIGEM);
        }
        setMenu();
    }
    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View view) {
        if (view == confirmButton){
            saveRequest();
        }if(view == attachmentEditText || view == attachmentKmAEditText || view == attachmentKmBEditText){
            tirarFoto(view);
        }
    }
    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public Request getRequest() {
        return request;
    }
    @Override
    public String returnBase64() {
        return this.base64Photo;
    }

    @Override
    public void close() {

    }

    private void findViews(){
        fromEditText = findViewById(R.id.edittext_from);
        toEditText = findViewById(R.id.edittext_to);
        dateEditText = findViewById(R.id.edittext_date);
        attachmentEditText = findViewById(R.id.button_attachment);
        attachmentKmBEditText = findViewById(R.id.button_attachment_km_b);
        attachmentKmAEditText = findViewById(R.id.button_attachment_km_a);
        confirmButton = findViewById(R.id.button_save_request);
        photoImageView = findViewById(R.id.imgPhoto);
        photoImageViewKmB = findViewById(R.id.imgPhotoKmB);
        photoImageViewKmA = findViewById(R.id.imgPhotoKmA);
    }

    private void setListener(){
        confirmButton.setOnClickListener(this);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RequestAddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateEditText.setBackground(null);
                                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year ;
                                dateEditText.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        attachmentEditText.setOnClickListener(this);
        attachmentKmAEditText.setOnClickListener(this);
        attachmentKmBEditText.setOnClickListener(this);
    }
    public void tirarFoto(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            if(view == attachmentEditText){
                startCamera();
            }else if(view == attachmentKmBEditText){
                startCamera2();
            }else if(view == attachmentKmAEditText){
                startCamera3();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            base64Photo = Base64.encodeToString(byteArray, Base64.DEFAULT);
            photoImageView.setImageBitmap(photo);
        }
        else if (requestCode == CAMERA_REQUEST_CODE_2 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            base64Photo_2 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            photoImageViewKmB.setImageBitmap(photo);
        }
       else if (requestCode == CAMERA_REQUEST_CODE_3 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            base64Photo_3 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            photoImageViewKmA.setImageBitmap(photo);
        }
    }

    private void startCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }
    private void startCamera2() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE_2);
    }
    private void startCamera3() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE_3);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void saveRequest() {
        Request request = new Request();
        request.setOrigem(fromEditText.getText().toString());
        request.setDestino(toEditText.getText().toString());
        request.setDataViagem(dateEditText.getText().toString());
        request.setAnexoNotaFiscal(base64Photo);
        request.setAnexoKmAntes(base64Photo_2);
        request.setAnexoKmDepois(base64Photo_3);
        request.setStatus("Aguardando aprovação");
        presenter.saveNewRequest(request);
        finish();
    }
}