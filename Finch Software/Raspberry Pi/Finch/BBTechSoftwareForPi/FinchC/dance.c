/* Little Finch dance program, could be used as a starter file
   Tom Lauwers, 12/5/2012 */


#include <stdio.h>
#include <stdlib.h>

#include "Finch.h"

#ifdef _LINUX_
#include <pthread.h>
#else
#include <conio.h>
#include <windows.h>
#endif

/*
 * main entrypoint
 */
int main(int argc, char* argv[])
{

    // attempt to connect to the finch hardware
    // ...finch must be already plugged into a usb port
    if (Fin_Init() < 0)
       exit(1);

    // Program code
    Fin_LED(0,255,0);
    Fin_Move(5, 200, 200);
    Fin_LED(255,0,0);
    Fin_Move(5, -200, -200);
    Fin_LED(0,255,255);
    Fin_Move(8, 200, 0);
    Fin_LED(255, 0,255);
    Fin_Move(8, 0, 200);
    Fin_LED(255,255,255);
    Fin_Move(5, -200, -200);

    // disconnect before exiting the program
    Fin_Exit();
    return(0);

}