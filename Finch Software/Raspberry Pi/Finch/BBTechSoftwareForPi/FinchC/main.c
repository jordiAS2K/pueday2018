#if 0
Finch Robot Control  (finch.exe)

Notes:
- finch.exe was compiled for a 32-bit windows O/S
- make sure that the hidapi.dll is in the current working directory
- plug in the finch-usb cable before starting the program

#endif


#include <stdio.h>
#include <stdlib.h>

#include "Finch.h"

#ifdef _LINUX_
#include <pthread.h>
#else
#include <conio.h>
#include <windows.h>
#endif

// use this to output the statistics to a disk file
#define DISKFILE

// represents 5.0 seconds of history data
#define ENTRIES 50

struct hist {
   int left;
   int right;
   float x;
   float y;
   float z;
};

// place to store last 5.0 seconds of sensor information
struct hist snapshot[ENTRIES] = {0};
int snapofst = 0;
int history = 0;

// local prototypes
void Help(void);
void Command(char option);
char *CheckForInput(void);
char *GetInput(void);
void Pause(int msec);
void Report(void);
char *AccelText(float x, float y, float z);
char *PosText(float x, float y, float z);


/*
 * main entrypoint
 */
int main(int argc, char* argv[])
{
    char *option;

    // attempt to connect to the finch hardware
    // ...finch must be already plugged into a usb port
    if (Fin_Init() < 0)
       exit(1);

    Help();

    while (1)
    {
        // throw away any outstanding keyboard characters
        CheckForInput();

        // wait for a new command
        // ...accepts a single keypress
        printf("\nSelect Command (and press ENTER): ");
        option = GetInput();

        if (*option == 'Q' || *option == 'q')
            break;

        // perform the command
        Command(*option);
    }

    // disconnect before exiting the program
    Fin_Exit();
    return(0);
}


/*
 * display help information
 */
void Help(void)
{
    // all commands are single letters + <enter>
    printf("\nMovement:\n");
    printf("  F - Forward (F:fast, f:slow)\n");
    printf("  B - Backward (B:fast, b:slow)\n");
    printf("  L - Left (L:tight turn, l:wide turn)\n");
    printf("  R - Right (R:tight turn, r:wide turn)\n");

    printf("\nControls:\n");
    printf("  Y - Led (Y:on/off, y:solid)\n");
    printf("  Z - Buzzer (Z:on/off, z:continuous)\n");

    printf("\nSensors (upper case is continuous read):\n");
    printf("  A - Accelerometer\n");
    printf("  O - Obstacle\n");
    printf("  S - Light Sensor\n");
    printf("  T - Temperature\n");
#ifdef DISKFILE
    printf("  H - History Report File (H:collect, h:display)\n");
#else
    printf("  H - History Report (H:collect, h:display)\n");
#endif

    printf("\n");
    printf("  ?- Help\n");
    printf("  Q- Quit\n");
}


/*
 * perform the finch command
 */
void Command(char option)
{
    float fd1,fd2,fd3;
    int d1,d2,d3;
    char *textp;

    switch (option)
    {
        case '?':
            // display the help message
            Help();
            break;

        // motor on/off (F-fwd, B-back, L-left, R-right)
        case 'F':
            Fin_Motor(8,200,200);
            break;
        case 'f':
            Fin_Motor(21,90,90);
            break;

        case 'B':
            Fin_Motor(8,-200,-200);
            break;
        case 'b':
            Fin_Motor(21,-90,-90);
            break;

        case 'L':
            Fin_Motor(5,-140,140);
            break;
        case 'l':
            Fin_Motor(11,80,200);
            break;

        case 'R':
            Fin_Motor(5,140,-140);
            break;
        case 'r':
            Fin_Motor(11,200,80);
            break;

        // acceleration sensors
        case 'A':
            do
            {
                Fin_Accel(&fd1,&fd2,&fd3,&d1,&d2);
                printf("%s  %s   Tap:%d   Shake:%d \r", PosText(fd1,fd2,fd3), AccelText(fd1, fd2, fd3), d1,d2);
                Sleep(300);
            } while (!kbhit());
            printf("\n");
            break;
        case 'a':
            Fin_Accel(&fd1,&fd2,&fd3,&d1,&d2);
            printf("%s  %s   Tap:%d   Shake:%d\n", PosText(fd1,fd2,fd3), AccelText(fd1, fd2, fd3), d1,d2);
            break;

        // obstacle sensors
        case 'O':
            do
            {
                Fin_Obstacle(&d1,&d2);
                printf("Obstacle -- Left:%d   Right:%d \r", d1,d2);
                Sleep(300);
            } while (!kbhit());
            printf("\n");
            break;
        case 'o':
            Fin_Obstacle(&d1,&d2);
            printf("Obstacle -- Left:%d   Right:%d\n", d1,d2);
            break;

        // light sensors
        case 'S':
            do
            {
                Fin_Lights(&d1,&d2);
                printf("Lights -- Left:%3d   Right:%3d \r", d1,d2);
                Sleep(300);
            } while (!kbhit());
            printf("\n");
            break;
        case 's':
            Fin_Lights(&d1,&d2);
            printf("Lights -- Left:%3d   Right:%3d\n", d1,d2);
            break;

        // temperature sensors
        case 'T':
            do
            {
                Fin_Temp(&fd1);
                printf("%3.4f Celsius \r", fd1);
                Sleep(1000);
            } while (!kbhit());
            printf("\n");
            break;
        case 't':
            Fin_Temp(&fd1);
            printf("%3.4f Celsius\n", fd1);
            break;

        // led colors (blink or continuous)
        case 'Y':
        case 'y':
            printf("Enter the three color values:  red,green,blue  (0-255): ");
            textp = GetInput();
            sscanf(textp,"%d,%d,%d",&d1,&d2,&d3);
            do
            {
                Fin_LED(d1,d2,d3);
                if (option == 'y')
                    break;
                Sleep(250);
                Fin_LED(0,0,0);
                Sleep(250);
            } while (!kbhit());
            break;

        // buzzer (blink or continuous)
        case 'Z':
        case 'z':
            printf("Enter the buzzer frequency: ");
            textp = GetInput();
            sscanf(textp,"%d",&d1);
            do
            {
                Fin_Buzzer(0xffff,d1);
                if (option == 'z')
                    break;
                Sleep(250);
                Fin_Buzzer(0,0);
                Sleep(250);
            } while (!kbhit());
            break;

        // motor and acceleration history
        case 'H':
            history = 1;
            printf("Reporting on\n");
            break;
        case 'h':
            history = 0;
            Report();
            break;
    }
}


/*
 * check for a keypress
 */
char *GetInput(void)
{
   // check for incoming command
   // collect history if enabled
   while (!kbhit())
       Pause(100);

   return(CheckForInput());
}


/*
 * pause and do the background history collection
 */
void Pause(int msec)
{
    float fd1,fd2,fd3;
    int d1,d2;

    while (msec > 0)
    {
      Sleep(100);
      msec -= 100;

      if (history == 0)
          continue;

      // collect acceleration data
      Fin_Accel(&fd1,&fd2,&fd3,&d1,&d2);
      if (++snapofst >= ENTRIES)
          snapofst = 0;

      // save history of what was detected
      Fin_Speed(&snapshot[snapofst].left,&snapshot[snapofst].right);
      snapshot[snapofst].x = fd1;
      snapshot[snapofst].y = fd2;
      snapshot[snapofst].z = fd3;
      }
}


/*
 * display the history report
 */
void Report(void)
{
    int indx,max;
#ifdef DISKFILE
    FILE *disk;

    disk = fopen("Finch.Txt", "w+");
    if (disk == NULL)
        return;
#endif

    // display a report of what happened in the last ENTRIES x 1/10 seconds
    indx = snapofst;
    for (max=1; max<=ENTRIES; max++)
    {
        if (++indx >= ENTRIES)
            indx = 0;

#ifdef DISKFILE
        fprintf(disk,"Time:%3d.%d   Left:%4d  Right:%4d    %s\n",
            max/10, max%10, snapshot[indx].left, snapshot[indx].right,
            AccelText(snapshot[indx].x, snapshot[indx].y, snapshot[indx].z));
#else
        printf("Time:%3d.%d   Left:%4d  Right:%4d    %s\n",
            max/10, max%10, snapshot[indx].left, snapshot[indx].right,
            AccelText(snapshot[indx].x, snapshot[indx].y, snapshot[indx].z));
#endif
    }

#ifdef DISKFILE
    fclose(disk);
    system("type Finch.txt");
#endif

}


struct posfmt {
   float min_x;
   float max_x;
   float min_y;
   float max_y;
   float min_z;
   float max_z;
   char *desc;
};

/* determine if the x,y,z acceleration values are within range */
int  PosTest(struct posfmt *pf, float x, float y, float z)
{
    if (x <= pf->min_x)
        return(0);
    if (x >= pf->max_x)
        return(0);

    if (y <= pf->min_y)
        return(0);
    if (y >= pf->max_y)
        return(0);

    if (z <= pf->min_z)
        return(0);
    if (z >= pf->max_z)
        return(0);

    return(1);
}

char *PosText(float x, float y, float z)
{
    static struct posfmt PosTbl[9] = {
        { -1.5, -0.8,  -0.3, 0.3,  -0.3, 0.3, "Beak-Up   " },
        {  0.8,  1.5,  -0.3, 0.3,  -0.3, 0.3, "Beak-Dn   " },
        {  0.2,  0.5,  -0.3, 0.3,   0.9, 1.2, "Dn-Hill   " },
        { -0.5, -0.2,  -0.3, 0.3,   0.9, 1.2, "Up-Hill   " },
        { -0.5,  0.5,  -0.5, 0.5,  0.65, 1.2, "Level     " },
        { -0.5,  0.5,  -0.5, 0.5, -1.5, -0.65,"Dn/Up     " },
        { -0.5,  0.5,   0.7, 1.5,  -0.5, 0.5, "LftWing-Dn" },
        { -0.5,  0.5,  -1.5,-0.7,  -0.5, 0.5, "RgtWing-Dn" },
        {  0.0,  0.0,   0.0, 0.0,   0.0, 0.0, "          " }
    };
    char *textp;
    int ofst;

    /* convert an x,y,z acceleration to a description */
    textp = PosTbl[8].desc;
    for (ofst=0; ofst<8; ofst++)
    {
        if (PosTest(&PosTbl[ofst], x, y, z))
        {
            textp = PosTbl[ofst].desc;
            break;
        }
    }

    return(textp);
}

char *AccelText(float x, float y, float z)
{
    static char text[80];

    /* display the x,y,z acceleration values */
    sprintf(text,"X:%2.5f  Y:%2.5f  Z:%2.5f",x,y,z);
    return(text);
}
