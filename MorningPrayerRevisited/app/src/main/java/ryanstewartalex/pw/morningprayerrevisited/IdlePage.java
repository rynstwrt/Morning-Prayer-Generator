package ryanstewartalex.pw.morningprayerrevisited;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class IdlePage extends AppCompatActivity {

    DateTime date;
    Document doc;
    boolean isPriest, isJubilate, isNTest, isOTest, isGospel;
    int collectSpinnerChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_page);

        //date = new DateTime();
        date = new DateTime(2017, 6, 28, 10, 30);

        isPriest = getIntent().getBooleanExtra("isPriest", true);
        isJubilate = getIntent().getBooleanExtra("isJubilate", true);
        isNTest = getIntent().getBooleanExtra("nTest", false);
        isOTest = getIntent().getBooleanExtra("oTest", false);
        isGospel = getIntent().getBooleanExtra("gospel", false);
        collectSpinnerChoice = getIntent().getIntExtra("collectSpinner", 0);

        new JsoupAsyncTask().execute();
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        String url;

        JsoupAsyncTask() {
            String[] engMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            url = "http://www.missionstclare.com/english/" + engMonths[date.getMonthOfYear() - 1] + "/morning/" + date.getDayOfMonth() + "m.html";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                doc = Jsoup.connect(url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            GlobalDocument.doc = doc;

            Intent getGenActivity = new Intent(IdlePage.this, GeneratedPage.class);

            getGenActivity.putExtra("isPriest", isPriest);
            getGenActivity.putExtra("isJubilate", isJubilate);
            getGenActivity.putExtra("collectSpinner", collectSpinnerChoice);
            getGenActivity.putExtra("nTest", isNTest);
            getGenActivity.putExtra("oTest", isOTest);
            getGenActivity.putExtra("gospel", isGospel);

            startActivity(getGenActivity);
            finish();
        }

    }
}
