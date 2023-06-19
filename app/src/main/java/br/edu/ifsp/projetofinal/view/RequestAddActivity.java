package br.edu.ifsp.projetofinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.presenter.RequestPresenter;


public class RequestAddActivity extends AppCompatActivity implements RequestMVP.View, View.OnClickListener{

    private RequestMVP.Presenter presenter;
    private EditText fromEditText;
    private EditText toEditText;
    private EditText dateEditText;
    private EditText attachmentEditText;
    private EditText attachmentKmBEditText;
    private EditText attachmentKmAEditText;
    private EditText status;
    private Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_add);
        presenter = new RequestPresenter(this);
        findViews();
        setListener();
        setToolbar();
    }
    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }
    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onClick(View view) {
        if (view == confirmButton){
            presenter.saveNewRequest(fromEditText.getText().toString(),
                    toEditText.getText().toString(),
                    dateEditText.getText().toString(),
                    attachmentEditText.getText().toString(),
                    attachmentKmBEditText.getText().toString(),
                    attachmentKmAEditText.getText().toString(),
                    status.getText().toString());
        }
    }
    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void close() {
        presenter.deatach();
        finish();
    }
    private void findViews(){
        fromEditText = findViewById(R.id.edittext_from);
        toEditText = findViewById(R.id.edittext_to);
        dateEditText = findViewById(R.id.edittext_date);
        attachmentEditText = findViewById(R.id.edittext_attachment);
        attachmentKmBEditText = findViewById(R.id.edittext_attachment_km_b);
        attachmentKmAEditText = findViewById(R.id.edittext_attachment_km_a);
        status = findViewById(R.id.edittext_status);
        confirmButton = findViewById(R.id.button_save_request);
    }

    private void setListener(){
        confirmButton.setOnClickListener(this);
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
}