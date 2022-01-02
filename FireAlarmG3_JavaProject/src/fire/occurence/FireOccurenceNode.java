/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fire.occurence;

/**
 *
 * @author sara
 */
public class FireOccurenceNode {

    private int fireId;
    FireOccurenceNode nextFireOccurence;
    private String fireOccurenceDate;
    private String fireOccurenceTime;
    static int TotalFireOccurences = 0;

    FireOccurenceNode(){

    }

    FireOccurenceNode(int id){

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
