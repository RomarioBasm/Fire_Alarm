/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firealarmg3_javaproject;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import communication.*;

public class FireOccurence extends SerialProtocol{
      
    File f;                           //File to open a new file.
    Media media;                      //media file to open the media file.
    MediaPlayer mplayer;              //mediaplayer file to play the sound.
    boolean toggle = false;           //flag to detect if the client send the data to the server or not.
    boolean fireFlag = false;         //flag to detect if there is a fire or not.
    
    /********************** Constructor  ****************************
    * constructor that open a new file for the media player file----*
    ****************************************************************/
    FireOccurence(){
        f = new File("C:\\Users\\sara\\Downloads\\project\\files\\peep.mp3"); 
        media = new Media(f.toURI().toString());                                    
        mplayer = new MediaPlayer(media);
    }
   
    /********************* fireOccurence  ***************************
    * This method always run immediately, whether or not the -------*
    * fire exists. when the fire occur, takes many actions ---------*
    *   - send a signal to the hardware ----------------------------*
    *   - send an email to alert the client ------------------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public void fireOccurence() { 
        boolean checkFire = checkFire();         //checking if there is a fire or not.
        
        if (( checkFire == true) && ( fireFlag == false)){ 
                 
            fireFlag = true;
            toggle = true;
            
            sendAlert();                 //send an email to the client.
            playSound();                 //play a software sound.
            fireLedBuzzer();             //send a signal to the hardware.                           
        }
        else if((checkFire == false) && ( fireFlag == true)){
            fireFlag=false;
        }
    }
    
    
    /********************** checkFire  ******************************
    * This method get the data from the hardware, check ------------*
    * if the temp exceed the threshold of the temp, then change the *
    * state of the system ------------------------------------------*
    * @param                                                        *
    * @return    state - boolean -----------------------------------*
    ****************************************************************/
    public boolean checkFire() {
        boolean state = false;
        int temp = temper;
        System.out.println("The temp now is : " + temp);
        if (temp > 25){
            state = true;
        }
        return state;
    }
    
    /********************** playSound  ******************************
    * This method get the path of the music to play it when there --*
    * is a fire ----------------------------------------------------*
    * @param     PATH - String -------------------------------------*
    * @return                                                       *
    ****************************************************************/
    private void playSound() {
        mplayer.setAutoPlay(true);            //start to play the sound    
    }
    
    
    /********************** stop  ***********************************
    * This method stop the process of the system, by sending -------*
    * a signal to the hardware -------------------------------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public void stop(){
        this.send(2);
        this.stopSound();
    }
    
    /********************** fireLedBuzzer  **************************
    * This method start the process of the system,  ----------------*
    * when the fire occur, by sending signal to the hardware ------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    void fireLedBuzzer(){
        this.send(1);
    }
    
    /*********************** sendAlert ******************************
    * This method send an email to the client when -----------------*
    * there is a fire. ---------------------------------------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public void sendAlert(){
        final String [] emailID = {"hagermohmed256@gmail.com"}; // can be any email id 
        Thread t1 = new Thread(){
           
            @Override
            public void run(){
                System.out.println("TLSEmail Start");
                String body = "fire.......";
                for (String emailID1 : emailID) {
                    EmailClass.sendEmail(emailID1, body);
                }
            }
        };
        t1.start();
    }
    
    /*********************** sendAlert ******************************
    * This method get the temperature from the hardware sensor------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public float getTemp(){
        return temper;
    }
    
    
    /*********************** stopSound ******************************
    * This method stop the software sound --------------------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    private void stopSound(){
        mplayer.stop();
    }
}



