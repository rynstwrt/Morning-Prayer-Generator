package ryanstewartalex.pw.morningprayerrevisited;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Random;


public class GeneratedPage extends AppCompatActivity{

    ChristianEvents cevents;
    TextView mainText;
    boolean isPriest;
    boolean isJubilate;
    int collectSpinnerChoice;
    Random ran;

    String text = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generated_page);

        ran = new Random();
        cevents = new ChristianEvents();
        mainText = (TextView) findViewById(R.id.mainText);


        isPriest = getIntent().getBooleanExtra("isPriest", true);
        isJubilate = getIntent().getBooleanExtra("isJubilate", true);
        collectSpinnerChoice = getIntent().getIntExtra("collectSpinner", 0);

        //start generation
        genBig(R.string.opensent);

        if (cevents.inAdvent()) {
            String[] a = idToArray(R.array.openingadvent);
            String[] b = idToArray(R.array.openingadvent_chapters);
            int r = ran.nextInt(a.length);
            gen(a[r]);
            genSmall(b[r]);
            genSmall();
        }


        mainText.setText(Html.fromHtml(text));

    }

    private String[] idToArray(int in) {
        String[] s = getResources().getStringArray(in);
        return s;
    }

    private void gen(String s) {
        text += s;
        text += "<br/>";
    }

    private void genSmall(String s) {
        text += "  ";
        text += ("<small>" + s + "</small>");
        text += "<br/><br/>";
    }

    private void genSmall() {
        text += "<br/><br/>";
    }

    private void genBig(int s) {
        String str = getString(s);
        text += ("<b>" + str + "</b>");
        text += "<br/><br/>";
    }

    private void genItal(int s) {
        text += ("<i>" + getString(s) + "</i>");
    }


}
