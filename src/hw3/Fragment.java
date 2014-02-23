package hw3;


/**
 * Fragment enumeration with some statics to easily determine the Fragment
 * matching given data.
 * @author Sal Wolffs(s4064542)
 * @author Lars Jellema(s4388747)
 */

public enum Fragment {
	NZ, OW, NO, NW, ZO, ZW, KR;
	
	/**
	 * Combines two fragments. Error unless one is null or they combine
	 * neatly into a crossing.
	 * @param left
	 * @param right
	 * @return left and right combined
	 */
	public static Fragment combine(Fragment left,Fragment right){
		if(left==null) return right;
		if(right==null) return left;
		if((left==NZ && right==OW) || (left==OW && right==NZ)) return KR;
		//Fell through all legal patterns.
		throw new IllegalArgumentException(
				"Fatal: Attempt to overwrite existing path.");
	}
	
	/**
	 * Function for determining a fitting fragment to travel between two given
	 * sides in a "tile".
	 * @param from The direction one enters the fragment from. Note: this is
	 * opposite the direction one is heading when one enters the fragment.
	 * @param to The direction one leaves the fragment at. This equals
	 * the direction one is heading afterwards.
	 * @return The fragment connecting the two given sides.
	 */
	public static Fragment byDirections(Loipe.Direction from,Loipe.Direction to){
		switch(from){
		case N: {
			switch(to){
			case N: throw new IllegalArgumentException("Fragment from==to.");
			case E: return NO;
			case S: return NZ;
			case W: return NW;
			}
		}
		case E: {
			switch(to){
			case N: return NO;
			case E: throw new IllegalArgumentException("Fragment from==to.");
			case S: return ZO;
			case W: return OW;
			}
		}
		case S: {
			switch(to){
			case N: return NZ;
			case E: return ZO;
			case S: throw new IllegalArgumentException("Fragment from==to.");
			case W: return ZW;
			}
		}
		case W: {
			switch(to){
			case N: return NW;
			case E: return OW;
			case S: return ZW;
			case W: throw new IllegalArgumentException("Fragment from==to.");
			}
		}
		default: throw new NullPointerException(
				"Nonexistent direction passed as arg.");
		}
	}
}
