package es.pue.finch.motors;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


class Square {
    
    
    public static void main(String[] args) {
    
        Finch myFinch = new Finch();
        
        myFinch.setWheelVelocities(100, 100, 2000);
        myFinch.setWheelVelocities(100, -100, 850);
        myFinch.setWheelVelocities(100, 100, 2000);
        myFinch.setWheelVelocities(100, -100, 850);
        myFinch.setWheelVelocities(100, 100, 2000);
        myFinch.setWheelVelocities(100, -100, 850);
        myFinch.setWheelVelocities(100, 100, 2000);
        myFinch.setWheelVelocities(100, -100, 850);
        
        myFinch.quit();
        
    }
    
}
