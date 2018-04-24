package es.pue.finch.weather;

import edu.cmu.ri.createlab.rss.readers.WeatherReader;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;

class Demo {
    
    public static void main(String[] args) {
        
        //https://www.wunderground.com/
        Finch myFinch = new Finch();
        
        WeatherReader rss = new WeatherReader("New York, NY");
        
        while (!myFinch.isFinchUpsideDown()) {
            double temperature = (rss.getTemperature() - 32) / 1.8;

            if (temperature < 15) {
                myFinch.setLED(Color.RED);
            }
            else {
                myFinch.setLED(Color.BLUE);
            }
            myFinch.sleep(30000);
            rss.updateWeatherFeed();
        }
        myFinch.quit();
    }
}
