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
        br();br();

        if (cevents.inAdvent()) {
            pickTextWithVerse(R.array.openingadvent, R.array.openingadvent_chapters);
        } else if (cevents.isChristmas()) {
            pickTextWithVerse(R.array.openingchristmas, R.array.openingchristmas_chapters);
        } else if (cevents.isEpiphany()) {
            pickTextWithVerse(R.array.openingepiphany, R.array.openingepiphany_chapters);
        } else if (cevents.inLent()) {
            pickTextWithVerse(R.array.openinglent, R.array.openinglent_chapters);
        } else if (cevents.inHolyWeek()) {
            pickTextWithVerse(R.array.openingholyweek, R.array.openingholyweek_chapters);
        } else if (cevents.inEasterSeasonIncludingAscensionDay()) {
            gen(R.string.openingeasterasc_pt1);
            genItal(R.string.openingeasterasc_pt2);
            pickTextWithVerse(R.array.openingeasterasc, R.array.openingeasterasc_chapters);
        } else if (cevents.isTrinitySunday()) {
            gen(R.string.openingtrinity);
            genItal(R.string.revelation4_8);
        } else if (cevents.isAllSaints()) {
            pickTextWithVerse(R.array.openingsaint, R.array.openingsaint_chapters);
        } else if (cevents.isThanksgiving()) {
            gen(R.string.openingthanksgiving);
            genItal(R.string.psalm105_1);
        } else {
            pickTextWithVerse(R.array.openinganytime, R.array.openinganytime_chapters);
        }

        br();br();br();
        if (isPriest) {
            genBig(R.string.confofsin);
            br();br();
            genItal(R.string.officiantsays);
            br();br();
            pickText(R.array.confessionofsin_pt1_priest);
            br();br();
            genItal(R.string.silence);
            br();br();
            genItal(R.string.togetherkneel);
            br();br();
            gen(R.string.confessionofsin_pt2_priest);
            br();br();
            genItal(R.string.prieststand);
            br();br();
            gen(R.string.confessionofsin_pt3_priest);
        } else {
            genBig(R.string.confofsin);
            br();br();
            genItal(R.string.officiantsays);
            br();br();
            pickText(R.array.confessionofsin_pt1_other);
            br();br();
            genItal(R.string.silence);
            br();br();
            genItal(R.string.togetherkneel);
            br();br();
            gen(R.string.confessionofsin_pt2_other);
            br();br();
            genItal(R.string.remainkneeling);
            br();br();
            gen(R.string.confessionofsin_pt3_other);
        }

        br();br();br();
        genBig(R.string.invandsalt);
        br();br();
        genItal(R.string.stand);
        br();br();
        gen(R.string.invandsalt_pt1);
        br();
        gen(R.string.invandsalt_pt2);
        br();br();
        genItal(R.string.officiantandpeople);
        br();br();
        gen((cevents.inLent()) ? R.string.invandsalt_pt3_lent : R.string.invandsalt_pt3);

        br();br();br();
        if (isJubilate) {
            genBig(R.string.jubilate_title);
            br();br();
            gen(R.string.jubilate);
            br();
            genItal(R.string.psalm100);
        } else {
            genBig(R.string.venite_title);
            br();br();
            gen(R.string.venite);
            br();
            genItal(R.string.psalm95_1);
        }

        mainText.setText(Html.fromHtml(text));

    }

    private String[] idToArray(int in) {
        String[] s = getResources().getStringArray(in);
        return s;
    }

    private void br() {
        text += "<br/>";
    }

    private void pickTextWithVerse(int anum, int bnum) {
        String[] a = idToArray(anum);
        String[] b = idToArray(bnum);
        int r = ran.nextInt(a.length);
        gen(a[r]);
        genSmall(b[r]);
    }

    private void pickText(int anum) {
        String[] a = idToArray(anum);
        int r = ran.nextInt(a.length);
        gen(a[r]);
    }

    private void gen(String s) {
        text += s;
    }

    private void gen(int id) {
        text += getString(id);
    }

    private void genSmall(String s) {
        br();
        text += ("<small>" + s + "</small>");
    }

    private void genBig(int s) {
        String str = getString(s);
        text += ("<b>" + str + "</b>");
    }

    private void genItal(int s) {
        text += ("<i><small>" + getString(s) + "</small></i>");
    }


}
