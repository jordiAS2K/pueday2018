package es.pue.finch.light;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Arrays;

public class LightDetection {

    public static void main(String[] args) {
        
        Finch finch = new Finch();
        
        int[] sensors = new int[2];
        sensors = finch.getLightSensors();
        
        while(!finch.isFinchUpsideDown()) {
            
            if (finch.getLeftLightSensor() > (sensors[0] + 3) && finch.getRightLightSensor()> (sensors[1] + 3)) {
                
                finch.setLED(255, 0, 0);
                if (finch.getLeftLightSensor() > (finch.getRightLightSensor() + 6)) {
                     finch.setWheelVelocities(50, 255);
                }
                else if (finch.getRightLightSensor() > finch.getLeftLightSensor() + 6) {
                     finch.setWheelVelocities(255, 50);
                }
                else {
                       finch.setWheelVelocities(255, 255);
                } 
            }
            else {
                finch.setLED(0, 0, 0);
                finch.stopWheels();
            }
        }
        
        finch.quit();
    }
}
