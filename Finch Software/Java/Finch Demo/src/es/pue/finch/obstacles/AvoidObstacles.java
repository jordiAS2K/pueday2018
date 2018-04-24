package es.pue.finch.obstacles;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class AvoidObstacles {
   public static void main(final String[] args) {
      // Instantiating the Finch object
      Finch finch = new Finch();

      // Run so long as the Finch is not pointed beak down
      while(!finch.isBeakDown()) {
        if (finch.isObstacleLeftSide() && finch.isObstacleRightSide()) {
            finch.setLED(125, 125, 125);
            finch.sleep(500);
            finch.setWheelVelocities(-100,-100, 750);
            finch.setWheelVelocities(100,-255, 500);
        }
        else if(finch.isObstacleLeftSide() && !finch.isObstacleRightSide()) {
            // If there's an obstacle on the left, turn LED red, back up for 750 ms
            // and turn for 500 ms
            finch.setLED(255,0,0);
            finch.setWheelVelocities(-255,-255,750);
            finch.setWheelVelocities(100,-255, 500);
            finch.buzz(440, 500);
        }
        else if(finch.isObstacleRightSide() && !finch.isObstacleLeftSide()) {
            // If there's an obstacle on the right, set LED blue, back up for 750 ms
            // and turn for 500 ms
            finch.setLED(0,0,255);
            finch.setWheelVelocities(-255,-255,750);
            finch.setWheelVelocities(-255, 100, 500);
            
            finch.buzz(880, 500);
        }
        else {
            // Else, robot goes straight, LED is green
            finch.setWheelVelocities(255,255);
            finch.setLED(0,255,0);
        }
       }
      
      // Always end your program with finch.quit()
      finch.quit();
      System.exit(0);
    }

}
