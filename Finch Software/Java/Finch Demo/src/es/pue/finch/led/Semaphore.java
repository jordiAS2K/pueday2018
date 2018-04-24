package es.pue.finch.led;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;


//
//  ----------------------------------------------------------------------
//  LED - Demo 002
//  ----------------------------------------------------------------------
//  It's late at night and the corner traffic signal has broken down.
//  If the Finch can demonstrate the ability to follow the correct pattern for the corner 
//  traffic light, you can earn big money hiring your robot out for the evening.
//  The corner traffic light is green for 9 seconds, yellow for two seconds and red 
//  for 5 seconds.
//
class Semaphore {

    public static void main(String[] args) {
        
        System.out.printf("Finch starting... %n");
        Finch myFinch = new Finch();
        
        while(!myFinch.isObstacle()) {
            //Green Light
            myFinch.setLED(Color.GREEN, 8000);
            for(int i=1; i<=10;i++) {
                myFinch.setLED(Color.BLACK, 50);
                myFinch.setLED(Color.GREEN, 50);
                myFinch.buzzBlocking(1000, 50);
            }
            
            //Yellow Light
            myFinch.setLED(Color.YELLOW, 2000);
            
            //Red Light
            myFinch.setLED(Color.RED, 4000);
            for(int i=1; i<=10;i++) {
                myFinch.setLED(Color.BLACK, 50);
                myFinch.setLED(Color.RED, 50);
                myFinch.buzzBlocking(1000, 50);
            }
        }
        
        myFinch.quit();
        System.out.printf("Finch ending... %n");
        
    }
}
