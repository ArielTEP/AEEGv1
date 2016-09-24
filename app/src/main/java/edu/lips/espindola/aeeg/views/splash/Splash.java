package edu.lips.espindola.aeeg.views.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import edu.lips.espindola.aeeg.R;
import edu.lips.espindola.aeeg.views.login.ui.LoginActivity;


public class Splash extends AppCompatActivity {

    private static final int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        launchSplash();
    }

    private void launchSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
