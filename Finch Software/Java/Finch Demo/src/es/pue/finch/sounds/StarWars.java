package es.pue.finch.sounds;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

class StarWars {
    
    private static Finch FINCH;
    private static final int DURATION = 400;
    private static final int DO5 = 523;
    private static final int RE5 = 587;
    private static final int MI5 = 659;
    private static final int FAS5 = 739;
    private static final int SOL5 = 783;
    private static final int LA5 = 880;
    private static final int LAS5 = 923;
    private static final int SI5 = 987;
    private static final int DO6 = 1046;
    private static final int RE6 = 1174;
    private static final int RES6 = 1244;
    private static final int FA6 = 1396;
    private static final int SOL6 = 1567;
        
    public static void main(String[] args) {
        
        FINCH = new Finch();
                
        mainNotes(2);
        middleNotes();
        mainNotes(3);
        endNotes();
        
        FINCH.quit();
    }
    

    private static void mainNotes(int times) {
        for(int t = 0; t < times; t++) {
            repeatNote(3, RE5, DURATION/3);
            FINCH.buzzBlocking(SOL5, DURATION*2);
            FINCH.buzzBlocking(RE6, DURATION*2);
            for(int n=0; n < 2; n++) soundDoSiLaSolRe();
            FINCH.buzzBlocking(DO6, DURATION/3);
            FINCH.buzzBlocking(SI5, DURATION/3);
            FINCH.buzzBlocking(DO6, DURATION/3);

            FINCH.buzzBlocking(LA5, DURATION*2);
        }
    }
    
    private static void middleNotes() {
        soundReReMiMi();
        middleNotesA();
        soundReReMiMi();
        middleNotesB();
        soundReReMiMi();
        middleNotesA();
        middleNotesC();
    }
    
    private static void middleNotesA() {
        soundDoSiLaSol();
        FINCH.buzzBlocking(SOL5, DURATION/2);
        FINCH.buzzBlocking(LA5, DURATION/4);
        FINCH.buzzBlocking(SI5, DURATION/4);
        FINCH.buzzBlocking(LA5, DURATION/2);
        FINCH.buzzBlocking(MI5, DURATION/2);
        FINCH.buzzBlocking(FAS5, DURATION);
    }

    private static void middleNotesB() {
        soundDoSiLaSol();
        FINCH.buzzBlocking(LA5, DURATION * 2);
    }

    private static void middleNotesC() {
        FINCH.buzzBlocking(RE6, (int)(DURATION/1.5));
        FINCH.buzzBlocking(RE6, DURATION/3);
        FINCH.buzzBlocking(SOL6, (int)(DURATION/1.5));
        FINCH.buzzBlocking(FA6, DURATION/3);
        FINCH.buzzBlocking(RES6, (int)(DURATION/1.5));
        FINCH.buzzBlocking(RE6, DURATION/3);
        FINCH.buzzBlocking(DO6, (int)(DURATION/1.5));
        FINCH.buzzBlocking(LAS5, DURATION/3);
        FINCH.buzzBlocking(LA5, (int)(DURATION/1.5));
        FINCH.buzzBlocking(SOL5, DURATION/3);
        FINCH.buzzBlocking(RE6, DURATION);
        repeatNote(3, LA5, DURATION/3);
        FINCH.buzzBlocking(LA5, DURATION);
    }
    
    private static void endNotes() {
        repeatNote(3, RE6, DURATION/3);
        FINCH.buzzBlocking(SOL6, DURATION*2);
        repeatNote(3, SOL5, DURATION/3);
        FINCH.buzzBlocking(SOL5, DURATION*2);
   }
    

    private static void soundDoSiLaSolRe() {
        FINCH.buzzBlocking(DO6, DURATION/3);
        FINCH.buzzBlocking(SI5, DURATION/3);
        FINCH.buzzBlocking(LA5, DURATION/3);

        FINCH.buzzBlocking(SOL6, DURATION*2);
        FINCH.buzzBlocking(RE6, DURATION);
    }

    private static void soundReReMiMi() {
        FINCH.buzzBlocking(RE5, (int)(DURATION/1.5));
        FINCH.buzzBlocking(RE5, DURATION/3);
        FINCH.buzzBlocking(MI5, (int)(DURATION*1.5));
        FINCH.buzzBlocking(MI5, DURATION/2);
    }

    private static void soundDoSiLaSol() {
        FINCH.buzzBlocking(DO6, DURATION/2);
        FINCH.buzzBlocking(SI5, DURATION/2);
        FINCH.buzzBlocking(LA5, DURATION/2);
        FINCH.buzzBlocking(SOL5, DURATION/2);
    }

    private static void repeatNote(int times, int frequency, int milliseconds) {
        for (int n = 0; n < times; n++) {
            FINCH.buzzBlocking(frequency, milliseconds);
        }
    }
}
