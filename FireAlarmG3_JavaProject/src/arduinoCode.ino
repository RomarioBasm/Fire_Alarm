
/************************* define ******************************/
int LED = 11;       //set the led pin
int BUZZER = 8;     //set the buzzer pin

int analog = A0;    //set the analog pin for the temperature sensor 
int vout = 0;       //variable to get the temperature from the sensor
int data;           //buffer to get the data from the software

int flag = 0;
 
/************************* setup ********************************
* This method always run immediately, whether or not the -------*
* fire exists. to receive a data from the hardware -------------*
* @param                                                        *
* @return                                                       *
****************************************************************/
void setup() {
  Serial.begin(9600);     //set the bautrate of the serial communication
  pinMode(LED,OUTPUT);    //set the led pin as a digital output pin 
  pinMode(BUZZER,OUTPUT); //set the buzzer pin as a digital output pin 
  Serial.setTimeout(50);  //set the timeout of the communication
  delay(50);
}

/************************* loop *********************************
* This method always run immediately, whether or not the -------*
* fire exists. to take an action based on if there are ---------*
* a fire or not ------------------------------------------------*
* @param                                                        *
* @return                                                       *
****************************************************************/
void loop() {
  
  vout=analogRead(analog);  //get the analog read of the temperature sensor
  vout=(vout*500)/1023;     //set the value to the required 
  Serial.println(vout);     //sent the value to the serial port of the arduino
  
  if(flag == 1){ //if there is a fire, the led will blink.
      digitalWrite(LED,HIGH);
      delay(500);
      digitalWrite(LED,LOW);
      delay(500); 
  } 
  if(flag == 0){
    digitalWrite(LED,LOW);
    digitalWrite(BUZZER,LOW);
  }
  
  if(Serial.available()>0){         //polling to get a message in the serial buffer from the software.
      data = Serial.read();
     
      if(data == '0' || data == 0){ //signal from the software to start the connection.
          flag = 0;
          digitalWrite(LED,LOW);
          digitalWrite(BUZZER,LOW);
      }
      if(data == '1' || data == 1){ //signal from the software when there is a fire.
        flag = 1;
        digitalWrite(LED,HIGH);
        delay(50);
        digitalWrite(LED,LOW);
        delay(50);
        digitalWrite(BUZZER,HIGH);
      }
      
      if(data == '2'||data == 2 ){ //signal from the software to stop the alert.
        flag = 0;
        digitalWrite(LED,LOW);
        digitalWrite(BUZZER,LOW);
      }
  }
  delay(200);
}