package es.pue.finch.sounds;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

class ImperialMarchComputer {
    
    private static final int C0 = 16;
    private static final int DB0  = 17;
    private static final int D0 = 18;
    private static final int EB0 = 19;
    private static final int E0 = 20;
    private static final int F0 = 21;
    private static final int GB0 = 23;
    private static final int G0 = 24;
    private static final int AB0 = 25;
    private static final int LA0 = 27;
    private static final int BB0 = 29;
    private static final int B0 = 30;
    private static final int C1 = 32;
    private static final int DB1 = 34;
    private static final int D1 = 36;
    private static final int EB1 = 38;
    private static final int E1 = 41;
    private static final int F1 = 43;
    private static final int GB1 = 46;
    private static final int G1 = 49;
    private static final int AB1 = 51;
    private static final int LA1 = 55;
    private static final int BB1 = 58;
    private static final int B1 = 61;
    private static final int C2 = 65;
    private static final int DB2 = 69;
    private static final int D2 = 73;
    private static final int EB2 = 77;
    private static final int E2 = 82;
    private static final int F2 = 87;
    private static final int GB2 = 92;
    private static final int G2 = 98;
    private static final int AB2 = 103;
    private static final int LA2 = 110;
    private static final int BB2 = 116;
    private static final int B2 = 123;
    private static final int C3 = 130;
    private static final int DB3 = 138;
    private static final int D3 = 146;
    private static final int EB3 = 155;
    private static final int E3 = 164;
    private static final int F3 = 174;
    private static final int GB3 = 185;
    private static final int G3 = 196;
    private static final int AB3 = 207;
    private static final int LA3 = 220;
    private static final int BB3 = 233;
    private static final int B3 = 246;
    private static final int C4 = 261;
    private static final int DB4 = 277;
    private static final int D4 = 293;
    private static final int EB4 = 311;
    private static final int E4 = 329;
    private static final int F4 = 349;
    private static final int GB4 = 369;
    private static final int G4 = 392;
    private static final int AB4 = 415;
    private static final int LA4 = 440;
    private static final int BB4 = 466;
    private static final int B4 = 493;
    private static final int C5 = 523;
    private static final int DB5 = 554;
    private static final int D5 = 587;
    private static final int EB5 = 622;
    private static final int E5 = 659;
    private static final int F5 = 698;
    private static final int GB5 = 739;
    private static final int G5 = 783;
    private static final int AB5 = 830;
    private static final int LA5 = 880;
    private static final int BB5 = 932;
    private static final int B5 = 987;
    private static final int C6 = 1046;
    private static final int DB6 = 1108;
    private static final int D6 = 1174;
    private static final int EB6 = 1244;
    private static final int E6 = 1318;
    private static final int F6 = 1396;
    private static final int GB6 = 1479;
    private static final int G6 = 1567;
    private static final int AB6 = 1661;
    private static final int LA6 = 1760;
    private static final int BB6 = 1864;
    private static final int B6 = 1975;
    private static final int C7 = 2093;
    private static final int DB7 = 2217;
    private static final int D7 = 2349;
    private static final int EB7 = 2489;
    private static final int E7 = 2637;
    private static final int F7 = 2793;
    private static final int GB7 = 2959;
    private static final int G7 = 3135;
    private static final int AB7 = 3322;
    private static final int LA7 = 3520;
    private static final int BB7 = 3729;
    private static final int B7 = 3951;
    private static final int C8 = 4186;
    private static final int DB8 = 4434;
    private static final int D8 = 4698;
    private static final int EB8 = 4978;
    private static final int DURATION = 250;
    private static final int Q  = 60000/DURATION; //quarter 1/4 
    private static final int H  = 2*Q; //half 2/4
    private static final int E  = Q/2;   //eighth 1/8
    private static final int S  = Q/4; // sixteenth 1/16
    private static final int W  = 4*Q;// whole 4/4

    
    public static void main(String[] args)   {
        
        Finch finch = new Finch();
        
        
        finch.playTone(LA3,Q); 
        finch.sleep(1+Q); //finch.sleep( duration should always be 1 ms more than the note in order to separate them.
        finch.playTone(LA3,Q);
        finch.sleep(1+Q);
        finch.playTone(LA3,Q);
        finch.sleep(1+Q);
        finch.playTone(F3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(C4,S);
        finch.sleep(1+S);

        finch.playTone(LA3,Q);
        finch.sleep(1+Q);
        finch.playTone(F3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(C4,S);
        finch.sleep(1+S);
        finch.playTone(LA3,H);
        finch.sleep(1+H);

        finch.setWheelVelocities(255, 255);
        
        finch.playTone(E4,Q); 
        finch.sleep(1+Q); 
        finch.playTone(E4,Q);
        finch.sleep(1+Q);
        finch.playTone(E4,Q);
        finch.sleep(1+Q);
        finch.playTone(F4,E+S);
        finch.sleep(1+E+S);
        finch.playTone(C4,S);
        finch.sleep(1+S);

        finch.playTone(AB3,Q);
        finch.sleep(1+Q);
        finch.playTone(F3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(C4,S);
        finch.sleep(1+S);
        finch.playTone(LA3,H);
        finch.sleep(1+H);

        finch.stopWheels();
        
        finch.playTone(LA4,Q);
        finch.sleep(1+Q);
        finch.playTone(LA3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(LA3,S);
        finch.sleep(1+S);
        finch.playTone(LA4,Q);
        finch.sleep(1+Q);
        finch.playTone(AB4,E+S);
        finch.sleep(1+E+S);
        finch.playTone(G4,S);
        finch.sleep(1+S);

        finch.playTone(GB4,S);
        finch.sleep(1+S);
        finch.playTone(E4,S);
        finch.sleep(1+S);
        finch.playTone(F4,E);
        finch.sleep(1+E);
        finch.sleep(1+E);//PAUSE
        finch.playTone(BB3,E);
        finch.sleep(1+E);
        finch.playTone(EB4,Q);
        finch.sleep(1+Q);
        finch.playTone(D4,E+S);
        finch.sleep(1+E+S);
        finch.playTone(DB4,S);
        finch.sleep(1+S);

        finch.playTone(C4,S);
        finch.sleep(1+S);
        finch.playTone(B3,S);
        finch.sleep(1+S);
        finch.playTone(C4,E);
        finch.sleep(1+E);
        finch.sleep(1+E);
        finch.playTone(F3,E);
        finch.sleep(1+E);
        finch.playTone(AB3,Q);
        finch.sleep(1+Q);
        finch.playTone(F3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(LA3,S);
        finch.sleep(1+S);

        finch.playTone(C4,Q);
        finch.sleep(1+Q);
        finch.playTone(LA3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(C4,S);
        finch.sleep(1+S);
        finch.playTone(E4,H);
        finch.sleep(1+H);

        finch.playTone(LA4,Q);
        finch.sleep(1+Q);
        finch.playTone(LA3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(LA3,S);
        finch.sleep(1+S);
        finch.playTone(LA4,Q);
        finch.sleep(1+Q);
        finch.playTone(AB4,E+S);
        finch.sleep(1+E+S);
        finch.playTone(G4,S);
        finch.sleep(1+S);

        finch.playTone(GB4,S);
        finch.sleep(1+S);
        finch.playTone(E4,S);
        finch.sleep(1+S);
        finch.playTone(F4,E);
        finch.sleep(1+E);
        finch.sleep(1+E);//PAUSE
        finch.playTone(BB3,E);
        finch.sleep(1+E);
        finch.playTone(EB4,Q);
        finch.sleep(1+Q);
        finch.playTone(D4,E+S);
        finch.sleep(1+E+S);
        finch.playTone(DB4,S);
        finch.sleep(1+S);

        finch.playTone(C4,S);
        finch.sleep(1+S);
        finch.playTone(B3,S);
        finch.sleep(1+S);
        finch.playTone(C4,E);
        finch.sleep(1+E);
        finch.sleep(1+E);//PAUSE QUASI FINE RIGA
        finch.playTone(F3,E);
        finch.sleep(1+E);
        finch.playTone(AB3,Q);
        finch.sleep(1+Q);
        finch.playTone(F3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(C4,S);
        finch.sleep(1+S);

        finch.playTone(LA3,Q);
        finch.sleep(1+Q);
        finch.playTone(F3,E+S);
        finch.sleep(1+E+S);
        finch.playTone(C4,S);
        finch.sleep(1+S);
        finch.playTone(LA3,H);
        finch.sleep(1+H);

        finch.sleep(2*H);
        finch.quit();
        
    }
}
