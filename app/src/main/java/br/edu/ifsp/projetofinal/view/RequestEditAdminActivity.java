package br.edu.ifsp.projetofinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestEditMVP;
import br.edu.ifsp.projetofinal.presenter.RequestEditPresenter;
import br.edu.ifsp.projetofinal.utils.Constant;

public class RequestEditAdminActivity extends AppCompatActivity implements RequestEditMVP.View, View.OnClickListener{
    private RequestEditMVP.Presenter presenter;
    private EditText fromEditText;
    private EditText toEditText;
    private TextView dateEditText;
    private TextView statusEditText;
    private ImageView photoImageView;
    private ImageView photoImageViewKmB;
    private ImageView photoImageViewKmA;
    private Button approveButton;
    private Button rejectButton;
    private Request editRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_edit_admin);
        presenter = new RequestEditPresenter(this);
        findViews();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editRequest = (Request) bundle.getSerializable(Constant.REQUEST);
        }
        setData();
        setListener();
        setMenu();
    }
    @Override
    public void onClick(View view) {
        if (view == approveButton){
            updateStatusApproved(editRequest);
        }if (view == rejectButton){
            updateStatusReject(editRequest);
        }
    }
    public void updateStatusApproved(Request editRequest) {
        editRequest.setStatus("Aprovado");
        presenter.updateRequest(editRequest);
        finish();
    }
    public void updateStatusReject(Request editRequest) {
        editRequest.setStatus("Rejeitado");
        presenter.updateRequest(editRequest);
        finish();
    }
    public void setData() {
        fromEditText.setText(editRequest.getOrigem());
        toEditText.setText(editRequest.getDestino());
        dateEditText.setText(editRequest.getDataViagem());
        if (editRequest.getAnexoNotaFiscal() != null) {
            byte[] decodedBytes = Base64.decode(editRequest.getAnexoNotaFiscal(), Base64.DEFAULT);
            if (decodedBytes.length > 0) {
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                photoImageView.setImageBitmap(decodedBitmap);
            }
        }
        if (editRequest.getAnexoKmAntes() != null) {
            byte[] decodedBytes_2 = Base64.decode(editRequest.getAnexoKmAntes(), Base64.DEFAULT);
            if (decodedBytes_2.length > 0) {
                Bitmap decodedBitmap_2 = BitmapFactory.decodeByteArray(decodedBytes_2, 0, decodedBytes_2.length);
                photoImageViewKmB.setImageBitmap(decodedBitmap_2);
            }
        }
        if (editRequest.getAnexoKmDepois() != null) {
            byte[] decodedBytes_3 = Base64.decode(editRequest.getAnexoKmDepois(), Base64.DEFAULT);
            if (decodedBytes_3.length > 0) {
                Bitmap decodedBitmap_3 = BitmapFactory.decodeByteArray(decodedBytes_3, 0, decodedBytes_3.length);
                photoImageViewKmA.setImageBitmap(decodedBitmap_3);
            }
        }
        statusEditText.setText(editRequest.getStatus());
    }
    private void findViews(){
        fromEditText = findViewById(R.id.edittext_from);
        toEditText = findViewById(R.id.edittext_to);
        dateEditText = findViewById(R.id.edittext_date);
        statusEditText = findViewById(R.id.edittext_status);
        approveButton = findViewById(R.id.button_approve);
        rejectButton = findViewById(R.id.button_reject);
        photoImageView = findViewById(R.id.imgPhoto);
        photoImageViewKmB = findViewById(R.id.imgPhotoKmB);
        photoImageViewKmA = findViewById(R.id.imgPhotoKmA);
    }

    private void setListener(){
        approveButton.setOnClickListener(this);
        rejectButton.setOnClickListener(this);
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
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void close() {
    }
}