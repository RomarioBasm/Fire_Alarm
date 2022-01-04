
package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import firealarmm.FireOccurence;

public class ClientHandler  extends Thread  
{
     DataInputStream dis;
     PrintStream ps;
     FireOccurenceList ll = new FireOccurenceList();

     static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();                                    // the vector here is to keep and save each client data and keep track on it
     
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
     public ClientHandler(Socket s)
	 {
         try
		 {
            dis = new DataInputStream(s.getInputStream()); 
            ps = new PrintStream(s.getOutputStream());     
            clientsVector.add(this);                                                                                  // the word 'this' indicates the chatHandler object socket.
            System.out.println("dhdsdshjf");
            start();
         }
		 catch(Exception ex)
		 {
			 
		 }
     }
     
     public void run()
	 {
          while(true)
		  {
             try
			 {
                  System.out.println("request thread");
                  String request = dis.readLine();
                  System.out.println(request);
				  
                  if(request.equals("1"))
				  {
                       System.out.println("Hello from the other side");												// test for debugging info
                        FireOccurenceNode newNode = new FireOccurenceNode(FireOccurenceNode.TotalFireOccurences+1); // updating server with data of new fire event in a form of node tobe put in the history list
                        ll.appendNode(newNode);                                                                     // adding new node (fire event) to the list of events history
                  }
                  else if(request.equals("2"))
				  {
                       System.out.println(" 222222 ");																// test for debuging info.
                       ll.setRecordFileDirectory("C:\\Users\\sara\\Downloads\\project\\files\\history.txt");		// accessing & write in the file 'History.txt' on the pc harddisk that include all fire events records 
                       ll.recordData();																				// save recorded data in the file 'History.txt' on the pc harddisk that include all fire events records 
                       ll.openHistory();																			// show history on UI in a txt opened from harddisk
                  }
             }
			 catch(IOException ex)
			 {
				 
			 }
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
