package br.edu.ifsp.projetofinal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.mvp.UserAddMVP;
import br.edu.ifsp.projetofinal.presenter.UserAddPresenter;
import br.edu.ifsp.projetofinal.utils.Cryptography;

public class UserAddActivity extends AppCompatActivity implements UserAddMVP.View, View.OnClickListener{
    private UserAddMVP.Presenter presenter;
    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        presenter = new UserAddPresenter(this);
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
    public void onClick(View view) {
        if (view == confirmButton){
            if (presenter.checkPassword(passwordEditText.getText().toString(), confirmPasswordEditText.getText().toString())){
                saveUser();
            } else{
                Toast.makeText(this, "As senhas não estão iguais.", Toast.LENGTH_SHORT).show();
            }
        }
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
        presenter.deatach();
        finish();
    }
    private void findViews(){
        fullNameEditText = findViewById(R.id.edittext_full_name);
        emailEditText = findViewById(R.id.edittext_email);
        usernameEditText = findViewById(R.id.edittext_new_username);
        passwordEditText = findViewById(R.id.edittext_new_password);
        confirmPasswordEditText = findViewById(R.id.edittext_new_confirm_password);
        confirmButton = findViewById(R.id.button_save_user);
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

    public void saveUser() {
        User user = new User();
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        user.setFullname(fullNameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        presenter.registerUser(user);
        finish();
    }
}