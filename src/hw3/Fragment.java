package hw3;

public enum Fragment {
	NZ, OW, NO, NW, ZO, ZW, KR;
	
	//static for lack of a "return this;" statement.
	public static Fragment add(Fragment left,Fragment right){
		if(left==null) return right;
		if(right==null) return left;
		if((left==NZ && right==OW) || (left==OW && right==NZ)) return KR;
		//Fell through all legal patterns.
		throw new IllegalArgumentException(
				"Fatal: Attempt to overwrite existing path.");
	}
	
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
