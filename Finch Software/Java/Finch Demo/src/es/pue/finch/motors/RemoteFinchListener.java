package es.pue.finch.motors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class RemoteFinchListener extends JFrame implements KeyListener 
{	static final Integer SPEED = 100;
	static final Integer DURATION = 100;
 	JLabel label;
 	Finch myFinch = new Finch();
 	
 	public RemoteFinchListener(String s) {
        super(s);
        JPanel p = new JPanel();
        label = new JLabel("Finch Remote!");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {              
              myFinch.quit();
              System.out.println("Finch Ending...");
              System.exit(0);
            }
        });    }

 	@Override
    public void keyTyped(KeyEvent e) {    }
    @Override
    public void keyReleased(KeyEvent e) {   }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Turning Right");
            myFinch.setWheelVelocities(SPEED, -SPEED, DURATION);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Turning Left");
            myFinch.setWheelVelocities(-SPEED, SPEED, DURATION);
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Going Forward");
            myFinch.setWheelVelocities(SPEED, SPEED, DURATION);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Reversing");
            myFinch.setWheelVelocities(-SPEED, -SPEED, DURATION);
        }

    }

   public static void main(final String[] args)
      {
	   System.out.println("Finch Starting..."); 
       new RemoteFinchListener("Finch Movement");  
      }
   }
  

