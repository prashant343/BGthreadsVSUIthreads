package iotapps.bgthreadsvsuithreads;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button bgthread;
    Button uithread;
    TextView uitext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgthread = (Button) findViewById(R.id.bgbutton);
        uitext = (TextView) findViewById(R.id.textView2);
        




        bgthread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // A thread which prints numbers in console.
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            int i =0;
                            while(true) {
                                i = i +1;
                                sleep(1000);
                                Log.d("No.s -->", String.valueOf(i));

                                // Application crashes if we use the line below.

                                 // uitext.setText(i);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();


            }
        });




        uithread = (Button) findViewById(R.id.uithread);
        uithread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Now we will see the Asynch task which can update the UI

                new UIwork().execute();


            }
        });






    }
    private class UIwork extends AsyncTask<String, Void, String> {
        int j = 0;
        TextView txt = (TextView) findViewById(R.id.textView2);


        @Override
        protected String doInBackground(String... params) {

            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    j = j+1;
                    Log.d("UIthread",String.valueOf(j));


                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.textView2);
            txt.setText("Executed"); // txt.setText(result);

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }
}
