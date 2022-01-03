
package server;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import firealarmm.FireOccurence;

public class ClientHandler  extends Thread  {
     DataInputStream dis;
     PrintStream ps;
     FireOccurenceList ll = new FireOccurenceList();

     static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>(); // the vector here is to keep and save each client data and keep track on it
     
 /******************************************** Con-ClientHandler() ************************************************
  * This constructor is called inside the server class file, it contains an object ------------------------------ *
  * that opens a stream to receive data from the destination side (client) through the socket created  ---------- *
  * and also creates an object that allows for it to put on data on the stream through the socket---------------- *
  * Then the client socket is added to vector to keep its data and to be tracked -------------------------------- * 
  * Since our ClientHandler class will run independently of the main application, so it was made in a thread ---- *
  * and so; to make it work, it must has the "start()" method to begin functioning ------------------------------ *
  * try-catch statements are mainly for handling program exceptions to ensure a proper functionality ------------ *  
  @param
  @return
  *****************************************************************************************************************/
     public ClientHandler(Socket s){
         try{
            dis = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
            clientsVector.add(this);                                                           // the word 'this' indicates the chatHandler object socket.
            start();
         }catch(Exception ex){}
     }
  /************************************************** run() ******************************************************
  * This method is to describe a specific behaviour to be carried on by the server side which is:--------------- *
  * 1- it creates a string to receive in it a message request from the client side ----------------------------- *
  * 2- it checks the request coming from the client to act upon it. -------------------------------------------- *
  * 3- if the request is "1", that means that the client needs to update the server about a new fire event ----- *
  * 4- if the request is "2", that means that the client needs to get the history records stored by the server-- *
  @param
  @return
  ****************************************************************************************************************/ 
     public void run(){ 
             try{
                  String request = dis.readLine();
                  System.out.println(request);
                  if(request.equals("1")){
                        FireOccurenceNode newNode = new FireOccurenceNode(
													FireOccurenceNode.TotalFireOccurences+1);
                        ll.appendNode(newNode);                                               // adding new node (fire event) to the list of events history
                  }
                  if(request.equals("2")){
                        
                       ll.setRecordFileDirectory("C:\\Users\\Romario\\Desktop\\History.txt");
                       ll.recordData(); 													  // write in the file 'History.txt' on the pc harddisk that include all fire events records 
                       ll.openHistory();
                  }
             }catch(IOException ex){}
         }
     }
  /************************************************** debug-section **********************************************
  *This part of commented code is for testing and debugging purposes only -------------------------------------- *
  ****************************************************************************************************************/
  
     // sent to the client.
     /*void sendMessageToAll(String msg){
         for(ClientHandler ch:clientsVector){
             ch.ps.println(msg+" "+ch.getId());
         }
     }*/
     
}
