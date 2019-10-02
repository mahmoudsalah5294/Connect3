package com.mahmoudsalah.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.mahmoudsalah.connect3.R.raw.swosh;

public class MainActivity extends AppCompatActivity {
    int players = 0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winning = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    TextView textView;
    Button button;
    boolean gameactive = true;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button  = findViewById(R.id.button);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);


    }

    public void dropit(View view) {
        ImageView image = (ImageView) view;
        int counter = Integer.parseInt(image.getTag().toString());

        if (gamestate[counter] == 2 && gameactive) {
            image.setTranslationY(-1500);
            gamestate[counter] = players;
            if (players == 0) {
                image.setImageResource(R.drawable.redcircle2);
                mediaPlayer = MediaPlayer.create(this, swosh);
                mediaPlayer.start();
                players = 1;
            } else {
                image.setImageResource(R.drawable.bluecircle2);
                mediaPlayer = MediaPlayer.create(this, swosh);
                mediaPlayer.start();
                players = 0;
            }

            image.animate().translationYBy(1500).setDuration(300);

            for (int[] ints : winning) {
                if (gamestate[ints[0]] == gamestate[ints[1]] && gamestate[ints[1]] == gamestate[ints[2]] && gamestate[ints[0]] != 2) {

                    if (players == 1) {
                        textView.setText("The winning is red");
                        gameactive = false;
                        textView.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                        mediaPlayer = MediaPlayer.create(this,R.raw.dun);
                        mediaPlayer.start();
                    } else {

                        textView.setText("The winning is blue");
                        gameactive = false;
                        textView.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);
                        mediaPlayer = MediaPlayer.create(this,R.raw.dun);
                        mediaPlayer.start();
                    }
                }

            }



        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            mediaPlayer = MediaPlayer.create(this,R.raw.giggle);
            mediaPlayer.start();
        }
    }

    public void playagain(View view){
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        textView.setText("");
        gameactive =true;
        androidx.gridlayout.widget.GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView player = (ImageView)gridLayout.getChildAt(i);
            player.setImageDrawable(null);
        }
        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] =2;
        }
        players=0;

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}
