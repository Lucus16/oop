package hw3;

import java.util.ArrayList;

public class Loipe implements InfoLoipe{
	public enum Direction {
		N(0,-1), E(1,0), S(0,1), W(-1,0);
		
		private final int dx;
		private final int dy;
		
		Direction(int dx,int dy){
			this.dx=dx;
			this.dy=dy;
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
		
	}


	private void normalizeCoords() {
		// TODO Auto-generated method stub
		
	}


	private ArrayList<Tuple<Direction, Fragment>> toTrack(String pad) {
		// TODO Auto-generated method stub
		return null;
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
