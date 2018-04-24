package es.pue.finch.dancing;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;


public class Demo {

    public static void main(String[] args) {
        
        Finch myFinch = new Finch();
        myFinch.playClip("C:\\Users\\jordia\\Desktop\\PUE DAY\\PUE DAY 2017\\01.- The Finch Robot\\02.- Code\\01.- Demo Code\\Finch Demo\\src\\es\\pue\\pueday2017\\finch\\dancing\\DancingQueen.mid");
        
        myFinch.sleep(2000);
        while(!myFinch.isFinchUpsideDown()) {
             myFinch.setLED(Color.GREEN, 1000);
                
            for(int i=0; i<=2; i++) {
                
                myFinch.setLED(Color.BLUE);
                myFinch.setWheelVelocities(100, 100, 1000);
                myFinch.setWheelVelocities(-100, -100, 1000);

                myFinch.setWheelVelocities(150, 100, 1500);
                myFinch.setWheelVelocities(-150, -100, 1500);

                myFinch.setLED(Color.BLACK);
                
                myFinch.setLED(Color.RED);
                myFinch.setWheelVelocities(150, -150, 3000);
                myFinch.setWheelVelocities(-150, 150, 3000);
                myFinch.setLED(Color.BLACK);

                myFinch.setLED(Color.BLUE);
                myFinch.setWheelVelocities(100, 100, 1000);
                myFinch.setWheelVelocities(-100, -100, 1000);

                myFinch.setWheelVelocities(150, 100, 1500);
                myFinch.setWheelVelocities(-150, -100, 1500);
                myFinch.setLED(Color.BLACK);
                
                myFinch.setLED(Color.RED);
                myFinch.setWheelVelocities(-150, 150, 3000);
                myFinch.setWheelVelocities(150, -150, 3000);
                myFinch.setLED(Color.BLACK);
                
                myFinch.setLED(Color.GREEN);
                myFinch.setWheelVelocities(200, 100, 5000);
                myFinch.setWheelVelocities(255, 255, 1000);
                myFinch.setWheelVelocities(-255, -255, 2000);
                myFinch.setWheelVelocities(255, 255, 1000);
                myFinch.setLED(Color.BLACK);
            }
        }
        
        myFinch.quit();
        
        
        
    }
}
