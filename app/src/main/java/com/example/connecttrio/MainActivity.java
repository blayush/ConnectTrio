package com.example.connecttrio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0:shubh 1:ash
    int i=0;
    int c=0;
    int[] gameposn={2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winposn={{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameactivity=true;
    public void dropin(View view){
        if(i==1){
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
            mediaPlayer.start();
        }
        else {
            MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.music2);
            mediaPlayer.start();
        }
        ImageView counter=(ImageView)view;

        int tappedbox= Integer.parseInt(counter.getTag().toString());

        if(gameposn[tappedbox]==2 && gameactivity){
            c++;
            gameposn[tappedbox] = i;
            counter.setTranslationY(-1500);
            if (i == 0) {
                counter.setImageResource(R.drawable.shubham);
                // Toast.makeText(this, "Next Player's Move", Toast.LENGTH_LONG).show();
                i = 1;
            }
            else {
                counter.setImageResource(R.drawable.ashwani);
                //Toast.makeText(this, "Next Player's Move", Toast.LENGTH_LONG).show();
                i = 0;
            }
            counter.animate().translationYBy(1500).rotationY(3600).setDuration(300);
            boolean flag=false;
            for (int[] winposns : winposn) {

                if (gameposn[winposns[0]] == gameposn[winposns[1]] && gameposn[winposns[1]] == gameposn[winposns[2]] && gameposn[winposns[0]] != 2) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.clapping);
                    mediaPlayer.start();
                      flag=true;
                    String winner="";
                    gameactivity=false;
                    if (i == 1) {
                        winner = "Shubh Won!";
                    } else {
                        winner = "ASS WON";
                    }

                    Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
                    TextView resulttextview = (TextView) findViewById((R.id.RESULTTEXT));
                    resulttextview.setText(winner);
                    playagainbutton.setVisibility(View.VISIBLE);
                    resulttextview.setVisibility(View.VISIBLE);
                     break;
                }
                if(c==9&&flag==false){
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.gamedrawn);
                    mediaPlayer.start();
                    Button playagainbutton = (Button) findViewById(R.id.playagainbutton);
                    TextView resulttextview = (TextView) findViewById((R.id.RESULTTEXT));
                    resulttextview.setText("Match Drawn!");
                    playagainbutton.setVisibility(View.VISIBLE);
                    resulttextview.setVisibility(View.VISIBLE);
                }


            }


        }
    }
    public void PlayAgain(View view){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.musicentry);
        mediaPlayer.start();
        c=0;
        Button playagainbutton=(Button)findViewById(R.id.playagainbutton);
        TextView resulttextview=(TextView)findViewById((R.id.RESULTTEXT));
        playagainbutton.setVisibility(View.INVISIBLE);
        resulttextview.setVisibility(View.INVISIBLE);
        GridLayout gridLayout= (GridLayout)findViewById(R.id.gridLayout);
        for(int j=0;j<gridLayout.getChildCount();j++){
            ImageView counter1=(ImageView)gridLayout.getChildAt(j);
            counter1.setImageDrawable(null);
        }
        Arrays.fill(gameposn, 2);
        i=0;
        gameactivity=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.entry);
        mediaPlayer.start();
        ImageView startimage=(ImageView)findViewById(R.id.startimage);
        startimage.animate().alpha(0).setDuration(4000);
    }
}