package br.edu.ifsp.projetofinal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;

public class RequestActivity extends AppCompatActivity implements RequestMVP.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}