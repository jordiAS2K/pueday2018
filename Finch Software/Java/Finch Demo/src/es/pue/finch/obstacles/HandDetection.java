package es.pue.finch.obstacles;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class HandDetection {

    public static void main(String[] args) {
        
        Finch finch = new Finch();
        
        while(!finch.isFinchUpsideDown()) {
            
            if (finch.isObstacleLeftSide() && finch.isObstacleRightSide()) {
                finch.setWheelVelocities(100, 100, 500);
            }
            else if (finch.isObstacleLeftSide()) {
                finch.setWheelVelocities(100, 150, 500);
            }
            else if (finch.isObstacleRightSide()) {
                finch.setWheelVelocities(150, 100, 500);
            }
            else {
                finch.stopWheels();
            }
        }
        
        finch.quit();
    }
}
