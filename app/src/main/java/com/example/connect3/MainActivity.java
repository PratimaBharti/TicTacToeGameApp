package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: yellow,1:red,2:empty
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameactive=true;

    public void playAgain(View view)
    {
        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView= (TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
              }
        activePlayer=0;
        for (int i=0;i<9;i++)
        gamestate[i]=2;
         gameactive=true;

    }

    public void dropIn(View view)
    {
        ImageView counter=(ImageView) view;
        int trappedCounter= Integer.parseInt(counter.getTag().toString());
        if(gamestate[trappedCounter]==2 && gameactive)
        {
            gamestate[trappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningposition : winningpositions) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2)
                {
                    gameactive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "yellow";
                    } else
                        winner = "red";


                   // Toast.makeText(this, winner + " won!!", Toast.LENGTH_SHORT).show();
                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView= (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won:)");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
