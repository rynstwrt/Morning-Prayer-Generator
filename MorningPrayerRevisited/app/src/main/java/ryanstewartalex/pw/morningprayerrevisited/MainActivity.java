package ryanstewartalex.pw.morningprayerrevisited;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    RadioButton clergyPos1, clergyPos2, reading1, reading2, lesson1, lesson2, lesson3;
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
        lesson1 = (RadioButton) findViewById(R.id.lesson1);
        lesson2 = (RadioButton) findViewById(R.id.lesson2);
        lesson3 = (RadioButton) findViewById(R.id.lesson3);



    }

    public void generateTextButtonDown(View view) {

        Intent getIdleActivity = new Intent(MainActivity.this, IdlePage.class);

        getIdleActivity.putExtra("isPriest", clergyPos1.isChecked());
        getIdleActivity.putExtra("isJubilate", reading1.isChecked());
        getIdleActivity.putExtra("collectSpinner", collect.getSelectedItemPosition());
        getIdleActivity.putExtra("nTest", lesson1.isChecked());
        getIdleActivity.putExtra("oTest", lesson2.isChecked());
        getIdleActivity.putExtra("gospel", lesson3.isChecked());

        startActivity(getIdleActivity);
    }
}
