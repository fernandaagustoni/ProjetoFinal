package br.edu.ifsp.projetofinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.presenter.RequestPresenter;

public class RequestActivity extends AppCompatActivity implements RequestMVP.View{
    private RequestMVP.Presenter presenter;
    private FloatingActionButton createNewRequestButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        findViews();
        setListener();
        presenter = new RequestPresenter(this);
    }
    protected void onStart() {
        super.onStart();
        presenter.populateList(recyclerView);
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
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void close() {

    }
    private void findViews(){
        this.createNewRequestButton = findViewById(R.id.btn_new_request);
        recyclerView = findViewById(R.id.recyler_view);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setListener(){
        createNewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openNewRequest();
            }
        });
    }
}