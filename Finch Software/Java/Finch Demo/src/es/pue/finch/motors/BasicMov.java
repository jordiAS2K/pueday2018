package es.pue.finch.motors;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


class BasicMov {
    
    
    public static void main(String[] args) {
    
        Finch myFinch = new Finch();
        
        myFinch.setWheelVelocities(100, -100, 20000);
        
        myFinch.quit();
        
    }
    
}
