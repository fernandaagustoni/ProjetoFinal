package br.edu.ifsp.projetofinal.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import br.edu.ifsp.projetofinal.R;
import br.edu.ifsp.projetofinal.model.entities.Request;
import br.edu.ifsp.projetofinal.mvp.RequestMVP;
import br.edu.ifsp.projetofinal.presenter.RequestPresenter;
import br.edu.ifsp.projetofinal.utils.Constant;

public class RequestActivity extends AppCompatActivity implements RequestMVP.View{
    private RequestMVP.Presenter presenter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FloatingActionButton createNewRequestButton;
    private RecyclerView recyclerView;
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        findViews();
        setListener();
        presenter = new RequestPresenter(this);
        showMenu();
    }
    @Override
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
    public Request getRequest() {
        return request;
    }

    @Override
    public void close() {

    }
    private void findViews(){
        this.createNewRequestButton = findViewById(R.id.btn_new_request);
        drawerLayout = findViewById(R.id.activity_request);
        recyclerView = findViewById(R.id.recyler_view);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void setListener(){
        createNewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openNewRequest();
            }
        });
    }
    @Override
    public void setMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showMenu() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    int itemId = menuItem.getItemId();
                    Intent intentItem;
                    switch (itemId) {
                        case R.id.nav_item1:
                            intentItem = new Intent(this, RequestActivity.class);
                            startActivity(intentItem);
                            break;

                        case R.id.nav_item2:
                            intentItem = new Intent(this, RequestAddActivity.class);
                            startActivity(intentItem);
                            break;

                        case R.id.nav_item3:
                            intentItem = new Intent(this, UserEditActivity.class);
                            startActivity(intentItem);
                            break;

                        case R.id.nav_item4:
                            logout();
                            intentItem = new Intent(this,MainActivity.class);
                            startActivity(intentItem);
                            break;
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                });
    }

    public void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.USER_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Constant.ID_USER);
        editor.remove(Constant.USERNAME);
        editor.remove(Constant.PASSWORD);
        editor.apply();
    }
}