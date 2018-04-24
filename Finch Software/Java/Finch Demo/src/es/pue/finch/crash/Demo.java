package es.pue.finch.crash;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;


public class Demo {

    public static void main(String[] args) {
        Finch myFinch = new Finch();
        
        myFinch.setWheelVelocities(255, 255);
        while(!myFinch.isObstacle()){}
        
        myFinch.stopWheels();
        myFinch.setLED(Color.RED, 500);
        myFinch.buzz(262, 500);
        myFinch.saySomething("CRASH");
        myFinch.setLED(Color.RED, 500);
        myFinch.buzz(262, 500);
        myFinch.setLED(Color.RED, 500);
        myFinch.buzz(262, 500);
        myFinch.setLED(Color.RED, 500);
        myFinch.buzz(262, 500);
        myFinch.setWheelVelocities(-50, -50, 2000);
        myFinch.setLED(Color.GREEN);
        myFinch.saySomething("THANKS!!");
        myFinch.sleep(2000);
        myFinch.quit();
    }
}
