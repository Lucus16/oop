package hw3;

import java.util.ArrayList;

public class Loipe implements InfoLoipe{
	public enum Direction {
		N(0,-1), E(1,0), S(0,1), W(-1,0);
		
		private final int dx;
		private final int dy;
		private Direction left, right;
		
		static {
			N.left = W;
			E.left = N;
			S.left = E;
			W.left = S;
			N.right = E;
			E.right = S;
			S.right = W;
			W.right = N;
		}
		
		Direction(int dx,int dy){
			this.dx=dx;
			this.dy=dy;
		}
		
		private Direction turnByChar(char c){
			switch(c){
			case 'l': return left;
			case 'r': return right; 
			case 's': return this;
			default: throw new IllegalArgumentException(
				"Unknown direction symbol \'" + c + "\' encountered.");
			}
		}
		
		public Direction turnLeft(){
			return left;
		}
		
		public Direction turnRight(){
			return right;
		}
	}
	private Integer sizeX;
	private Integer sizeY;
	private Fragment[][] grid; 
	
	private Tuple<Integer,Integer> origin;//(x,y)
	private ArrayList<Tuple<Direction,Fragment>> track;
	
	public Loipe(String pad){
		track = toTrack(pad);
		normalizeCoords();
		generateGrid();
	}
	
	
	private void generateGrid() {
		int x = origin.left;
		int y = origin.right;
		grid = new Fragment[sizeX][sizeY];
		for(Tuple<Direction, Fragment> tile : track){
			grid[x][y]=Fragment.add(grid[x][y], tile.right);
			x += tile.left.dx;
			y += tile.left.dy;
		}
	}


	private void normalizeCoords() {
		// TODO Auto-generated method stub
		
	}


	private ArrayList<Tuple<Direction, Fragment>> toTrack(String pad) {
		Direction heading = Direction.N;
		int x,y,maxX,maxY,minX,minY;
		x=y=maxX=maxY=minX=minY=0;
		return null;
		//TODO: continue.
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
