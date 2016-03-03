package com.vishnugt.speedreader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et, et2;
    TextView tv;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView);
        et2=(EditText)findViewById(R.id.editText2);
        bt = (Button)findViewById(R.id.button);
    }



    String temp[];
    int i=0;
    int speed;
    String iterator="";
    int j=0;
    boolean isExecuted;

    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void function_to_call(View v)
    {

        if(isExecuted==true)
        {
            isExecuted=false;
            bt.setText("SPEED READ!");
        }

        else {


            isExecuted = true;
            j = 0;
            i = 0;
            iterator = "";
            String text = et.getText().toString();


            if (isEmpty(et)) {
                Toast.makeText(this, "Please enter some text!", Toast.LENGTH_SHORT).show();

            }
            if (isEmpty(et2)) {
                speed = 500;
                Toast.makeText(this, "Speed set to a default value of 500!", Toast.LENGTH_SHORT).show();

            } else
                speed = Integer.parseInt(et2.getText().toString());
            ;

            temp = text.split(" ");

            Thread t = new Thread() {

                @Override
                public void run() {
                    try {
                        while (!isInterrupted() && isExecuted) {
                            Thread.sleep(speed);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    j = i++;
                                    if (j + 1 > temp.length) {
                                        isExecuted = false;
                                    } else {
                                        iterator = temp[j];
                                        tv.setText(iterator);
                                    }
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };

            t.start();

            bt.setText("STOP!");
        }


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

        return super.onOptionsItemSelected(item);
    }
}
