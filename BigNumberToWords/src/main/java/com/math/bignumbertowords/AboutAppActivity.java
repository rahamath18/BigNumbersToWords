package com.math.bignumbertowords;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AboutAppActivity extends AppCompatActivity {

    //Button btnCloseApp;
    TextView txtViewAboutApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Toast toast = Toast.makeText(getApplicationContext(), Html.fromHtml("<font color='green' ><b>"
                + "Welcome to About App section" + "</b></font>"), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        txtViewAboutApp = (TextView)findViewById(R.id.txtViewAboutApp);
        txtViewAboutApp.setText(Html.fromHtml(getString(R.string.about_app_section)));

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       /* btnCloseApp = (Button)findViewById(R.id.btnCloseApp);

        btnCloseApp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MyConstant.isApplicationTerminated = true;
                finish();
            }
        });*/
    }

}
