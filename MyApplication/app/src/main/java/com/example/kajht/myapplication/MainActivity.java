package com.example.kajht.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/*
 *
 *  The Game is created on 18.02 By Me
 *  I hope you like it
 *
 *  I have used animations, custom buttom style, custom Game class
 *  multiple .xmls, custom background image (taken from site for free image sharing),
 *  custom launcher icon (taken from site for free image sharing and then resized using GIMP)
 *  The computer 'Bot' is highly unsophisticated, making move at the first position it seems suitable
 *  The Code for the game class is taken from my university's Praktikum in C++ where I had to create similar game
 *  (without interface of course)
 *
 *  I implement the View.OnClickListener in the MainActivity class so that I can quickly set
 *  onClickListener for each of the 'gameBoard' buttons to this classes onClick, which led
 *  to easier to read code but also problem with declaring their animations not only once.
 *
 *  All other on click listeners are set in the onCreate method.
 *
 *  Most of the animations have a special function to make after they finish executing
 *  as seting the background of the clicked to 'Gone' or starting another one.
 *
 */

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private Game game;
    private TextView displayMeTextView;
    private static final int XPLAYER = 1;
    private static final int YPLAYER = 2;
    private static final int XPLAYERWON = 3;
    private static final int YPLAYERWON = 4;
    private static final int DRAW = 0;
    private static final int COMPUTER = 5;
    private static final int INCORRECTMOVE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean vsComputer = false;

        final Animation buttonHomeAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_right_x_move);
        final Animation vsButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_left_x_move);
        final Animation playAgainButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_move);

        displayMeTextView = (TextView) findViewById(R.id.displayMe);

        // Initialize all the secondary buttons
        final Button button_home = (Button) findViewById(R.id.button_home),
             button_vs_player = (Button) findViewById(R.id.button_vs_player),
             button_vs_computer = (Button) findViewById(R.id.button_vs_computer),
             playAgainButton = (Button) findViewById(R.id.playAgainButton);

        // Initialize the game buttons
        // and set the onClickListener
        // to the actual onClick
        Button button = (Button) findViewById(R.id.button1_0);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_1);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_2);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_3);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_4);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_5);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_6);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_7);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.button1_8);
        button.setOnClickListener(this);

        // vs Computer, vs Player Button
        // set home_page visibility to gone and home button to visible at the end
        vsButtonAnimation.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation){
                findViewById(R.id.home_page).setVisibility(View.GONE);
                button_home.setVisibility(View.VISIBLE);
            }
        });
        // Home Button Animation
        // at end make home_apge visible all else gone
        buttonHomeAnimation.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation){
                findViewById(R.id.home_page).setVisibility(View.VISIBLE);
                button_home.setVisibility(View.GONE);
                playAgainButton.setVisibility(View.GONE);
                displayMeTextView.setVisibility(View.GONE);
            }
        });

        // set its own visibility and the displayMeTextViews  to gone
        playAgainButtonAnimation.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation){
                playAgainButton.setVisibility(View.GONE);
                displayMeTextView.setVisibility(View.GONE);
            }
        });

        /*
          * set onClick to the secondary Buttons
          */

        playAgainButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(playAgainButtonAnimation);
                game.init();
                cleanBoard();
            }
        });

        button_home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(buttonHomeAnimation);
                game.init();
                cleanBoard();
            }
        });

        button_vs_player.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(vsButtonAnimation);
                game = new Game(false);
            }
        });

        button_vs_computer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(vsButtonAnimation);
                game = new Game(true);
            }
        });
    }

    /*
     * Make all the buttons with their default text i.e none
     */

    private void cleanBoard() {
        String def = getString(R.string.def);
        Button button = (Button) findViewById(R.id.button1_0);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_1);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_2);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_3);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_4);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_5);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_6);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_7);
        button.setText(def);
        button = (Button) findViewById(R.id.button1_8);
        button.setText(def);
    }

    private void computerMove(int n) {
        Button button,
             playAgainButton = (Button) findViewById(R.id.playAgainButton);
        String y = getString(R.string.y);
        String ywon = getString(R.string.ywon);
        String draw = getString(R.string.draw);

        switch (n) {
            case 0: button = (Button) findViewById(R.id.button1_0);
                break;
            case 1: button = (Button) findViewById(R.id.button1_1);
                break;
            case 2: button = (Button) findViewById(R.id.button1_2);
                break;
            case 3: button = (Button) findViewById(R.id.button1_3);
                break;
            case 4: button = (Button) findViewById(R.id.button1_4);
                break;
            case 5: button = (Button) findViewById(R.id.button1_5);
                break;
            case 6: button = (Button) findViewById(R.id.button1_6);
                break;
            case 7: button = (Button) findViewById(R.id.button1_7);
                break;
            case 8: button = (Button) findViewById(R.id.button1_8);
                break;
            default: button = (Button) findViewById(R.id.button1_0);

        }
        button.setText(y);

        if (game.checkWin(YPLAYER)) {
            displayMeTextView.setText(ywon);
            displayMeTextView.setVisibility(View.VISIBLE);
            playAgainButton.setVisibility(View.VISIBLE);
        } else if (game.checkDraw()) {
            displayMeTextView.setText(draw);
            displayMeTextView.setVisibility(View.VISIBLE);
            playAgainButton.setVisibility(View.VISIBLE);
        }
    }

    /*
     *
     * Button action method
     * first making a move and saving the return code into a variable
     * if correct do the first animation, if not the second
     * check what the return code is and accordingly do an action
     *
     */

    private void buttonAction(View v, int buttonNumber, Animation animSuccess, Animation animFailure) {

        Button button = (Button) v;
        Button playButton = (Button) findViewById(R.id.playAgainButton);

        String x = getString(R.string.x);
        String y = getString(R.string.y);
        String xwon = getString(R.string.xwon);
        String ywon = getString(R.string.ywon);
        String draw = getString(R.string.draw);

        // get return value of making a move
        // if !-1 then the turn is fine and proceed with animation
        // else it is already occupied so make the wrong_move animation
        int moveCode = game.move(buttonNumber);

        if (moveCode != INCORRECTMOVE) {
            v.startAnimation(animSuccess);
        } else {
            v.startAnimation(animFailure);
            return;
        }

        // if return is 5 then we play against a computer
        // set the text and let the computer make a move
        if (moveCode == COMPUTER) {
            button.setText(x);
            int computerMove = game.getMove();
            computerMove(computerMove);
        } else if(moveCode == XPLAYER) {
            button.setText(x);
        } else if (moveCode == YPLAYER) {
            button.setText(y);
        } else if (moveCode == XPLAYERWON) {
            button.setText(x);
            displayMeTextView.setText(xwon);
            displayMeTextView.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
        } else if (moveCode == YPLAYERWON) {
            button.setText(y);
            displayMeTextView.setText(ywon);
            displayMeTextView.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
        } else {
            button.setText(xwon);
            displayMeTextView.setText(draw);
            displayMeTextView.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(final View v) {
        final Animation animCorrectMove = AnimationUtils.loadAnimation(this, R.anim.anim_scale_move);
        final Animation animWrongMove = AnimationUtils.loadAnimation(this, R.anim.anim_wrong_move);
        final Animation animWrongMoveAfter = AnimationUtils.loadAnimation(this, R.anim.anim_wrong_move_after);

        int buttonNumber = 0;

        // set animWrongMoveAfter to start at the end of animWrongMove
        animWrongMove.setAnimationListener(new Animation.AnimationListener(){

            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation){
                v.startAnimation(animWrongMoveAfter);
            }
        });

        // Switch to find the pressed buttons id
        switch (v.getId()) {
            case R.id.button1_0:
                buttonNumber = 0;
                break;
            case R.id.button1_1:
                buttonNumber = 1;
                break;
            case R.id.button1_2:
                buttonNumber = 2;
                break;
            case R.id.button1_3:
                buttonNumber = 3;
                break;
            case R.id.button1_4:
                buttonNumber = 4;
                break;
            case R.id.button1_5:
                buttonNumber = 5;
                break;
            case R.id.button1_6:
                buttonNumber = 6;
                break;
            case R.id.button1_7:
                buttonNumber = 7;
                break;
            case R.id.button1_8:
                buttonNumber = 8;
                break;
        }

        // pass the view, the buttons number and both animations
        buttonAction(v, buttonNumber, animCorrectMove, animWrongMove);
    }
}
