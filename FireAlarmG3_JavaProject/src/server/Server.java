
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class Server 
{
  /******************************************** Con-Server() ************************************************
  * This constructor is called once the file contains its class is run, it contains an object ------------ *
  * that makes a network internal socket to the main application that enables a session to be ------------ *
  * opened between the server side and the client side (GUI) --------------------------------------------- *
  * Then the client socket is created and told to be accepted from the server during the handshake process * 
  * and sent as a parameter to ChatHandler class constructor to function by sending and receiving data --- *
  
  @param
  @return
  **********************************************************************************************************/
   Server() throws IOException
   {
            ServerSocket ser = new ServerSocket(5005);									// make new socket for Server of main application with internal port number (50005)
            while(true)                                                                 // to accept clients as many as Guis could be created
			{
				System.out.println("i am server");                                      // for debugging and tracing info.
                try{
                Socket s = ser.accept();
                new ClientHandler(s);													// this line is to preserve new client objects created to avoid data loss
                }
                catch(IOException ex){
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);                
                }               
            }
    }

  /******************************************** main() *****************************************************
  * This is the main entry point/ function from where it creates a Server object so that the main -------- *
  * application could have a server that can store data and history and provide it at any given time ----- *
  
  @param (String [])
  @return 
  **********************************************************************************************************/
 public static void main(String[] args) throws IOException 
 
  {
        Server server = new Server();
  }
}
