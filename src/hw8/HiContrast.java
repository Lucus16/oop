/**
 * 
 */
package hw8;

import java.awt.Color;

/**
 * @author sal
 *
 */
public class HiContrast implements Painter {
	
	private static int full=0xff,zero=0x00;
	
	public int[] getColor(int n) {
		int prim,sec,tert,red,green,blue;
		prim = full;
		sec  = (n * 35) % 0x100;
		tert = zero;
		if(n%2 == 1){
			prim = full - prim;
			sec  = full - sec;
			tert = full - tert;
		}
		switch (n%3){
		case 0: red = prim; green = sec; blue = tert; break;
		case 1: green = prim; blue = sec; red = tert; break;
		case 2: blue = prim; red = sec; green = tert; break;
		default: red = prim; green = sec; blue = tert; 
		}
		return new int[] {red,green,blue};
	}

}
