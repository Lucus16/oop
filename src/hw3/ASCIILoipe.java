/**
 * 
 */
package hw3;

import utils.Range;

/**
 * Class for turning a given InfoLoipe into ASCII art, printing to stdout.
 * @author Sal Wolffs(s4064542)
 * @author Lars Jellema(s4388747)
 */
public class ASCIILoipe implements TekenLoipe {
	InfoLoipe loipe;
	
	/**
	 * Empty constructor. Requires a call to setLoipe(Loipe loipe) afterward
	 * before any other calls are made to avoid null pointer exceptions.
	 */
	ASCIILoipe(){
	}
	
	/**
	 * Effectively performs setLoipe during construction.
	 * @param loipe
	 */
	ASCIILoipe(InfoLoipe loipe){
		this.loipe=loipe;
	}
	
	/**
	 * Binds this class to the given InfoLoipe.
	 */
	public void setLoipe(InfoLoipe loipe){
		this.loipe=loipe;
	}
	
	@Override
	public void teken() {
		
		for(int y : new Range(0,loipe.getY())){
			for(int x : new Range(0,loipe.getX())){
				System.out.print(toASCII(loipe.getFragment(x, y)));
			}
			System.out.println();
		}
	}
	
	/**
	 * Alternative drawing method, produces a less narrow ASCII art pattern.
	 */
	public void tekenWide() {
		for(int y : new Range(0,loipe.getY())){
			for(int x : new Range(0,loipe.getX())){
				System.out.print(wideASCII(loipe.getFragment(x, y)));
			}
			System.out.println();
		}
	}
	
	/**
	 * ASCII representation lookup for Fragments
	 * @param fragment
	 * @return ASCII repr. for fragment.
	 */
	private char toASCII(Fragment fragment) {
		if(fragment==null) return ' ';
		switch(fragment){
		case KR: return '+';
		case NO: return '\\';
		case NW: return '/';
		case NZ: return '|';
		case OW: return '-';
		case ZO: return '/';
		case ZW: return '\\';
		default: return ' ';
		}
	}
	
	/**
	 * alternative ASCII Fragment conversion table.
	 * @param fragment
	 * @return a wide ASCII representation of fragment.
	 */
	private String wideASCII(Fragment fragment) {
		if(fragment==null) return "   ";
		switch(fragment){
		case KR: return "-+-";
		case NO: return "  \\";
		case NW: return "/  ";
		case NZ: return " | ";
		case OW: return "---";
		case ZO: return "  /";
		case ZW: return "\\  ";
		default: return "   ";
		}
	}

}
