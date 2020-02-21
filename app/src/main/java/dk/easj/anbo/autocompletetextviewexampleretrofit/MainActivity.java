package dk.easj.anbo.autocompletetextviewexampleretrofit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

// https://developer.android.com/reference/android/widget/AutoCompleteTextView.html
public class MainActivity extends AppCompatActivity {

    // http://www.fao.org/wairdocs/tan/x5994e/x5994e01.htm
    private static final String[] FISH_NAMES = new String[]{
            "bream", "carp",
            "grayling", "perch", "pike", "pike-perch", "roach",
            "tench", "eel", "sturgeon", "salmon",
            "trout", "smelt", "rainbow trout", "whitefish",
            "picked dogfish", "shark", "skate",
            "smooth hound", "anchovy", "herring", "pilchard",
            "sardine", "sardinella", "sprat", "blue ling",
            "blue whitling", "cod", "greater forkbeard",
            "haddock", "hake", "ling", "pollack", "poor cod",
            "pout", "saithe", "tush", "whiting",
            "atherine", "bogue", "mullet", "picaral", "scad",
            "sea bream", "surmullet", "chub mackerel", "garfish",
            "mackerel", "swordfish", "albacore tuna",
            "bonito tuna", "skipjack tuna", "tuna"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Arrays.sort(FISH_NAMES);
        AutoCompleteTextView view = findViewById(R.id.mainBreedAutoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, FISH_NAMES);
        view.setAdapter(adapter);

        final Button button = findViewById(R.id.mainShowButton);
        button.setOnHoverListener((v, event) -> {
            button.setBackgroundColor(Color.BLUE);
            return false;
        });
    }

    public void buttonNextClicked(View view) {
        Intent intent = new Intent(this, DataFromRestActivity.class);
        startActivity(intent);
    }

    public void buttonShowClicked(View view) {
        AutoCompleteTextView textView = findViewById(R.id.mainBreedAutoCompleteTextView);
        Editable text = textView.getText();
        String message = "You choose: " + text;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        TextView messageView = findViewById(R.id.mainMessageTextView);
        messageView.setText(message);
    }
}
