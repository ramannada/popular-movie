package com.blogspot.ramannada.popularmovieapp.auth;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blogspot.ramannada.popularmovieapp.R;

public class AuthActivity extends AppCompatActivity implements
        LoginFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AuthTheme);
        setContentView(R.layout.activity_auth);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new LoginFragment())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
