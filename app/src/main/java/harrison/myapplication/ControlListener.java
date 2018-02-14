package harrison.myapplication;

import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Class ControlListener
 *
 * class implements all nessesary event listeners,
 * making it a suitable object to listen to all events
 * this class's largely exist to call the correct AppState methods
 *
 * @author Harry Thoma
 */

public class ControlListener implements
                                View.OnClickListener,
                                SeekBar.OnSeekBarChangeListener,
                                RadioGroup.OnCheckedChangeListener,
                                AdapterView.OnItemSelectedListener
{
    /**
     * External Citation:
     *
     * Problem: what listener to impliment for spinner
     *  and how to use the methods
     *
     *  Source:
     *    Android Studio API for AdapterView and
     *    AdapterView.OnItemSelectedListener
     *  Solution:
     *    use OnItemSelectedListner
     */


    //a control listener requires the state to call updates on
    AppState state;

    public ControlListener(AppState state){
        this.state= state;
    }

    @Override
    public void onClick(View view) {
        state.buttonPushed(view.getId());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(!fromUser) return; //we only care about when the user moves the bar
        state.sliderMoved();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        state.updateSliders();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(view == null) return; //none of that

        //get the text in the selected view
        String selectedText= (String)((TextView)view).getText();
        state.spinnerSelected(selectedText);
    }



    //unused methods
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
