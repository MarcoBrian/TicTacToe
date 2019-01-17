package com.example.marcobrian.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0 ;
    //activePlayer: red = 1, yellow=0 , empty=2
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;

    public void dropIn(View view){
        ImageView image = (ImageView) view;
        int tappedimage = Integer.parseInt(image.getTag().toString());
        if(gameState[tappedimage]==2 && gameActive) {
            gameState[tappedimage] = activePlayer;
            image.setTranslationY(-1500);
            if (activePlayer == 0) {
                image.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                image.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            image.animate().translationYBy(1500).setDuration(300);
            for (int[] winningposition : winningpositions) {
                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2) {
                    gameActive=false;
                    String winner="";
                    if(activePlayer==1){
                        winner = "Yellow" ;
                    }
                    else if (activePlayer==0){
                        winner = "Red" ;
                    }
                    TextView winnertext = (TextView) findViewById(R.id.winnertext);
                    Button PlayAgain = (Button) findViewById(R.id.playagain);
                    winner = winner + " has won";
                    winnertext.setText(winner);
                    winnertext.setVisibility(View.VISIBLE);
                    PlayAgain.setVisibility(View.VISIBLE);
                }
            }

            if(gameActive
                    ==true){
                boolean MaxedOut = true;
                for(int i = 0 ; i < gameState.length;i++){
                    if(gameState[i]==2){
                        MaxedOut=false;
                    }
                }
                if (MaxedOut){
                    TextView winnertext = (TextView) findViewById(R.id.winnertext);
                    Button PlayAgain = (Button) findViewById(R.id.playagain);
                    winnertext.setText("Nobody  won");
                    winnertext.setVisibility(View.VISIBLE);
                    PlayAgain.setVisibility(View.VISIBLE);

                }

            }
        }

       /*** boolean MaxedOut = true;
        for(int i = 0 ; i < gameState.length;i++){
            if(gameState[i]==2){
                MaxedOut=false;
            }
        }
        if (MaxedOut){
            TextView winnertext = (TextView) findViewById(R.id.winnertext);
            Button PlayAgain = (Button) findViewById(R.id.playagain);
            winnertext.setText("Nobody  won");
            winnertext.setVisibility(View.VISIBLE);
            PlayAgain.setVisibility(View.VISIBLE);

        }*/

    }
    public void playagain(View view){
        TextView winnertext = (TextView) findViewById(R.id.winnertext);
        Button PlayAgain = (Button) findViewById(R.id.playagain);
        winnertext.setVisibility(View.INVISIBLE);
        PlayAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.board);
        for(int i = 0 ; i < gridLayout.getChildCount() ; i++ ){
            ImageView childimage =  (ImageView) gridLayout.getChildAt(i);
            childimage.setImageDrawable(null);
        }
        for(int i =0 ; i < gameState.length;i++){
            gameState[i]=2;
        }
        gameActive = true;
        activePlayer = 0 ;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
