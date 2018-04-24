package es.pue.finch.temperature;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;

public class Demo {

    public static void main(String[] args) {
        
        Finch finch = new Finch();
        
        finch.setLED(Color.BLUE);
        
        double initialTemperature = finch.getTemperature();
        double currentTemperature;
        while (!finch.isFinchUpsideDown()) {
            
            currentTemperature = finch.getTemperature();
            System.out.printf("La temperatura es: %.2f ºC %n", currentTemperature);
            
            if (currentTemperature - initialTemperature > 0.5) {
                finch.setLED(Color.RED, 100);
                finch.buzzBlocking(1000, 100);
                finch.setLED(Color.WHITE, 100);
                finch.buzzBlocking(1000, 100);
                finch.setLED(Color.RED, 100);
                finch.buzzBlocking(1000, 100);
                finch.setLED(Color.WHITE, 100);
            }
            
        }
        
        finch.quit();
    }
    
}
