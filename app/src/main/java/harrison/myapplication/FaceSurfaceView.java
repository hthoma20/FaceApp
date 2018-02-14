package harrison.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Class FaceSurfaceView
 *
 * class extends surface view so it can be displayed on the layout
 * contains a face and draws it
 *
 * @author Harry Thoma
 */

public class FaceSurfaceView extends SurfaceView {
    //the face for this surfaceview to draw
    private ArrayList<Face> faces;
    //the currently selected face
    private Face selectedFace;

    public FaceSurfaceView(Context context) {
        super(context);
        initialize();
    }

    public FaceSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public FaceSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    /**
     * do initialization things
     * called from every contructor
     */
    private void initialize(){
        setWillNotDraw(false);

        this.faces= new ArrayList<Face>();

        for(int i=0;i<6;i++){
            faces.add(new Face(500 + 250*i,500));
        }

        selectedFace= faces.get(0);
    }

    public void onDraw(Canvas c){
        for(Face face : faces){
            face.onDraw(c);
        }

        selectedFace.drawBounds(c);
    }

    public ArrayList<Face> getFaces(){
        return faces;
    }

    public void setSelectedFace(Face face){
        if(faces.contains(face)) {
            selectedFace = face;
        }
    }
}
