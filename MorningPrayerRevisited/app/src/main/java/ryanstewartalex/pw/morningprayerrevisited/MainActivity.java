package ryanstewartalex.pw.morningprayerrevisited;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    RadioButton clergyPos1, clergyPos2, reading1, reading2;
    CheckBox lesson1, lesson2, lesson3;
    Spinner collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //variables
        Spinner collectSpinner = (Spinner) findViewById(R.id.collect_spinner);
        ArrayAdapter<CharSequence> collectAdapter = ArrayAdapter.createFromResource(this, R.array.collects, android.R.layout.simple_spinner_item);
        collectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        collectSpinner.setAdapter(collectAdapter);

        clergyPos1 = (RadioButton) findViewById(R.id.clergy1);
        clergyPos2 = (RadioButton) findViewById(R.id.clergy2);
        reading1 = (RadioButton) findViewById(R.id.reading1);
        reading2 = (RadioButton) findViewById(R.id.reading2);
        collect = (Spinner) findViewById(R.id.collect_spinner);
        lesson1 = (CheckBox) findViewById(R.id.lesson1);
        lesson2 = (CheckBox) findViewById(R.id.lesson2);
        lesson3 = (CheckBox) findViewById(R.id.lesson3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                AlertDialog ad = b.create();

                b.setTitle("About").setMessage("© Ryan Stewart 2017 held under proprietary license. Developed for Resurrection Episcopal Church at resurrectionplano.org.").setPositiveButton("Ok", (dialog, which) -> {
                    ad.cancel();
                });
                b.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void generateTextButtonDown(View view) {
        if ((clergyPos1.isChecked() || clergyPos2.isChecked()) && (reading1.isChecked() || reading2.isChecked()) && (lesson1.isChecked() || lesson2.isChecked() || lesson3.isChecked())) {
            Intent getIdleActivity = new Intent(MainActivity.this, IdlePage.class);

            getIdleActivity.putExtra("isPriest", clergyPos1.isChecked());
            getIdleActivity.putExtra("isJubilate", reading1.isChecked());
            getIdleActivity.putExtra("collectSpinner", collect.getSelectedItemPosition());
            getIdleActivity.putExtra("nTest", lesson1.isChecked());
            getIdleActivity.putExtra("oTest", lesson2.isChecked());
            getIdleActivity.putExtra("gospel", lesson3.isChecked());

            startActivity(getIdleActivity);
        } else {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            AlertDialog ad = b.create();

            b.setTitle("Error").setMessage("Please fill out all options.").setPositiveButton("Ok", (dialog, which) -> {
                ad.cancel();
            });
            b.show();
        }
    }
}
