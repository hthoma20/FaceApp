package harrison.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Random;

/**
 * Face outlines a drawable face
 * includes three different hairstyles
 * includes method to draw the face on a given canvas
 *
 * @author Harry Thoma
 */

public class Face {
    private int hairColor;
    private int eyeColor;
    private int skinColor;

    //coordinates of this face
    int x, y;

    //represents what hair style to draw,
    //as defined by the static final ints
    private int hairType;

    public static final int OVAL= 0;
    public static final int SQUARE= 1;
    public static final int TRIANGLE= 2;


    /*
     * Constuctor to initialize new face with random colors
     * at specified location
     */
    public Face(int x, int y){
        this.x= x;
        this.y= y;
        randomize();
    }


    /*
     * randomize values of this face
     */
    public void randomize() {
        eyeColor= getRandomColor();
        hairColor= getRandomColor();
        skinColor= getRandomColor();
    }

    /*
     * returns a random color
     */
    private static int getRandomColor(){
        Random rand= new Random();
        int r= rand.nextInt(256);
        int g= rand.nextInt(256);
        int b= rand.nextInt(256);

        return Color.argb(255,r,g,b);
    }

    /*
     * draw this face on given canvas, centered at given x,y
     */
    public void onDraw(Canvas c){
        drawSkin(c);
        drawHair(c);
        drawEyes(c);
    }

    private void drawSkin(Canvas c) {
        Paint skinPaint= new Paint();
        skinPaint.setColor(skinColor);

        c.drawOval(x-100,y-135,x+100,y+135,skinPaint);
    }

    private void drawHair(Canvas c){
        Paint hairPaint= new Paint();
        hairPaint.setColor(hairColor);

        //choose what hair we should draw
        switch(hairType){
            case OVAL:
                drawOvalHair(c,hairPaint);
                break;
            case SQUARE:
                drawSquareHair(c,hairPaint);
                break;
            case TRIANGLE:
                drawTriangleHair(c,hairPaint);
                break;
            default:
                break;
        }
    }

    private void drawOvalHair(Canvas c, Paint hairPaint){
        c.drawOval(x-100,y-160,x+100,y-110,hairPaint);
    }

    private void drawSquareHair(Canvas c, Paint hairPaint){
        c.drawRect(x-100,y-150,x+100,y-110,hairPaint);
    }

    private void drawTriangleHair(Canvas c, Paint hairPaint){
        Path triangle= new Path();

        triangle.moveTo(x-100,y-110);
        triangle.lineTo(x+100,y-110);
        triangle.lineTo(x,y-150);

        c.drawPath(triangle,hairPaint);
    }
    /**
     External Citation
         Date: 2/11/18
         Problem: wanted to draw polygon on canvas

         Resource:
            Android Studio API draw path class
         Solution: used drawPath()
     */
    /**
     External Citation
     Date: 2/11/18
     Problem: how to use path

     Resource:
        http://android-er.blogspot.nl/2011/08/drawpath-on-canvas.html
     Solution: used moveTo and LineTo methods as outlined in this tutorial
     */


    private void drawEyes(Canvas c){
        Paint eyePaint= new Paint();
        eyePaint.setColor(eyeColor);

        c.drawCircle(x-30,y-20,20,eyePaint);
        c.drawCircle(x+30,y-20,20,eyePaint);
    }



    public int getSkinColor() {
        return skinColor;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setSkinColor(int color){
        this.skinColor= color;
    }

    public void setEyeColor(int color){
        this.eyeColor= color;
    }

    public void setHairColor(int color){
        this.hairColor= color;
    }

    public void setHairStyle(int hairStyle){
        if(hairStyle < 0 || hairStyle > 2) return; //not a valid style

        this.hairType= hairStyle;
    }
}
