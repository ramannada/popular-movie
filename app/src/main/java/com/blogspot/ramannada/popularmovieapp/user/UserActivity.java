package com.blogspot.ramannada.popularmovieapp.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.ramannada.popularmovieapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {
    @BindView(R.id.et_username) EditText etName;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.btn_save) Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                etName.setEnabled(true);
                etEmail.setEnabled(true);
                btnSave.setVisibility(View.VISIBLE);
                return true;
            case R.id.action_change_password:
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
