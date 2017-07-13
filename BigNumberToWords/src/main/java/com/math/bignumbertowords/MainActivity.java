package com.math.bignumbertowords;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtNumToConvert;
    Button btnWestern;
    Button btnIndian;
    Button btnClear;
    EditText txtWordConverted;
    Button btnCopyToClipBoard;
    Button btnCloseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumToConvert = (EditText)findViewById(R.id.txtNumToConvert);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnWestern = (Button)findViewById(R.id.btnWestern);
        btnIndian = (Button)findViewById(R.id.btnIndian);
        txtWordConverted = (EditText)findViewById(R.id.txtWordConverted);
        btnCopyToClipBoard = (Button)findViewById(R.id.btnCopyToClipBoard);
        btnCloseApp = (Button)findViewById(R.id.btnCloseApp);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnWestern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            String mynum1 = txtNumToConvert.getText().toString();
            if(TextUtils.isEmpty(txtNumToConvert.getText())) {
                showToastValidationMsg("Enter a valid number");
                return;
            }
            try {
                NumberUtil.isValidNumber(mynum1);
            } catch (Exception e) {
                showToastValidationMsg("Enter a valid number");
            }
            txtWordConverted.setText(NumberToWordWestern.convertToNumberName(mynum1));
            }
        });

        btnIndian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String mynum1 = txtNumToConvert.getText().toString();
                if(TextUtils.isEmpty(txtNumToConvert.getText())) {
                    showToastValidationMsg("Enter a valid number");
                    return;
                }
                try {
                    NumberUtil.isValidNumber(mynum1);
                } catch (Exception e) {
                    showToastValidationMsg("Enter a valid number");
                }
                txtWordConverted.setText(NumberToWordIndian.convertToNumberName(mynum1));

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtNumToConvert.setText("");
                txtWordConverted.setText("");
            }
        });

        btnCopyToClipBoard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("numberToWords", txtWordConverted.getText());
                clipboard.setPrimaryClip(clip);
                showToastValidationMsg("Above words conversion copied into clipboard.");
            }
        });

        btnCloseApp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            finish();
                System.exit(0);
            }
        });

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    private void showToastValidationMsg(String s) {
        Toast toast = Toast.makeText(getApplicationContext(), Html.fromHtml("<font color='green' ><b>"
                + s + "</b></font>"), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.action_settings) {
            return true;
       }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about_us)
        {
            // Start AboutUs activity here
            //setContentView(R.layout.aboutus);
            Log.d("About App", "Button clicked!");
            Intent intent = new Intent(this, AboutAppActivity.class);
            this.startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        if(MyConstant.isApplicationTerminated){
            finish();
        }
        super.onResume();
    }
}
