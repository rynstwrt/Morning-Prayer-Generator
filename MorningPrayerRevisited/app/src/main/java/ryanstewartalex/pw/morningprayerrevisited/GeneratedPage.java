package ryanstewartalex.pw.morningprayerrevisited;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class GeneratedPage extends AppCompatActivity{

    ChristianEvents cevents;

    Document doc;
    Date date;

    TextView mainText;
    boolean isPriest, isJubilate, isNTest, isOTest, isGospel;
    int collectSpinnerChoice;
    Random ran;

    String text = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.generated_page);

        doc = GlobalDocument.doc;

        ran = new Random();
        cevents = new ChristianEvents();
        mainText = (TextView) findViewById(R.id.mainText);

        isPriest = getIntent().getBooleanExtra("isPriest", true);
        isJubilate = getIntent().getBooleanExtra("isJubilate", true);
        isNTest = getIntent().getBooleanExtra("nTest", false);
        isOTest = getIntent().getBooleanExtra("oTest", false);
        isGospel = getIntent().getBooleanExtra("gospel", false);
        collectSpinnerChoice = getIntent().getIntExtra("collectSpinner", 0);

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
            br();
            genItal(R.string.revelation4_8);
        } else if (cevents.isAllSaints()) {
            pickTextWithVerse(R.array.openingsaint, R.array.openingsaint_chapters);
        } else if (cevents.isThanksgiving()) {
            gen(R.string.openingthanksgiving);
            br();
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
            br();
            br();
            gen(R.string.venite);
            br();
            genItal(R.string.psalm95_1);
        }

        br();br();
        if (cevents.isAllSaints()) {
            if (cevents.inEasterSeasonIncludingAscensionDay()) {
                gen(R.string.antiphon_allsaints_easter);
            } else {
                gen(R.string.antiphon_allsaints);
            }
        } else if (cevents.isChristmas()) { //feast of incarnation
            if (cevents.inEasterSeasonIncludingAscensionDay()) {
                gen(R.string.antiphon_incarnation_easter);
            } else {
                gen(R.string.antiphon_incarnation);
            }
        } else {
            pickText(R.array.antiphon_otherdays);
        }

        br();br();br();

        doc.select("p > a").remove();
        doc.select("p > em").remove();
        doc.select("p > br").remove();

        List<Object> bodyList = new ArrayList<>(Arrays.asList(doc.body().getAllElements().toArray()));
        Element lastItemPsalm = doc.select("h1#Psalms ~ audio").first();
        List<List<Object>> psalms = new ArrayList<>();
        List<String> psalmTitles = new ArrayList<>();
        Elements afterStrongsPsalm = doc.select("h1#Psalms ~ p > strong,h1#Psalms ~ p > h1,h1#Psalms ~ h1");

        for (int i = 0; i < afterStrongsPsalm.size(); i++) {
            if (bodyList.indexOf(afterStrongsPsalm.get(i)) < bodyList.indexOf(lastItemPsalm)) {
                List<Object> currentPsalm;
                if (bodyList.indexOf(afterStrongsPsalm.get(i + 1)) < bodyList.indexOf(lastItemPsalm)) {
                    currentPsalm = bodyList.subList(bodyList.indexOf(afterStrongsPsalm.get(i)), bodyList.indexOf(afterStrongsPsalm.get(i + 1)) - 1);
                } else {
                    currentPsalm = bodyList.subList(bodyList.indexOf(afterStrongsPsalm.get(i)), bodyList.indexOf(lastItemPsalm));
                }
                psalms.add(currentPsalm);
            }
        }

        for (List<Object> lo : psalms) {
            Element firstItem = (Element) lo.get(0);
            psalmTitles.add(firstItem.text());
            List<Object> originalLo = lo;
            lo = lo.subList(1, lo.size());

            genBig(firstItem.text());
            br();br();

            for (Object o : lo) {
                Element e = (Element) o;
                e.text(e.text().replaceAll("\\d+\\s?([A-Za-z]+)", "$1"));
                gen(e.text());
                if (lo.indexOf(lo.get(lo.size() - 1)) != lo.indexOf(o)) { //if not the last one
                    br();br();
                }
            }
            //if not last psalm, br() x3.
            if (psalms.indexOf(originalLo) != psalms.size() - 1) {
                br();br();br();
            }
        }


        br();br();
        gen(R.string.endofpsalmsappointed);

        br();br();br();

        Element lastItem = doc.select("h1#Lessons3 ~ #Essay").first();
        List<List<Object>> lessons = new ArrayList<>();
        List<String> lessonTitles = new ArrayList<>();
        Elements afterStrongsLesson = doc.select("#Lessons3 ~ h1,#Lessons3 ~ strong,#Lessons3 ~ b");

        for (int i = 0; i < afterStrongsLesson.size(); i++) {
            if (bodyList.indexOf(afterStrongsLesson.get(i)) < bodyList.indexOf(lastItem) && afterStrongsLesson.get(i).text().matches("The \\w+ Testament Lesson|The Gospel")) {
                List<Object> currentLesson;

                try {
                    if (bodyList.indexOf(afterStrongsLesson.get(i + 1)) < bodyList.indexOf(lastItem)) {
                        currentLesson = bodyList.subList(bodyList.indexOf(afterStrongsLesson.get(i)) + 1, bodyList.indexOf(afterStrongsLesson.get(i + 1)));
                    } else {
                        currentLesson = bodyList.subList(bodyList.indexOf(afterStrongsLesson.get(i)) + 1, bodyList.indexOf(lastItem));
                    }
                }catch(IndexOutOfBoundsException e) {
                    currentLesson = bodyList.subList(bodyList.indexOf(afterStrongsLesson.get(i)) + 1, bodyList.indexOf(lastItem));
                }
                lessons.add(currentLesson);
            }
        }

        List<List<Object>> selectedLessons = new ArrayList<>();
        if (isNTest) { selectedLessons.add(lessons.get(0)); }
        if (isOTest) { selectedLessons.add(lessons.get(1)); }
        if (isGospel) { selectedLessons.add(lessons.get(2)); }

        for (List<Object> lo : selectedLessons) {
            Element firstItem = (Element) lo.get(1);
            lessonTitles.add(firstItem.text());
            List<Object> originalLo = lo;
            lo = lo.subList(2, lo.size());

            genBig(firstItem.text());
            br();br();

            for (Object o : lo) {
                Element e = (Element) o;
                e.text(e.text().replaceAll("\\d+\\s?([A-Za-z]+)", "$1"));
                gen(e.text());
                br();br();
            }
            pickText(R.array.canticle);
            //if not last lesson, br() x3.
            if (selectedLessons.indexOf(originalLo) != selectedLessons.size() - 1) {
                br();br();br();
            }
        }


        br();br();br();
        genBig(R.string.creed);
        br();br();
        genItal(R.string.officiantandpeople);
        br();br();
        gen(R.string.apostlescreed);

        br();br();br();
        genBig(R.string.prayers);
        br();br();
        genItal(R.string.standorkneel);
        br();br();
        gen(R.string.theprayers_pt1);
        br();
        gen(R.string.theprayers_pt2);
        br();
        gen(R.string.theprayers_pt3);
        br();br();
        genItal(R.string.officiantandpeople);
        br();br();
        if (isPriest)
            gen(R.string.theprayers_pt4_priest);
        else
            gen(R.string.theprayers_pt4_other);

        br();br();br();
        genBig(R.string.collectday);
        br();br();
        switch (collectSpinnerChoice) {
            case 0:
                gen(R.string.collect_sundays);
                break;
            case 1:
                gen(R.string.collect_saturdays);
                break;
            case 2:
                gen(R.string.collect_fridays);
                break;
            case 3:
                gen(R.string.collect_peace);
                break;
            case 4:
                gen(R.string.collect_renewaloflife);
                break;
            case 5:
                gen(R.string.collect_grace);
                break;
            case 6:
                gen(R.string.collect_guidance);
                break;
        }
        br();br();
        pickText(R.array.collect_endings);

        br();br();br();
        genBig(R.string.thanksgiving);
        br();br();
        genItal(R.string.officiantandpeople);
        br();br();
        gen(R.string.thanksgiving_pt1);
        br();br();
        gen(R.string.thanksgiving_pt2);
        br();
        genItal(R.string.thanksgiving_pt3);



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

    private void genBig(String s) {
        text += ("<b>" + s + "</b>");
    }

    private void genItal(int s) {
        text += ("<i><small>" + getString(s) + "</small></i>");
    }





}