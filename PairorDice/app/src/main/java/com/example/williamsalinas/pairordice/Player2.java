package com.example.williamsalinas.pairordice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Player2 extends Activity {


    private FrameLayout die1, die2;
    private Button roll, hold;

    //lab2
    private int p1_total;
    private int p2_total;
    private int count = 0;
    private TextView p1;
    private TextView p2;
    private TextView round;
    private String text;

    private int p2_temp_total;
    //lab2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);

        //get p1 and p2 total
        Intent intent = getIntent();
        p1_total = intent.getIntExtra("p1_total",0);
        p2_total = intent.getIntExtra("p2_total",0);
        //Toast.makeText(this,"The score is : " + score, Toast.LENGTH_LONG).show();


        roll = (Button)findViewById(R.id.button);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Player2.this,MainActivity.class);

                p2_total += p2_temp_total;

                intent.putExtra("p1_total",p1_total);
                intent.putExtra("p2_total",p2_total);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }

        });


        //lab2
        p1 = (TextView)findViewById(R.id.p1);
        String p1_total_points = ""+ p1_total+ "";
        p1.append(p1_total_points);

        p2 = (TextView)findViewById(R.id.p2);
        String p2_total_points = ""+ p2_total + "";
        p2.append(p2_total_points);

        //lab2

        die1 = (FrameLayout)findViewById(R.id.die1);
        die2 = (FrameLayout)findViewById(R.id.die2);

        //lab2
        round = (TextView)findViewById(R.id.round);
        //lab2



    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice(){
        int val1 = 1 + (int)(6*Math.random());
        int val2 = 1 + (int)(6*Math.random());

        setDie(val1,die1);
        setDie(val2,die2);

        //start of lab2

        if(val1 == 1 && val2 == 1){

            p2_total = 0;
            text = "Round:" + count;

            //go to Player2 activity
            Intent intent = new Intent(Player2.this,MainActivity.class);
            intent.putExtra("p1_total",p1_total);
            intent.putExtra("p2_total",p2_total);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);


        }else if (val1 == 1 || val2 == 1){

            p2_total += 0;
            text = "Round: " + count;

            //go to Player2 activity
            Intent intent = new Intent(Player2.this,MainActivity.class);
            intent.putExtra("p1_total",p1_total);
            intent.putExtra("p2_total",p2_total);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);

        }else{
            p2_temp_total += val1+val2;
            count += 1;

            text = "Round: " + count;

            int temp2 = p2_total + p2_temp_total;
            //if over 100 then you win
            if (temp2 >= 100){

                AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                alertDialog.setTitle("You won player 2!");
                alertDialog.setMessage("Yipeeaieahhh!");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                });

                alertDialog.show();

            }



        }
        round.setText(text);


    }

    //set the appropiate picture for each die per int
    public void setDie(int value, FrameLayout layout){
        Drawable pic =null;

        switch (value){
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }

        layout.setBackground(pic);
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
