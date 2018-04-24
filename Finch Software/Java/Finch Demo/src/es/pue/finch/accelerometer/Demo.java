package es.pue.finch.accelerometer;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;


public class Demo {
    
    public static void main(String[] args) {
        
        Finch myFinch = new Finch();
        
        do {
            
            if (myFinch.isFinchLevel()) {
                //Boca arriba
                myFinch.setLED(Color.GREEN);
                //myFinch.saySomething("OK", 2000);
            }
            if(myFinch.isFinchUpsideDown()) {
                //Boca abajo
                myFinch.setLED(Color.RED);
                myFinch.saySomething("NO OK", 2000);
                myFinch.buzzBlocking(262, 100);
                myFinch.buzzBlocking(262, 100);
            }
            if(myFinch.isBeakUp()) {
                //Pico hacia arriba
                myFinch.setLED(Color.BLUE);
                myFinch.saySomething("BEAK UP", 2000);
            }
            if(myFinch.isBeakDown()) {
                //Pico hacia abajo
                myFinch.setLED(Color.YELLOW);
                myFinch.buzzBlocking(262, 100);
                myFinch.buzzBlocking(262, 100);
                myFinch.saySomething("BEAK DOWN", 2000);
            }
            if (myFinch.isLeftWingDown()) {
                //Ala izquierda hacia abajo
                myFinch.setLED(Color.PINK);
                myFinch.saySomething("LEFT", 2000);
            }
            if (myFinch.isRightWingDown()) {
                //Ala derecha hacia abajo
                myFinch.setLED(Color.MAGENTA);
                myFinch.saySomething("RIGHT", 2000);
            }
            if (myFinch.isTapped()) {
                //Finch golpeado
                System.out.println("Golpeado");
                myFinch.setLED(Color.ORANGE);
                myFinch.saySomething("CRASH", 2000);
                myFinch.buzzBlocking(262, 100);
                myFinch.buzzBlocking(262, 100);
            }
            if (myFinch.isShaken()) {
                myFinch.setLED(Color.RED, 100);
                myFinch.setLED(Color.BLACK, 100);
                myFinch.setLED(Color.RED, 100);
                myFinch.setLED(Color.BLACK, 100);
                myFinch.buzzBlocking(262, 100);
                myFinch.buzzBlocking(262, 100);
            }
            
        } while (!myFinch.isObstacleLeftSide() & !myFinch.isObstacleRightSide());
        
        
        myFinch.quit();
        
    }
}
