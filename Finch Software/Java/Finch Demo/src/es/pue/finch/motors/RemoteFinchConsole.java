package es.pue.finch.motors;

import java.util.Scanner;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

/**
 * Created by:
 * Date:
 * A starter file to use the Finch
 */

public class RemoteFinchConsole
   {
	static final Integer SPEED = 100;
	static final Integer DURATION = 500;

   public static void main(final String[] args)
      {
	   System.out.println("Finch Starting...");
      // Instantiating the Finch object
      Finch myFinch = new Finch();

      Scanner in = new Scanner(System.in);
      String keypressed = in.nextLine();
      while (!keypressed.equals("q")) {
        switch (keypressed) {
      case "w" : myFinch.setWheelVelocities(SPEED, SPEED, DURATION);
      	      break;
      case "a" : myFinch.setWheelVelocities(-SPEED, SPEED, DURATION);
       	      break;
      case "s" : myFinch.setWheelVelocities(-SPEED, -SPEED, DURATION);
      	      break;
      case "d" : myFinch.setWheelVelocities(SPEED, -SPEED, DURATION);
      	      break;
      }
           keypressed = in.nextLine();  
      }


      myFinch.quit();
      System.out.println("Finch Ending...");
      System.exit(0);
      }
   }

