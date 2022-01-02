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

    private int fireId;
    FireOccurenceNode nextFireOccurence;
    private String fireOccurenceDate;
    private String fireOccurenceTime;
    static int TotalFireOccurences = 0;

    FireOccurenceNode(){

	this.fireId = 1000;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/Y");
        this.fireOccurenceDate = dateFormat.format(currentDate);
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
        this.fireOccurenceTime = timeFormat.format(currentDate);
        this.nextFireOccurence=null;
    }

    FireOccurenceNode(int id){
       this.fireId=id;
        Date currentDate=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/Y");
        this.fireOccurenceDate=dateFormat.format(currentDate);
        SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm:ss a");
        this.fireOccurenceTime=timeFormat.format(currentDate);
        this.nextFireOccurence=null;
    }

    public void setFireId(int id){
        fireId =id;
    }

    public int getFireId(){
       return fireId;
    }

    public String getFireOccurenceDate(){
       return fireOccurenceDate;
    }

    public String getFireOccurenceTime(){
       return fireOccurenceTime;
    }


    
}
