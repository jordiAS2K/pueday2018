package es.pue.finch.sounds;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


//
//  ----------------------------------------------------------------------
//  Sounds - Demo 003
//  ----------------------------------------------------------------------
//  Create a program that will detect when the Finch is shaken and then
//  convert the text "ahh do not shake me" to a synthesized sound.
//
class SaySomething {
    
       
    public static void main(String[] args) {
        
        Finch myFinch = new Finch();
        
        boolean wasShaken = false;
        while (!myFinch.isObstacle()) {
            if (!myFinch.isFinchLevel() && myFinch.isShaken()) {
                wasShaken = true;
                myFinch.saySomething("STOP STOP STOP", 3000);
                System.out.println("Me están moviendo");
            }
            
            if (!myFinch.isShaken() && myFinch.isFinchLevel() && wasShaken) {
                wasShaken = false;
                myFinch.saySomething("GRAA   CII   AAS", 2000);
            }
            
	}
        
        myFinch.quit();
    }
   
    
}
