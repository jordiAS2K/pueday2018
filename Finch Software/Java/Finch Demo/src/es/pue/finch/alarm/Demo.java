package es.pue.finch.alarm;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;

public class Demo {
    
    public static void main(String[] args) {
        
        Finch finch = new Finch();
        
        while(!finch.isObstacle()) {
            
            if (!finch.isFinchLevel() || finch.isShaken() || finch.isTapped()) {
                do {
                    finch.setLED(Color.RED, 100);
                    finch.buzzBlocking(1000, 100);
                    finch.setLED(Color.WHITE, 100);
                    finch.buzzBlocking(1000, 100);
                    finch.setLED(Color.RED, 100);
                    finch.buzzBlocking(1000, 100);
                    finch.setLED(Color.WHITE, 100);
                } while(!finch.isFinchLevel() || finch.isShaken() || finch.isTapped());
                
                if (finch.isFinchLevel()) finch.setLED(Color.GREEN, 500);
            }
            
        }
        
        finch.quit();
    }
    
}
