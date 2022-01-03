
package communication;

public class SerialProtocol {
    
    Scanner in;              // an in stream of bytes. accepts input bytes.
    OutputStream out;        // an output stream of bytes. accepts bytes and sends them to the hardware. 
    static int temper;       // int variable that represent the 
    static SerialPort port;  // serialPort, that represent the port.
    
    
    /********************** arduinoConnection  **********************
    * This method create a new connection between the hardware and -*
    * the software -------------------------------------------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public void arduinoConnection() {
        Vector<String> portList = new Vector<String>();
        SerialPort[] portNames = SerialPort.getCommPorts();
        String comPort = "COM3";
//                for (int i = 0; i < portNames.length; i++) {
//                    portList.add(portNames[i].getSystemPortName());
//                    System.out.println(i + "- " + portNames[i].getSystemPortName());
//                    comPort = portNames[i].getSystemPortName();
//                }
        port = SerialPort.getCommPort(comPort);
        port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        in = new Scanner(port.getInputStream());
        port.openPort();
    }
    
    
    /************************* receive  *****************************
    * This method always run immediately, whether or not the -------*
    * fire exists. to receive a data from the hardware -------------*
    * @param                                                        *
    * @return                                                       *
    ****************************************************************/
    public void receive() {
        Thread t2 = new Thread(new Runnable(){
           @Override
           public void run() {
                    try{
                        System.out.println("Hello");
                    }catch(Exception e){}                
                   
                    while (in.hasNextLine()) {//in.hasNextLine()
                        try {
                           temper = Integer.parseInt(in.nextLine());
                           //System.out.println(temper);
                           Thread.sleep(50);
                        } catch (Exception e) {}
                    }               
            }
        });
        t2.start();
    }
    
    
}
