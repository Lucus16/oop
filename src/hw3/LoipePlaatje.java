package hw3;

import hw3.Loipe.Fragment;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Pieter Koopman
 */
public class LoipePlaatje extends Canvas implements TekenLoipe
{
   /** Used for painting the tiles.
    *  We assume that all tiles have same width and height.
    */
   private final int UNIT;

   /** Color for painting the background. */
   private static final Color SNOW = new Color(0xF0,0xF0,0xFF);
   
   /**
    * The icons for drawing
    */
   private final ImageIcon nz, ow, no, nw, zo, zw, nzow;

   /**
    * the loipe to be painted
    */
   private InfoLoipe loipe;

   private boolean loipeOK;

   /**
    * Constructor. Fill all icons.
    */
    public LoipePlaatje() {
        nz = plaatje("nz");
        ow = plaatje("ow");
        no = plaatje("no");
        nw = plaatje("nw");
        zo = plaatje("zo");
        zw = plaatje("zw");
        nzow = plaatje("nzow");
        UNIT = nz.getIconWidth();
    }

   /**
    * Creates a LoipePlaatje object based on the track information in
 <code>Loipe</code>.
    * @param s this Loipe contains the 2D track information
    */
   public void setLoipe (InfoLoipe s) {
      loipe = s;
      loipeOK = true;
      checkLoipe();
      setBackground(SNOW);
   }

   /**
    * Checks validity of the input track description. Terminates normally
    * when the track is valid, prints a message otherwise.
    */
   private void checkLoipe() {
      if (loipe == null)
      {
         System.err.println("Illegale loipe: null pointer");
         loipeOK = false;
      }
      else if (loipe.getY() == 0)
      {
         System.err.println("Illegale loipe: height should be at least 1");
         loipeOK = false;
      }
      else if (loipe.getX() == 0)
      {
         System.err.println("Illegale loipe: width should be at least 1");
         loipeOK = false;
      }
  }
   /**
    * Gets the preferred size of this component.
    *
    * @return a dimension object indicating this component's preferred size.
    */
   @Override
    public Dimension getPreferredSize() {
        return new Dimension(loipe.getX() * UNIT, loipe.getY() * UNIT);
    }

    /**
     * Updates this component.
     * @param g the graphics context to use for painting.
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    /**
     * try to read a picture with the given name
     * @param naam "name.png" is filename of picture
     * @return the picture
     * @throws IllegalArgumentexception if file is not found
     */
    private ImageIcon plaatje (String name) {
        name += ".png";
        URL resource = getClass().getClassLoader().getResource(name);
        if (resource != null) {
            return new ImageIcon (resource);
        } else {
            System.err.printf ("ERROR: Resource \"%s\" not found... aborting...\n", name);
            System.err.println("For NetBeans the file should be placed in the src folder.");
            throw new IllegalArgumentException ("Image " + name + " not found!");
        }
    }

   /**
    * Update graphics according to stored track.
    * @param g the graphics context to use for painting.
    */
   @Override
   public void paint(Graphics g) {
      for (int i = 0; i < loipe.getX(); i++) {
         int x = i * UNIT;
         for (int j = 0; j < loipe.getY(); j++) {
            int y = j * UNIT;            
            Fragment rail = loipe.getFragment(i, j);
            ImageIcon plaatje;
            if (rail != null) {
                switch (rail) {
                    case NZ: plaatje = nz; break;
                    case OW: plaatje = ow; break;
                    case NO: plaatje = no; break;
                    case NW: plaatje = nw; break;
                    case ZO: plaatje = zo; break;
                    case ZW: plaatje = zw; break;
                    default: plaatje = nzow;
                }
                if (plaatje != null) {
                    plaatje.paintIcon(this, g, x, y);
                }
            }
         }
      }
   }
   /**
    * The method to call in order to create a window and draw a picture of
    * the given track.
    * You are supposed to use this method once for each object.
    */
   @Override
   public void teken() {
       if (loipeOK) {
            JFrame frame = new JFrame("Kaart");
            frame.getContentPane().add(this);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();               // set frame size to fit content
            frame.setVisible(true);     // make frame visible
       }
       else
           System.err.println("Kan helaas niet tekenen. Loipe is ongeldig of plaatjes niet gevonden");
   }
}