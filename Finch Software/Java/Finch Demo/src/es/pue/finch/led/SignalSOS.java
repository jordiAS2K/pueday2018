package es.pue.finch.led;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;


//
//  ----------------------------------------------------------------------
//  LED - Demo 003
//  ----------------------------------------------------------------------
//  Create a program that displays the international distress signal SOS
//  (3 dots, 3 dashes and 3 dots)
//
//  La señal internacional de socorro SOS se transmite mediante tres pulsos o sonidos cortos, 
//  seguido de tres largos y tres cortos más.
//  En código morse se escribiría como • • • — — — • • •.
//
//  Una particularidad del SOS es su forma de transmitirlo ya que pese a ser tres letras separadas, 
//  que se deberían transmitir como tres pulsos cortos, una pausa, tres pulsos largos, una pausa y 
//  tres pulsos cortos, la transmisión de la señal internacional de socorro se transmite como un código 
//  continuo sin ninguna pausa.
//
//  https://www.nauticalnewstoday.com/conoces-senal-internacional-de-socorro-sos/

class SignalSOS {

    private static Finch myFinch;
    private static final int DOT_DURATION = 250;
    private static final int DASH_DURATION = 500;
    private static final int PAUSE = 200;

    public static void main(String[] args) {
        
        System.out.printf("Finch starting... %n");
        myFinch = new Finch();
        
        while(!myFinch.isObstacle()){
            myFinch.setLED(Color.GREEN);
        }
        
        for(int i = 1; i<=3; i++) {
            System.out.printf("SOS %n");
            dots();
            dashes();
            dots();
            
            myFinch.sleep(1000);
        }
        
        myFinch.quit();
        System.out.printf("Finch ending... %n");
        
    }
    
    static void dots() {
        for (int i = 1; i<=3; i++) {
            myFinch.buzz(1000, DOT_DURATION);
            myFinch.setLED(Color.RED, DOT_DURATION);
            myFinch.setLED(Color.BLACK, PAUSE);
        }
    }
	
    static void dashes() {
	for (int i = 1; i<=3; i++) {
            myFinch.buzz(1000, DASH_DURATION);
            myFinch.setLED(255, 0, 0, DASH_DURATION);
            myFinch.setLED(0, 0, 0, PAUSE);
        }
    }
}
