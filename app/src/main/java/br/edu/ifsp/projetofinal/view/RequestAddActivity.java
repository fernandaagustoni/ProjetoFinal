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
    private TextView attachmentEditText;
    private TextView attachmentKmBEditText;
    private TextView attachmentKmAEditText;
    private Request request;
    private ImageView photoImageView;
    private String base64Photo;
    private Button confirmButton;

    private static final int CAMERA_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_add);
        findViews();
        setListener();
        presenter = new RequestAddPresenter(this);
        setToolbar();
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
    public void close() {

    }

    private void findViews(){
        fromEditText = findViewById(R.id.edittext_from);
        toEditText = findViewById(R.id.edittext_to);
        dateEditText = findViewById(R.id.edittext_date);
        attachmentEditText = findViewById(R.id.edittext_attachment);
        attachmentKmBEditText = findViewById(R.id.edittext_attachment_km_b);
        attachmentKmAEditText = findViewById(R.id.edittext_attachment_km_a);
        confirmButton = findViewById(R.id.button_save_request);
        photoImageView = findViewById(R.id.imgPhoto);
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
        attachmentEditText.setOnClickListener(this::tirarFoto);
        attachmentKmAEditText.setOnClickListener(this::tirarFoto);
        attachmentKmBEditText.setOnClickListener(this::tirarFoto);
    }
    public void tirarFoto(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            startCamera();
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
            //    attachmentEditText.setImageBitmap(photo);
            //  attachmentKmAEditText.setImageBitmap(photo);
            //  attachmentKmBEditText.setImageBitmap(photo);
        }
    }
    private void startCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        request.setAnexoNotaFiscal(attachmentEditText.getText().toString());
        request.setAnexoKmAntes(attachmentKmBEditText.getText().toString());
        request.setAnexoKmDepois(attachmentKmAEditText.getText().toString());
        request.setStatus("Aguardando aprovação");
        presenter.saveNewRequest(request);
        finish();
    }
}