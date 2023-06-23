package br.edu.ifsp.projetofinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.mvp.RequestAddMVP;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.presenter.RequestAddPresenter;
import br.edu.ifsp.projetofinal.presenter.RequestPresenter;
import br.edu.ifsp.projetofinal.utils.Constant;


public class RequestAddActivity extends AppCompatActivity implements RequestAddMVP.View, View.OnClickListener{
    private RequestAddMVP.Presenter presenter;
    private EditText fromEditText;
    private EditText toEditText;
    private TextView dateEditText;
    private EditText attachmentEditText;
    private EditText attachmentKmBEditText;
    private EditText attachmentKmAEditText;
    private Request request;
    private Button confirmButton;
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