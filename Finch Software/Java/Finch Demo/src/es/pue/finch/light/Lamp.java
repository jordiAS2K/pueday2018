package es.pue.finch.light;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;

public class Lamp {

    public static int LIGHT_LEVEL = 20;
    
    public static void main(String[] args) {
        
        Finch finch = new Finch();
        
        while(!finch.isFinchUpsideDown()) {
            
            if (finch.getLeftLightSensor() < LIGHT_LEVEL && finch.getRightLightSensor() < LIGHT_LEVEL) {
                finch.setLED(Color.YELLOW);
                finch.sleep(1000);
                finch.saySomething("NIGHT MODE");
                do {
                }
                while(!finch.isLeftLightSensor(LIGHT_LEVEL) && !finch.isLeftLightSensor(LIGHT_LEVEL));
                finch.setLED(Color.BLACK);
            }
            
        }
        
    }
}
