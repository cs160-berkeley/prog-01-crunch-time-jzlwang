package judy.crunchtime;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class MainActivity extends Activity {
    private final double BLANK_EXERCISE = 0;
    private final double PUSHUPS = 0.28571429;
    private final double SITUPS = 0.5;
    private final double SQUATS = 0.44444444;
    private final double LEG_LIFTS = 4.0;
    private final double PLANK = 4.0;
    private final double JUMPING_JACKS = 10.0;
    private final double PULLUPS = 1.0;
    private final double CYCLING = 8.33333333;
    private final double WALKING = 5.0;
    private final double JOGGING = 8.33333333;
    private final double SWIMMING = 7.69230769;
    private final double STAIR_CLIMBING = 6.66666667;

    private static TextView output;
    private EditText weight;
    private TextView label;
    private EditText amt;
    private int cals;
    private String exercise;

    private double weightMultiplier = 0;
    private double exerciseMultiplier = 0;
    private int amount;

    private TextView pushups;
    private TextView situps;
    private TextView squats;
    private TextView leglifts;
    private TextView plank;
    private TextView jj;
    private TextView pullups;
    private TextView cycling;
    private TextView walking;
    private TextView jogging;
    private TextView swimming;
    private TextView stairs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Calories burned result.
        output = (TextView) findViewById(R.id.output_calories);
        pushups = (TextView) findViewById(R.id.pushup_reps);
        situps = (TextView) findViewById(R.id.situp_reps);
        squats = (TextView) findViewById(R.id.squat_reps);
        leglifts = (TextView) findViewById(R.id.leglift_reps);
        plank = (TextView) findViewById(R.id.plank_time);
        jj = (TextView) findViewById(R.id.jj_time);
        pullups = (TextView) findViewById(R.id.pullup_reps);
        cycling = (TextView) findViewById(R.id.cycling_time);
        walking = (TextView) findViewById(R.id.walking_time);
        jogging = (TextView) findViewById(R.id.jogging_time);
        swimming = (TextView) findViewById(R.id.swimming_time);
        stairs = (TextView) findViewById(R.id.stair_time);

//        Weight Segment.
        weight = (EditText) findViewById(R.id.weight);
        weight.addTextChangedListener(weightWatcher);

//        Reps Segment.
        label = (TextView) findViewById(R.id.label);
        amt = (EditText) findViewById(R.id.amt);
        amt.addTextChangedListener(repsWatcher);
        label.setVisibility(View.INVISIBLE);
        amt.setVisibility(View.INVISIBLE);

//        Exercise spinner.
        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);
        List<String> exercise_list = new ArrayList<String>();
        exercise_list.add("Choose an exercise");
        exercise_list.add("Pushups");
        exercise_list.add("Situps");
        exercise_list.add("Squats");
        exercise_list.add("Leg-lifts");
        exercise_list.add("Plank");
        exercise_list.add("Jumping Jacks");
        exercise_list.add("Pullups");
        exercise_list.add("Cycling");
        exercise_list.add("Walking");
        exercise_list.add("Jogging");
        exercise_list.add("Swimming");
        exercise_list.add("Stair-Climbing");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, exercise_list);
        dynamicSpinner.setAdapter(adapter);
        dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                exercise = (String) parent.getItemAtPosition(position);
                switch(exercise) {
                    case "Choose an exercise":
                        label.setVisibility(View.INVISIBLE);
                        amt.setVisibility(View.INVISIBLE);
                        exerciseMultiplier = BLANK_EXERCISE;
                        break;
                    case "Pushups":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Reps:");
                        exerciseMultiplier = PUSHUPS;
                        break;
                    case "Situps":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Reps:");
                        exerciseMultiplier = SITUPS;
                        break;
                    case "Squats":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Reps:");
                        exerciseMultiplier = SQUATS;
                        break;
                    case "Leg-lifts":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = LEG_LIFTS;
                        break;
                    case "Plank":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = PLANK;
                        break;
                    case "Jumping Jacks":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = JUMPING_JACKS;
                        break;
                    case "Pullups":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Reps:");
                        exerciseMultiplier = PULLUPS;
                        break;
                    case "Cycling":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = CYCLING;
                        break;
                    case "Walking":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = WALKING;
                        break;
                    case "Jogging":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = JOGGING;
                        break;
                    case "Swimming":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = SWIMMING;
                        break;
                    case "Stair-Climbing":
                        label.setVisibility(View.VISIBLE);
                        amt.setVisibility(View.VISIBLE);
                        label.setText("# of Minutes:");
                        exerciseMultiplier = STAIR_CLIMBING;
                        break;
                }
                cals = (int) round(exerciseMultiplier * weightMultiplier * amount);
                output.setText(cals + " calories burned");
                updateEquates(exerciseMultiplier * weightMultiplier * amount);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

//    TextWatcher for weight field.
    private final TextWatcher weightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                weightMultiplier = 0;
                cals = 0;
            } else {
                weightMultiplier = 1; //Integer.parseInt(weight.getText().toString());
                cals = (int) round(exerciseMultiplier * weightMultiplier * amount);
            }
            output.setText(cals + " calories burned");
            updateEquates(exerciseMultiplier * weightMultiplier * amount);
        }
    };
//    TextWatcher for reps field.
    private final TextWatcher repsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                amount = 0;
                cals = 0;
            } else {
                amount = Integer.parseInt(amt.getText().toString());
                cals = (int) round(exerciseMultiplier * weightMultiplier * amount);
            }
            output.setText(cals + " calories burned");
            updateEquates(exerciseMultiplier * weightMultiplier * amount);
        }
    };

    private void updateEquates(double calories) {
        pushups.setText((int) round(calories/PUSHUPS) + " reps");
        situps.setText((int) round(calories/SITUPS) + " reps");
        squats.setText((int) round(calories/SQUATS) + " reps");
        leglifts.setText((int) round(calories/LEG_LIFTS) + " reps");
        plank.setText((int) round(calories / PLANK) + " min");
        jj.setText((int) round(calories/JUMPING_JACKS) + " min");
        pullups.setText((int) round(calories/PULLUPS) + " reps");
        cycling.setText((int) round(calories/CYCLING) + " min");
        walking.setText((int) round(calories/WALKING) + " min");
        jogging.setText((int) round(calories/JOGGING) + " min");
        swimming.setText((int) round(calories/SWIMMING) + " min");
        stairs.setText((int) round(calories/STAIR_CLIMBING) + " min");
    }
}