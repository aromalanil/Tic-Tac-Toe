package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int yellow=0,red=0;
    //Yellow :1 Red: 0
    int currentPlayer=0;
    // No : 2 Clicked by yellow : 1, clicked by Red :0
    int[] isClicked = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean won=false;
    boolean isDraw=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void coinClick(View view)
    {
        ImageView coin = (ImageView) view;
        TextView redScore = (TextView) findViewById(R.id.redScore);
        TextView yellowScore = (TextView) findViewById(R.id.yellowScore);
        int position = Integer.parseInt(coin.getTag().toString());
        if(isClicked[position]==2&&!won&&!isDraw)
        {
            if (currentPlayer == 1) {
                coin.setImageResource(R.drawable.yellow_coin);
                isClicked[position] = currentPlayer;
                currentPlayer = 0;

            } else if(currentPlayer==0&!won&&!isDraw) {
                coin.setImageResource(R.drawable.red_coin);
                isClicked[position] = currentPlayer;
                currentPlayer = 1;
            }
            coin.setAlpha(1f);
        }
        for(int wp[]: winningPositions)
        {
            if(isClicked[wp[0]]==isClicked[wp[1]]&&isClicked[wp[1]]==isClicked[wp[2]]&&isClicked[wp[0]]!=2)
            {
                won=true;
             if(isClicked[wp[0]]==1)
             {
                 showDialog("Yellow Wins!");
                 yellow++;
                 yellowScore.setText(Integer.toString(yellow));
             }
             else
             {
                 showDialog("Red Wins!");
                 red++;
                 redScore.setText(Integer.toString(red));
             }
            }
        }
        isDraw=true;
        for(int i=0;i<isClicked.length;i++)
        {
            if(isClicked[i]==2)
            {
                isDraw=false;
            }
        }
        if(isDraw)
        {
            showDialog("It's a Draw !");
        }


    }

    public void showDialog(String message)
    {
        findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
        TextView msg = (TextView) findViewById(R.id.messageText);
        msg.setText(message);



    }

    public void playAgain(View view)
    {
        findViewById(R.id.linearLayout).setVisibility(View.INVISIBLE);
        GridLayout grid = findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ImageView image =(ImageView) grid.getChildAt(i);
            image.setAlpha(0f);
            isClicked[i]=2;

        }
        isDraw=false;
        currentPlayer=1;
        won=false;


    }
}
