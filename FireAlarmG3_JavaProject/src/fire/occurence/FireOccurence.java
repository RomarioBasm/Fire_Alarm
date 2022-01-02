/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fire.occurence;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.tools.ShellFunctions.input;
import java.io.PrintWriter;
import java.util.Scanner;
import communication.*;


/**
 *
 * @author Romario
 */
public class FireOccurence extends SerialProtocol{

    boolean fireFlag = false;
    File f;
    Media media;
    MediaPlayer mplayer;
    FireOccurence(){
        f = new File("C:\\Users\\Romario\\Desktop\\alert.mpeg");
        media = new Media(f.toURI().toString());
        mplayer = new MediaPlayer(media);
    }
    public void fireOccurence() {
        //boolean checkFire = checkFire();   
        boolean checkFire = true;
        if (( checkFire == true) && ( fireFlag == false)) {    
            //sendAlert();
            //client.setData("1");
            fireFlag = true; 
            System.out.println("FIRE !!!");
            fireLedBuzzer();                              
            playSound();
        }
    }
    
    /*-------------------------------------------------------------------------------------------*/
    public float getTemp(){
        return temper;
    }
    /*-------------------------------------------------------------------------------------------*/
    void fireLedBuzzer(){
        this.send(1);
    }
    /*-------------------------------------------------------------------------------------------*/
    public boolean checkFire() {
        boolean state = false;
        int temp = temper;
        System.out.println("The temp now is : " + temp);
        if (temp > 15){
            state = true;
        }
        return state;
    }
    /*-------------------------------------------------------------------------------------------*/ 
    private void playSound(){
        mplayer.setAutoPlay(true);
    }
    /*-------------------------------------------------------------------------------------------*/
    private void stopSound(){
        mplayer.setAutoPlay(false);
    }
    /*-------------------------------------------------------------------------------------------*/
    public void stop(){
        this.send(2);
        fireFlag = false;
        this.stopSound();
    }
}
