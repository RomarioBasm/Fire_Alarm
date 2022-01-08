/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author sara
 */

import java.awt.Desktop;
import java.io.*;

public class FireOccurenceList {

    private FireOccurenceNode head; //creating a refrence to FireOccurenceNode class
    private String recordFileDirectory; //a string refrence to save a file path 
    File file; //creating a refrence to file class

    // class constructor to intailize the start of the list by null at first
    FireOccurenceList(){
	head=null;
    }
    //method to add node at the end of the list and recives an object of class FireOccurenceNode
    public void appendNode(FireOccurenceNode data){

        FireOccurenceNode node= data;//creating a dummy node
        //in case no nodes in the list assign the start of the list o the given node
        if (head==null){ 
            head = node;
        }else{//loop till the end of the list is found
        FireOccurenceNode fire=head; //creat a refrence to FireOccurenceNode and intalize it to the start node of the list
        //check if it is the last node in the while condition
        while(fire.nextFireOccurence !=null){
            fire=fire.nextFireOccurence;//assign the refrence to the next node
        }
        fire.nextFireOccurence=node;//add the new node after the last node
        }
        FireOccurenceNode.TotalFireOccurences++; //after adding the node incrment the total fires by 1
    }
    // method to search for a node by its fire order number and iit takes intger and retures an object of FireOccurenceNode class
    public FireOccurenceNode getNode(int id){
        FireOccurenceNode node=head; //creat a refrence to FireOccurenceNode and intalize it to the start node of the list
        //the condition terminates when the refrence reaches the last node 
        while(node.nextFireOccurence !=null){
        //check if the node id is the same to refrence node id 
            if (node.getFireId()== id){
                return node; //node is found and return it
            }
            node=node.nextFireOccurence; //if condition is not true set refrence to the next node
        }
        //check for the last node if its id matches with the refrence id 
        if (node.getFireId()== id){
            return node; // node is found and return it
        }
        return null; //if non of the conditions is true so the node is not found and the method returns null
    }

    public void deleteNode(int id){
      if(id==1){
        FireOccurenceNode newHead=head;
        head=newHead.nextFireOccurence;
        newHead.nextFireOccurence=null;
      }else if(id == FireOccurenceNode.TotalFireOccurences){
            FireOccurenceNode previous=head;
            for(int i=1;i<FireOccurenceNode.TotalFireOccurences;i++){
                previous=previous.nextFireOccurence; 
            }
            previous.nextFireOccurence=null;

      }else{
            FireOccurenceNode previous=head;
            int count=FireOccurenceNode.TotalFireOccurences -id;
            while(count<(id-1)){
                previous=previous.nextFireOccurence;    
                count++;
            }
            FireOccurenceNode current=previous.nextFireOccurence;
            previous.nextFireOccurence=current.nextFireOccurence;
            current.nextFireOccurence=null;
      }
      FireOccurenceNode.TotalFireOccurences--; 

    }
    //method that takes a path of a file as a string 
    public void setRecordFileDirectory(String fileDirectory) throws FileNotFoundException{
        recordFileDirectory= fileDirectory; //set the path to the given oath
        this.file=new File(recordFileDirectory); // 
    }

    public String getHistoryContent(){

        String recordfileContents="";
        String fireIdStr="";             
        FireOccurenceNode node=head;
        if(node==null){
            System.out.println("no fires occured");
            recordfileContents= recordfileContents.concat("no fires occured");
        }

       while(node.nextFireOccurence !=null){
            recordfileContents=recordfileContents.concat("fireID:"+fireIdStr.valueOf(node.getFireId())+"\t fireDate:"+
            node.getFireOccurenceDate()+"\t fireTime:"+
            node.getFireOccurenceTime());
            node=node.nextFireOccurence;
        }
        recordfileContents=recordfileContents.concat("fireID:"+fireIdStr.valueOf(node.getFireId())+"\t fireDate:"+
        node.getFireOccurenceDate()+"\t fireTime:"+node.getFireOccurenceTime());
         return  recordfileContents;   
    }

    //method to write the fires occured in the text file of the given path
    public void recordData() throws IOException{
        //creat a filewriter reference pints to an objject and put the given file path in the constructor
    	FileWriter writer=new FileWriter(recordFileDirectory);
    	String recordfileContents=""; //creat an empty string to record in it the fire data
        String fireIdStr="";       //creat an empty string to change fire id fro int to string
        FireOccurenceNode node=head; //creat a refrence to fire occurence node and st it to the start of the list
        if(node==null){ //if there is no nodes
            System.out.println("no fires occured");
                recordfileContents= recordfileContents.concat("no fires occured");//set the empty string to no fires 
            writer.write(recordfileContents);//write the string in the file
            writer.close(); //end writing
            return; //end the method
        }
        //till last node is reached
       while(node.nextFireOccurence !=null){
           recordfileContents= recordfileContents.concat("fireID:"+fireIdStr.valueOf(node.getFireId())+"\t fireDate:"+
            node.getFireOccurenceDate()+"\t fireTime:"+
            node.getFireOccurenceTime()+"\n"); // add the current node's data to the string
            node=node.nextFireOccurence; //point the refrence to the next node

        }
        System.out.println("entered first node");
        recordfileContents=recordfileContents.concat("fireID:"+fireIdStr.valueOf(node.getFireId())+"\t fireDate:"+
            node.getFireOccurenceDate()+"\t fireTime:"+
            node.getFireOccurenceTime()+"\n"); //add the last node's data to the string
        writer.write(recordfileContents); // write the stringin the file
        writer.close(); // stop writing

    }
    
    
    //method to display the history in a  new desktop window
    public void openHistory() throws IOException{
        //creat a refrence to desktop class to acess the descktop of your computer
        Desktop recordHistoryTextFile=Desktop.getDesktop();
        //open a window containg the text file in the given path 
        recordHistoryTextFile.open(file);
    }

}
