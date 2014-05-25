package hw11;

/**
 * OO1route66 initial class
 * @author Pieter Koopman
 * Route66 class constructs model, view and controller
 */
public class Route66
{
    Controller controller;
    /**
     * the main method for OO13route66
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Route66 r66 = new Route66();
    }

    /**
     * the main constructor:
     * - creates model, controller and views
     */
    public Route66() {
    	//TODO: voor iedere Car car, maak een Driver(car,controller,model) aan.
    	//TODO: Crossing moet een container voor Cars hebben.
    	//TODO: check dat iedere wait() en notifyAll() vanuit een sync-block wordt geroepen.
        Model model = new Model();
        
        RoadView rview  = new RoadView(model);
        TableView tview = new TableView(model);
        model.addView(tview);
        model.addView(rview);
        
        controller = new Controller(model);
        
        KeyHandler keyHandler = new KeyHandler(controller);
        rview.addKeyListener(keyHandler);
        tview.addKeyListener(keyHandler);
        
        tview.setVisible(true);
        rview.setVisible(true);
        
        controller.run();
    }
}
