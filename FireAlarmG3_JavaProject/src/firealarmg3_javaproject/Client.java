
package firealarmg3_javaproject;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

public class Client{ 

    Socket mySocket;                           // socket object that handle the connection between the the server and the client.
    DataInputStream dis;                       // an in stream of bytes. accepts input bytes.
    PrintStream ps;                            // an output stream of bytes. accepts bytes and sends them to the hardware. 
    FireOccurence fire = new FireOccurence();  // an object from fire occurenece class, contains all the action.

    /************************** Client  *****************************
    * constructor of the class that create a new socket of the -----*
    * connection between the server and the client, and new input --*
    * and new output stream to send and receive the data -----------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/                                                                                                                                          
    public Client() throws IOException
    {
        mySocket = new Socket("127.0.0.1",5005);
        dis = new DataInputStream(mySocket.getInputStream());
        ps = new PrintStream(mySocket.getOutputStream());
    }
    
    /************************ setData *******************************
    * This method is called to write in the buffer of the outstream-*
    * to send the data from the client to the server----------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public void setData(String msg){
        ps.println(msg);     // write the data in the buffer of the outstream.
    }
    
    
    /************************** start  ******************************
    * This method is called to run a thread that polling to get ----*
    * the toggle in the flag of the fire, to send a request to the--*
    * server -------------------------------------------------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public void start(){
        new Thread(new Runnable(){
            @Override
             public void run(){

                 while(true){
                    try{ Thread.sleep(1000);
                        if(fire.toggle == true){

                            System.out.println("client condition ");
                            setData("1");
                            fire.toggle = false;
                        }
                    } catch (InterruptedException ex) {                 
                         
                    }                 
                 }
             }
         }).start();

    }

}

