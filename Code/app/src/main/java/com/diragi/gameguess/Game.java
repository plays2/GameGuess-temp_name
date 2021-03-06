package com.diragi.gameguess;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView test;
    List<String> choices;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button ten;
    Button eleven;
    Button twelve;
    Button thirteen;
    Button fourteen;
    int current = 0;
    int strikes = 3;
    String answer = "";
    Button continueButton;
    int currentLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        View view = findViewById(R.id.buttonOne);
        View root = view.getRootView();
        int bgColor = getIntent().getIntExtra("BGCOLOR", 0xFF000000);
        root.setBackgroundColor(bgColor);
        continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setVisibility(View.INVISIBLE);
        continueButton.setEnabled(false);

        currentLevel = getIntent().getIntExtra("LEVEL", 1);

        one = (Button)findViewById(R.id.buttonOne);
        two = (Button)findViewById(R.id.buttonTwo);
        three = (Button)findViewById(R.id.buttonThree);
        four = (Button)findViewById(R.id.buttonFour);
        five = (Button)findViewById(R.id.buttonFive);
        six = (Button)findViewById(R.id.buttonSix);
        seven = (Button)findViewById(R.id.buttonSeven);
        eight = (Button)findViewById(R.id.buttonEight);
        nine = (Button)findViewById(R.id.buttonNine);
        ten = (Button)findViewById(R.id.buttonTen);
        eleven = (Button)findViewById(R.id.buttonEleven);
        twelve = (Button)findViewById(R.id.buttonTwelve);
        thirteen = (Button)findViewById(R.id.buttonThirteen);
        fourteen = (Button)findViewById(R.id.buttonFourteen);


        int level = getIntent().getIntExtra("LEVEL", 1);
        String ans = getIntent().getStringExtra("ANSWER");
        int imgId = getIntent().getIntExtra("IMAGE", R.drawable.ic_error);

        Drawable d = ContextCompat.getDrawable(getApplicationContext(), imgId);
        ImageView gameView = (ImageView)findViewById(R.id.imageView2);
        gameView.setImageDrawable(d);

        ArrayList<Character> game = makeLevel(ans);
        makeGame(game);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public ArrayList<Character> makeLevel(String answer){

        //Create an array with all of the letters of the answer, so we can assign each letter to a button
        String name = getIntent().getStringExtra("ANSWER");

        //Convert the array to a list so we can randomize and add to it
        ArrayList<Character> ans = new ArrayList<Character>();

        for (char c : name.toCharArray()){

            ans.add(c);

        }

        //get the length of the answer, if it's greater than 12 that's bad news
        if (ans.size() < 14){

            int howManyRand = 14 - ans.size();

            String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

            for (int i = 0; i < howManyRand; i++){

                int rand = new Random().nextInt(letters.length);
                String letter = (letters[rand]);
                Log.d("LETTER", letter);
                char letterChar = letter.charAt(0);
                if (ans.contains(letterChar)){
                    //Go again
                    i--;
                } else {
                    ans.add(letterChar);
                    Log.d("ARRAY", ans.toString());
                }

            }

        } else {
            //We're gunna need more buttons
        }

        return ans;

    }

    public void makeGame(ArrayList<Character> arrayList){

        //First, we need to get the array and randomize it.
        long seed = System.nanoTime();
        Collections.shuffle(arrayList, new Random(seed));
        Log.d("SHUFFLE: ", arrayList.toString());
        //Now that shit's shuffled up, we need to set all of the buttons to the right letter.

        one.setText(arrayList.get(0).toString());
        two.setText(arrayList.get(1).toString());
        three.setText(arrayList.get(2).toString());
        four.setText(arrayList.get(3).toString());
        five.setText(arrayList.get(4).toString());
        six.setText(arrayList.get(5).toString());
        seven.setText(arrayList.get(6).toString());
        eight.setText(arrayList.get(7).toString());
        nine.setText(arrayList.get(8).toString());
        ten.setText(arrayList.get(9).toString());
        eleven.setText(arrayList.get(10).toString());
        twelve.setText(arrayList.get(11).toString());
        thirteen.setText(arrayList.get(12).toString());
        fourteen.setText(arrayList.get(13).toString());

    }

    public void checkAnswer(View v){

        Button pressed = (Button)v;
        String buttonText = pressed.getText().toString();
        Log.d("Pressed: ", buttonText);
        String name = getIntent().getStringExtra("ANSWER");
        //Convert the array to a list so we can randomize and add to it
        ArrayList<Character> ans = new ArrayList<Character>();

        for (char c : name.toCharArray()){

            ans.add(c);

        }

        if (buttonText.equals(ans.get(current).toString())){
            Log.d("COrrect", "answer");
            //Put the letter into the first textview
            answer = answer + ans.get(current).toString();
            TextView answerTextView = (TextView)findViewById(R.id.answerTextView);
            answerTextView.setText(answer);
            int compare = ans.size() - 1;
            if (current == compare) {

                //Win
                continueButton.setEnabled(true);
                continueButton.setVisibility(View.VISIBLE);

            } else {
                current++;
            }
            v.setEnabled(false);

        } else {

            //That's not the right button. Let's take away a point from this asshole
            //Set up the strikes textView
            TextView strikesTextView = (TextView)findViewById(R.id.strikes);
            if (strikes >=2) {

                strikes--;
                strikesTextView.setText("Strikes: " +strikes);

            } else if (strikes == 1) {

                //This faggot lost
                strikes--;
                strikesTextView.setText("Strikes: " + strikes);
                Log.d("Game", "You lost faggot");
                Intent youLose = new Intent(getBaseContext(), LoseActivity.class);
                youLose.putExtra("LEVEL", currentLevel);
                startActivity(youLose);

            }

        }

    }

    public void onContinue(View v){

        Intent goToLevelPicker = new Intent(getBaseContext(), LevelPicker.class);
        /**
         *
         * Maybe we can make it go right to the next level
         * TODO:But we'll need an array of all the level answers and images and colors so it'll make the apk significantly larger. We'll see about this kek
         *
         * Intent goToNextLevel = new Intent(getBaseContext(), LevelPicker.class);
         *
         * if (autoPlay){
         *
         *       int nextLevel = currentLevel + 1;
         *       goToNextLevel.putExtra("LEVEL", nextLevel);
         *       String nextTitle = arrayOfAllAnswers[nextLevel - 1];
         *       goToNextLevel.putExtra("ANSWER", nextTitle);
         *       int nextImageId = arrayOfAllImageId[nextLevel - 1]; //We'll probably need a method to create the correct nextLevel number since arrays start with 0
         *       goToNextLevel.putExtra("IMAGE", R.drawable.zelda);
         *       int nextBgColor = arrayOfAllBgColors[nextLevel - 1];
         *       goToNextLevel.putExtra("BGCOLOR", 0xFF45b649);
         *
         *       startActivity(goToNextLevel);
         *
         * } else {
         *
         **/


        goToLevelPicker.putExtra("WIN", true);
        switch (currentLevel){
            case 1:
                goToLevelPicker.putExtra("BUTTONID", R.id.one);
                break;
            case 2:
                goToLevelPicker.putExtra("BUTTONID", R.id.two);
                break;
            case 3:
                goToLevelPicker.putExtra("BUTTONID", R.id.three);
                break;
            case 4:
                goToLevelPicker.putExtra("BUTTONID", R.id.four);
                break;
            case 5:
                goToLevelPicker.putExtra("BUTTONID", R.id.five);
                break;
            case 6:
                goToLevelPicker.putExtra("BUTTONID", R.id.six);
                break;
            case 7:
                goToLevelPicker.putExtra("BUTTONID", R.id.seven);
                break;
            case 8:
                goToLevelPicker.putExtra("BUTTONID", R.id.eight);
                break;
            case 9:
                goToLevelPicker.putExtra("BUTTONID", R.id.nine);
                break;
            case 10:
                goToLevelPicker.putExtra("BUTTONID", R.id.ten);
                break;
            default:
                break;

        }

        startActivity(goToLevelPicker);
    }

}
