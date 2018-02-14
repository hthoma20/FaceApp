package harrison.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to nessesary widgets
        RadioGroup selectGroup= (RadioGroup)findViewById(R.id.radioGroupSelect);

        SeekBar redBar= (SeekBar)findViewById(R.id.seekBarRed);
        SeekBar greenBar= (SeekBar)findViewById(R.id.seekBarGreen);
        SeekBar blueBar= (SeekBar)findViewById(R.id.seekBarBlue);

        TextView redLabel= (TextView)findViewById(R.id.textViewRVal);
        TextView greenLabel= (TextView)findViewById(R.id.textViewGVal);
        TextView blueLabel= (TextView)findViewById(R.id.textViewBVal);

        Spinner hairSpinner= (Spinner)findViewById(R.id.spinnerHair);

        Button randomButton= (Button)findViewById(R.id.buttonRand);

        FaceSurfaceView fsv= (FaceSurfaceView)findViewById(R.id.viewFace);

        //create an instance of the AppState,
        //the object in charge of updating the layout
        AppState state= new AppState(this,
                                    redBar, greenBar, blueBar,
                                    redLabel, greenLabel, blueLabel,
                                    selectGroup,
                                    hairSpinner,
                                    fsv);

        //create an instance of ControlListener,
        //the object in charge of listening to events
        ControlListener listener= new ControlListener(state);

        //set the listner for all relevant widgets
        selectGroup.setOnCheckedChangeListener(listener);

        redBar.setOnSeekBarChangeListener(listener);
        greenBar.setOnSeekBarChangeListener(listener);
        blueBar.setOnSeekBarChangeListener(listener);

        randomButton.setOnClickListener(listener);

        hairSpinner.setOnItemSelectedListener(listener);

        fsv.setOnTouchListener(listener);
    }
}
