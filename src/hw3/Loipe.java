package hw3;

import java.util.ArrayList;

/**
 * Class for deriving a 2D representration of a path (loipe) from a trace
 * in the form of a string in the format specified by assignment oop-hw3
 * and yielding entries by x,y values
 * @author Sal Wolffs(s4064542)
 * @author Lars Jellema(s4388747)
 */

public class Loipe implements InfoLoipe{
	/**
	 * Enum of the cardinal directions, stores various intrinsic properties
	 * of same such as relations to each other and the required offset
	 * to move in a direction in a matrix formatted as in the rest of the class.
	 * @author Sal Wolffs(s4064542)
	 * @author Lars Jellema(s4388747)
	 */
	public enum Direction {
		N(0,-1), E(1,0), S(0,1), W(-1,0);
		
		private final int dx;
		private final int dy;
		private Direction left, right,rev;
		
		static {
			N.left = W;
			E.left = N;
			S.left = E;
			W.left = S;
			N.right = E;
			E.right = S;
			S.right = W;
			W.right = N;
			N.rev = S;
			E.rev = W;
			S.rev = N;
			W.rev = E;
		}
		
		Direction(int dx,int dy){
			this.dx=dx;
			this.dy=dy;
		}
		
		/**
		 * 
		 * @param c character to be parsed.
		 * @return the Direction associated with this by c as defined by the
		 * assignment
		 */
		private Direction turnByChar(char c){
			switch(c){
			case 'l': return left;
			case 'r': return right; 
			case 's': return this;
			default: throw new IllegalArgumentException(
				"Unknown direction symbol \'" + c + "\' encountered.");
			}
		}
		
		/**
		 * 
		 * @return the Direction to the left (CCW) of this one.
		 */
		public Direction turnLeft(){
			return left;
		}
		
		/**
		 * 
		 * @return the Direction to the right(CW) of this one.
		 */
		public Direction turnRight(){
			return right;
		}
	}
	private Integer sizeX;
	private Integer sizeY;
	private Fragment[][] grid; 
	
	private Tuple<Integer,Integer> origin;//(x,y)
	private ArrayList<Tuple<Direction,Fragment>> track;
	
	/**
	 * Sets up a loipe corresponding to the given pad.
	 * @param pad A path formatted as defined in the assignment.
	 */
	public Loipe(String pad){
		track = toTrack(pad);
		normalizeCoords();
		generateGrid();
	}
	
	/**
	 * Sets up the loipe after pad has been translated to track and
	 * normalizeCoords() has run.
	 */
	private void generateGrid() {
		int x = origin.left;
		int y = origin.right;
		grid = new Fragment[sizeX][sizeY];
		for(Tuple<Direction, Fragment> tile : track){
			grid[x][y]=Fragment.combine(grid[x][y], tile.right);
			x += tile.left.dx;
			y += tile.left.dy;
		}
	}
	
	/**
	 * Calculates sizeX, sizeY and origin based on track.
	 */
	private void normalizeCoords() {
		int x,y,maxX,maxY,minX,minY;
		x=y=maxX=maxY=minX=minY=0;
		for(Tuple<Direction, Fragment> tile : track){
			maxX = Math.max(x,maxX);
			maxY = Math.max(y,maxY);
			minX = Math.min(x,minX);
			minY = Math.min(y,minY);
			x += tile.left.dx;
			y += tile.left.dy;
		}
		origin = new Tuple<Integer,Integer>(-minX,-minY);
		sizeX = maxX - minX + 1;
		sizeY = maxY - minY + 1;
	}
	
	/**
	 * Converts an assignment-compliant String into an internal format.
	 * @param pad An assignment-compliant pad-String.
	 * @return A list of (Direction,Fragment) tuples corresponding to the same
	 * loipe as pad.
	 */
	private ArrayList<Tuple<Direction, Fragment>> toTrack(String pad) {
		Direction heading = Direction.N;
		Direction newHeading;
		ArrayList<Tuple<Direction,Fragment>> accu = 
				new ArrayList<Tuple<Direction,Fragment>>();
		for (char c : pad.toCharArray()){
			newHeading = heading.turnByChar(c);
			accu.add(new Tuple<Direction,Fragment>(
					newHeading,Fragment.byDirections(heading.rev, newHeading)));
			heading=newHeading;
		}
		return accu;
	}


	@Override
	public int getX() {
		return sizeX;
		
	}
	@Override
	public int getY() {
		return sizeY;
		
	}
	
	@Override
	public Fragment getFragment(int x, int y) {
		if(x >= sizeX || y >= sizeY || x < 0 || y < 0){
			System.err.println(
				"Warning: accessed fragment out of bounds. Returning null.");
			return null;
		}
		return grid[x][y];
	}
}
