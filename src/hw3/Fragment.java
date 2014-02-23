package hw3;

import java.lang.IllegalArgumentException;

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
}
