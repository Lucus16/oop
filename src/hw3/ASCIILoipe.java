/**
 * 
 */
package hw3;

import range.Range;

/**
 * @author Sal
 *
 */
public class ASCIILoipe implements TekenLoipe {
	InfoLoipe loipe;
	
	ASCIILoipe(){
		
	}
	
	ASCIILoipe(InfoLoipe loipe){
		this.loipe=loipe;
	}
	
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
