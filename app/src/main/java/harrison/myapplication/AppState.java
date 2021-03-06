package harrison.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class AppState
 *
 * This class contains references to widgets etc.
 * on the screen
 * it contains methods to update values for all nessesary
 * objects. These methods are called by a ControlListener
 *
 * @author Harry Thoma
 */

public class AppState {
    //the seekBars that may need to be updated
    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;

    //the textViews corresponding to the seekbars
    private TextView redLabel;
    private TextView greenLabel;
    private TextView blueLabel;

    //the radio group
    private RadioGroup selectionRadio;

    //the hairstyle selection spinner
    private Spinner hairSpinner;

    //static string array used to populate the spinner
    private static final String[] hairTypes= {
            "Oval", "Square", "Triangle"
    };

    //the context on which all these widgets exist
    private Context context;

    //the surface view we will need to redraw
    private FaceSurfaceView fsv;
    //list of faces on the surface view
    //this list is shared by this and fsv
    private ArrayList<Face> faceList;
    //the currently selected face
    private Face face;

    //which radio is selected, as defined by the static ints
    private int selectedRadio;

    private static final int HAIR= 0;
    private static final int EYES= 1;
    private static final int SKIN= 2;


    //contructor requires all nessesary widgets
    //including a faceSurface view, from which it will pull the face
    public AppState(
                    Context context,
                    SeekBar redBar,
                    SeekBar greenBar,
                    SeekBar blueBar,
                    TextView redLabel,
                    TextView greenLabel,
                    TextView blueLabel,
                    RadioGroup selection,
                    Spinner hairSpinner,
                    FaceSurfaceView fsv
                    )
    {
        this.context= context;

        this.redBar= redBar;
        this.greenBar= greenBar;
        this.blueBar= blueBar;
        this.redLabel= redLabel;
        this.greenLabel= greenLabel;
        this.blueLabel= blueLabel;

        this.selectionRadio= selection;

        this.hairSpinner= hairSpinner;
        setUpHairSpinner();

        this.fsv= fsv;
        this.faceList= fsv.getFaces();
        this.face= faceList.get(0); //choose first face on startup

        updateSliders();
    }

    /**
     * update face color and textviews based on slider movement
     */
    public void sliderMoved(){
        int newColor= getColorFromSeekBars(); //the new color for whatever component

        switch(selectedRadio){
            case HAIR:
                face.setHairColor(newColor);
                break;
            case EYES:
                face.setEyeColor(newColor);
                break;
            case SKIN:
                face.setSkinColor(newColor);
                break;
            default:
                return;
        }

        updateLabels();

        fsv.invalidate();
    }

    /**called when the spinner is changed
     *
     * @param style the style to set the face's hair to
     */
    public void spinnerSelected(String style){
        //choose which hairstyle
        if(style.equals(hairTypes[0])) face.setHairStyle(Face.OVAL);
        else if(style.equals(hairTypes[1])) face.setHairStyle(Face.SQUARE);
        else if(style.equals(hairTypes[2])) face.setHairStyle(Face.TRIANGLE);
        else { //unknown hairstyle
            return;
        }

        fsv.invalidate();
    }

    /**
     * called when a button is pushed
     *
     * @param id the id of the pushed button
     */
    public void buttonPushed(int id){
        if(id == R.id.buttonRand){ //random button
            face.randomize();
        }
        else { //unknown button
            return;
        }

        updateAll();
        fsv.invalidate();
    }

    /**
     * called when the surfaceview is touched
     * @param x the x-coord of the touch
     * @param y the y-coord of the touch
     */
    public void touched(int x, int y){
        //go see if we selected a new face
        for(Face f : faceList){
            if(f.clickedMe(x,y)){
                Log.i("touched","clicked me");
                face= f;
                fsv.setSelectedFace(face);
                updateAll();
            }
        }

        fsv.invalidate();
    }

    /**
     * update sliders and spinner
     */
    private void updateAll(){
        hairSpinner.setSelection(face.getHairStyle());
        updateSliders();
    }

    /**
     * update slider values to reflect face's colors
     * called by control listner when radio updates
     */
    public void updateSliders(){
        int radioId= selectionRadio.getCheckedRadioButtonId();
        int color= 0; //the color that the sliders must update to

        //determine which button is selected and set color accordingly
        switch(radioId){
            case R.id.radioButtonEyes:
                color= face.getEyeColor();
                selectedRadio= EYES;
                break;
            case R.id.radioButtonHair:
                color= face.getHairColor();
                selectedRadio= HAIR;
                break;
            case R.id.radioButtonSkin:
                color= face.getSkinColor();
                selectedRadio= SKIN;
                break;
            default:
                return;
        }

        setSeekBarsToColor(color);

        updateLabels();
    }

    /**
     * updates lables to match current sliders
     *
     */
    private void updateLabels(){
        redLabel.setText(""+ redBar.getProgress());
        greenLabel.setText(""+ greenBar.getProgress());
        blueLabel.setText(""+ blueBar.getProgress());
    }

    /**
     * gets color from current position of sliders
     *
     * @return the color represented by the current state of the seekbars
     */
    private int getColorFromSeekBars(){
        int r= redBar.getProgress();
        int g= greenBar.getProgress();
        int b= blueBar.getProgress();

        return Color.argb(255,r,g,b);
    }

    /**
     * sets sliders to given color
     *
     * @param color the color to set the seekbars to
     */
    private void setSeekBarsToColor(int color){
        //parse color for r,g,b
        int r= Color.red(color);
        int g= Color.green(color);
        int b= Color.blue(color);

        redBar.setProgress(r);
        greenBar.setProgress(g);
        blueBar.setProgress(b);
    }

    /**
     * sets up the spinner by adding the relevant entries
     */
    private void setUpHairSpinner(){
        //create the array adapter
        ArrayAdapter<String> hairAdapter=
                new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_dropdown_item,
                        hairTypes);

        hairSpinner.setAdapter(hairAdapter);
    }
}
