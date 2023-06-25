package br.edu.ifsp.projetofinal.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.mvp.LoginMVP;
import br.edu.ifsp.projetofinal.presenter.LoginPresenter;
import br.edu.ifsp.projetofinal.utils.Constant;

public class MainActivity extends AppCompatActivity implements LoginMVP.View{
    private LoginMVP.Presenter presenter;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private CheckBox mPreferences;
    private Button confirmButton;
    private FloatingActionButton createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LoginPresenter(this);
        findViews();
        setListener();
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.USER_PREFERENCES, Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt(Constant.ID_USER, -1);
        if (userId != -1) {
            String username = sharedPreferences.getString(Constant.USERNAME, "");
            String password = sharedPreferences.getString(Constant.PASSWORD, "");
            presenter.autenticate(username, password, true);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
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
    private void findViews(){
        this.usernameEditText = findViewById(R.id.edittext_username);
        this.passwordEditText = findViewById(R.id.edittext_password);
        this.mPreferences = findViewById(R.id.check_remember_login);
        this.confirmButton = findViewById(R.id.button_login);
        this.createUserButton = findViewById(R.id.btn_add_user);
    }
    private void setListener(){
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.autenticate(usernameEditText.getText().toString(), passwordEditText.getText().toString(), mPreferences.isChecked())) {
                    Toast.makeText(getContext(), "Usuário logado com Sucesso!", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getContext(), "Dados inválidos!", Toast.LENGTH_LONG).show();
                }
            }
        });
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openSignUp();
            }
        });
    }
}