package com.example.mrb.threadexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{

    TextView txtvwDisplay;
    DrawingArea drawBox;
    Button btnGo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvwDisplay = (TextView) findViewById(R.id.txtvwDisplay);
        drawBox = (DrawingArea) findViewById(R.id.drawBox);
        btnGo = (Button) findViewById(R.id.btnGo);

    }

    public void onClick(View v)
    {
        new CountingTask().execute();

        //long lngFib = 70;
        //new FibonacciTask().execute(lngFib);

        //new AnimationTask().execute();

    }


    private class AnimationTask extends AsyncTask<Void, Void, Void>
    {
        // NOTE: Android now has dedicated classes for handling different types of Animations.
        //       The Code here does work but is not optimal for a modern app.

        boolean blnAnimationDone;

        @Override
        protected void onPreExecute()
        {
            blnAnimationDone = false;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            while (!blnAnimationDone)
            {
                // The Code below forces the background thread to wait 10 milliseconds
                try
                {
                    Thread.sleep(10);
                }
                catch(InterruptedException ie)
                {

                }

                // The Code below calls the onProgressUpdate() method
                publishProgress();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... params)
        {
            // If the right edge of the rectangle is <= the width of drawBox
            if (drawBox.getFltRight() <= drawBox.getWidth())
            {
                drawBox.moveRight(2);
            }
            else
            {
                blnAnimationDone = true;
            }
        }

    }

    private class FibonacciTask extends AsyncTask<Long, Long, Long>
    {

        @Override
        protected void onPreExecute()
        {
            txtvwDisplay.setText("");
        }

        @Override
        protected Long doInBackground(Long... params)
        {
            return fibonacci(params[0]);
        }

        @Override
        protected void onProgressUpdate(Long... values)
        {
            txtvwDisplay.append(values[0].toString() + " ");
        }

        private long fibonacci(long lngInput)
        {
            long lngResult = 0;

            long lngLastFib = 1;
            long lngSecondLastFib = 1;

            if ((lngInput == 1) || (lngInput == 2))
            {
                lngResult = 1;
            }
            else
            {
                publishProgress(Long.parseLong("1"));
                publishProgress(Long.parseLong("1"));

                for (int i = 3; i <= lngInput; i++)
                {
                    lngResult = lngSecondLastFib + lngLastFib;

                    publishProgress(lngResult);

                    lngSecondLastFib = lngLastFib;
                    lngLastFib = lngResult;
                }
            }

            return lngResult;
        }

    }


    private class CountingTask extends AsyncTask<Void, Integer, Boolean>
    {

        @Override
        protected Boolean doInBackground(Void... params)
        {
            for(int i = 1; i < 1000000; i++)
            {
                // If the counting value of i is divisible by 100
                if(i % 100 == 0)
                {

                    // The Code below forces the background thread to wait 100 milliseconds
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch(InterruptedException ie)
                    {

                    }
                    // The Code below calls the onProgressUpdate() method, which runs on the UI thread.
                    publishProgress(i);
                }
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            txtvwDisplay.setText(values[0].toString());
        }

    }


}
