# FIRE ALARM PROJECT GROUP 3

This instruction was updated on Jan 6, 2022.
************************
Authors & contributers:
   Dounia khaled
   \\Hager Mohamed
   \\Mahmoud Dahroug
   \\Romario Bassem
   \\Sara Adel


## Table of contents
* [video Submission](#video-submission)
* [documentation Submission](#documentation-submission)
* [General Information](#general-information)
* [Technologies](#technologies)
* [Implemtation](#implemtation)
* [Future Work](#future-work)

### video Submission
<br /> google drive link:
https://drive.google.com/drive/folders/1yKQvS4skvKgBBO4Z1lEtk1ghU5QCS62-

### documentation Submission
<br /> google docs link:
https://docs.google.com/document/d/1sMRclI51AV_U3ygjXVZddkFAkhpE1qVpf2EEzbOidgg/edit?usp=sharing

### General Information
<br />Project name: Fire Alarm System

<br /> Project Credits:
* final Java project for Java course
* Embedded Systems Department in ITI-Information Technology Inistitute- smart village internship Intake42
* supervised by :
    * Eng.Eman Hesham
    * Eng. Ahmed El-Ashmawy
* done by:
    * Dounia khaled
    * Hager Mohamed
    * Mahmoud Dahroug
    * Romario Bassem
    * Sara Adel

<br />Project description: 
* UI to show the current temprature through a guage 
* fires an alarm (sound - light - warning sign in the UI) when a fire occures
* press stop button to stop the alarm in case of wrong fire detection
* press help button to send a mail to the firefighting truck 
* press on history button to show the data of fires occured since operation including:
    * fire ID: the number of fire since operation
    * fire date: day/month/year
    * fire time: hours:miniutes:seconds  am/pm
 
<br/>Programming Language: 
* Java for application
* C++ for arduino


### Technologies
<br />Project is created with:
* Java developer kit: JDK 8 
* Apache NetBeans IDE version: 12.6
* Scene Builder GLUON version: 8.5.0
* Medusa library version: 8.0

### Implemtation
<br /> Architecture UML class diagram:
![fireAlarm](https://user-images.githubusercontent.com/70255404/148305370-eb183652-b589-4349-a4f1-4e30c6db7387.jpg)

<br /> Architecture Explanation:

* communication package
    * Email class: responsible for sending mails 
    * SerialProtocol class: responisble for connection with ports (arduino board port in our application)
* fire.alarm package (main package)
    * FireOccurence: contain our main functionality to check fire condition and take actions towards the fire
    * Client class: communicates with the server through two requests,
    <br /> these requests either tell server to record a fire or tell it show the recorded fires history 
    * FXML Controller class:contains  the client thread and the main thread responsible for checking fire 
    <br/>and also the FXML objects handlers
    * FireAlarm class:contains the main method responsible for launching the GUI

* server package
    * FireOccurenceNode class: a customized node class for fire, each node represent the fire occurence data 
    <br /> which are the fire temp, date and time of occurence, the fire record number and total number of fires occured
    * FireOccurenceList class: a customized list class where its nodes are the fire occurence nodes  
    * ClientHandler class: responsible for checking the requests from the client and take actions towards it
    * Server class: waits for client requests

### Future Work
<br /> Srever send data to client:
<br /> This will enable us to display the fire history in another tab in the same UI 
<br /> instead of poping up a text file window containing the history
