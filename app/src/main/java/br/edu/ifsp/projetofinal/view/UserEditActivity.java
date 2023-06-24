package br.edu.ifsp.projetofinal.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.User;
import br.edu.ifsp.projetofinal.mvp.UserEditMVP;
import br.edu.ifsp.projetofinal.presenter.UserEditPresenter;
import br.edu.ifsp.projetofinal.utils.UserSession;

public class UserEditActivity extends AppCompatActivity implements UserEditMVP.View, View.OnClickListener{
    private UserEditMVP.Presenter presenter;
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
        presenter = new UserEditPresenter(this);
        findViews();
        setListener();
        setToolbar();
        setData();
    }
    public void setData() {
        fullNameEditText.setText(UserSession.getInstance().getUser().getFullname());
        emailEditText.setText(UserSession.getInstance().getUser().getEmail());
        usernameEditText.setText(UserSession.getInstance().getUser().getUsername());
        passwordEditText.setText(UserSession.getInstance().getUser().getPassword());
        confirmPasswordEditText.setText(UserSession.getInstance().getUser().getPassword());
    }
    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }
    @Override
    public void onClick(View view) {
        if (view == confirmButton){
            changeUser();
        }
    }

    @Override
    public Context getContext() {
        return this;
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
    public void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void changeUser() {
        User user = new User();
        user.setUsername(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        user.setFullname(fullNameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        presenter.editUser(user);
        finish();
    }
}