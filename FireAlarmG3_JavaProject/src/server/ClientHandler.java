package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
    FireOccurenceList ll = new FireOccurenceList();

    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();                                    // the vector here is to keep and save each client data and keep track on it

  /******************************************** Con-ClientHandler() ************************************************
   * This constructor is called inside the server class file, it contains an object ------------------------------ *
    @param
    @return
  *****************************************************************************************************************/
    public ClientHandler(Socket s) {
        try {
            dis = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
            clientsVector.add(this);                                                                                  // the word 'this' indicates the chatHandler object socket.
            System.out.println("There is a new client! ");
            start();
        } catch (Exception ex) {

        }
    }
    /************************************************** run() ******************************************************
     * This method is to describe a specific behavior to be carried on by the server side which is:--------------- *
     * 1- it creates a string to receive in it a message request from the client side ----------------------------- *
     * 2- it checks the request coming from the client to act upon it. -------------------------------------------- *
     * 3- if the request is "1", that means that the client needs to update the server about a new fire event ----- *
     * 4- if the request is "2", that means that the client needs to get the history records stored by the server-- *
     @param
     @return
     ****************************************************************************************************************/ 
    public void run() {
        while (true) {
            try {
                System.out.print("request thread : ");
                String request = dis.readLine();
                System.out.println(request);

                if (request.equals("1")) {
                    System.out.println("There is a new fire action ! ");												// test for debugging info
                    FireOccurenceNode newNode = new FireOccurenceNode(FireOccurenceNode.TotalFireOccurences + 1); // updating server with data of new fire event in a form of node tobe put in the history list
                    ll.appendNode(newNode);                                                                     // adding new node (fire event) to the list of events history
                } else if (request.equals("2")) {
                    System.out.println("There is a request to display the history.  ");																// test for debuging info.
                    ll.setRecordFileDirectory("History.txt");		// accessing & write in the file 'History.txt' on the pc harddisk that include all fire events records 
                    ll.recordData();																				// save recorded data in the file 'History.txt' on the pc harddisk that include all fire events records 
                    ll.openHistory();																			// show history on UI in a txt opened from harddisk
                }
            } catch (IOException ex) {

            }
        }
    }

    /************************************************** debug-section **********************************************
    *This part of commented code is for testing and debugging purposes only -------------------------------------- *
    ****************************************************************************************************************/
    /*void sendMessageToAll(String msg){
         for(ClientHandler ch:clientsVector){
             ch.ps.println(msg+" "+ch.getId());
         }
     }*/
}
