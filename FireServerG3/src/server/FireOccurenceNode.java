/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author sara
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class FireOccurenceNode {

    private int fireId; // private intger member to the order  of fire since operation
    FireOccurenceNode nextFireOccurence; //reference to FireOccurenceNode contain the address of the next node
    private String fireOccurenceDate; // private string member to show fire date: day/month/year
    private String fireOccurenceTime; //private string member to show fire time: hours:miniutes:seconds am/pm
    static int TotalFireOccurences = 0; //declaring & intalizeing static intger member to show the total number of fires occured since operation

    //constructor to be used while creating a dummy node  
    FireOccurenceNode(){
        //setting a large (far) id as it will be a dummy node
	this.fireId = 1000; 
        //creating a refrence to date class and store a new created object of class date in it
        Date currentDate = new Date();
        //changing the date format to be day/month/year 
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/Y");
        //changing the type from date to string and store it in fireOccurenceDate member
        this.fireOccurenceDate = dateFormat.format(currentDate);
        //changing the date format to be hours:miniutes:seconds am/pmhours:miniutes:seconds am/pm
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        //changing the type from date to string and store it in fireOccurencetime member
        this.fireOccurenceTime = timeFormat.format(currentDate);
        //initalize the next fire occurence refrence to null  
        this.nextFireOccurence=null;
    }
    //a constructor to be used while creating a node when a fire occured and give the fire an order number (ID)
    FireOccurenceNode(int id){
        this.fireId=id; // set the fire order number of the fire
        //creating a refrence to date class and store a new created object of class date in it
        Date currentDate=new Date();
        //changing the date format to be day/month/year 
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/Y");
        //changing the type from date to string and store it in fireOccurenceDate member
        this.fireOccurenceDate=dateFormat.format(currentDate);
        //changing the date format to be hours:miniutes:seconds am/pmhours:miniutes:seconds am/pm
        SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm:ss a");
        //changing the type from date to string and store it in fireOccurencetime member
        this.fireOccurenceTime=timeFormat.format(currentDate);
        //initalize the next fire occurence refrence to null 
        this.nextFireOccurence=null;
    }
    //setter for fire id that takes intger
    public void setFireId(int id){
        fireId =id;
    }
    //getter for fire id that return intger
    public int getFireId(){
       return fireId;
    }
    //getter for fireoccurence date that return string
    public String getFireOccurenceDate(){
       return fireOccurenceDate;
    }
    //getter for fire occurence time that return string
    public String getFireOccurenceTime(){
       return fireOccurenceTime;
    }


    
}
