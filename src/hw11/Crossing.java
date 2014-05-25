
package hw11;

/**
 * @author Lars Jellema (s4388747)
 * @author Sal Wolffs (s4064542)
 * 
 * English version of the Dutch "Regelaar"
 */
class Crossing {
    private static final int left = (RoadView.WINDOWSIZE / 2) - Car.CARWIDTH - 4;
    private static final int width = 2 * Car.CARWIDTH + 8;
    private Model model;
    
    public Crossing(Model m){
    	model = m;
    	
    }
}
