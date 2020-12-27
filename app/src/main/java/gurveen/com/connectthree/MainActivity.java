package gurveen.com.connectthree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    //0: yellow comes in,1: red comes in 2: empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 0;
    boolean gameActive = true;



    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow_counter);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red_counter);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(400);

            if (gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2 && gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2 && gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2) {
                Toast.makeText(this, "Red and yellow has drawn! Game will be restarted", Toast.LENGTH_LONG).show();
                playAgain(view);
            }

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
                    Button playAgainButton = findViewById(R.id.playAgainButton);

                    winnerText.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerText.setText(winner + " has Won!!");
                }
            }
        }
    }

    public void playAgain(View view) {
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton = findViewById(R.id.playAgainButton);

        winnerText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.grid_layout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView childView = (ImageView) gridLayout.getChildAt(i);
            childView.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        gameActive = true;

        activePlayer = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
