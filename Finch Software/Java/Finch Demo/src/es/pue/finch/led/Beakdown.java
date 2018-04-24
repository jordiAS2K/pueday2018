package es.pue.finch.led;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


//
//  ----------------------------------------------------------------------
//  LED - Demo 001
//  ----------------------------------------------------------------------
//  Create a program that will continually change the color of the beak
//  from RED/GREEN/BLUE until the finches beak is pointing down.
//  Each color will be displayed for one second
//
class Beakdown {

    public static void main(String[] args) {
        
        System.out.printf("Finch starting... %n");
        Finch myFinch = new Finch();
        
        while(!myFinch.isBeakDown()) {
            myFinch.setLED(255, 0, 0, 1000);
            myFinch.setLED(0, 255, 0, 1000);
            myFinch.setLED(0, 0, 255, 1000);
        }
        
        myFinch.quit();
        System.out.printf("Finch ending... %n");
        
    }
}
