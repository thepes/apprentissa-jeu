package org.copains.tools.games;

import org.copains.tools.geometry.Coordinates;

import android.util.Log;

public class MazeGenerator {
	
	/**
	 * generates randomly a maze. 
	 * currently the generation algorithm is 
	 * @param sizeX the size X (including walls)
	 * @param sizeY the size Y (including walls)
	 * @return the generated maze
	 */
	public static Maze generateMaze(int sizeX, int sizeY) {
		Maze newMaze = new Maze(sizeX, sizeY);
		byte[][] visited = new byte[sizeX][sizeY];
		byte[][] maze = new byte[sizeX][sizeY];
		for (int i = 0 ; i < sizeX ; i++)
			for (int j = 0 ; j < sizeY ; j++) {
				visited[i][j] = 0;
				maze[i][j] = 1;
			}
		newMaze.setVisited(visited);
		newMaze.setMaze(maze);
		newMaze.setEndPoint(new Coordinates(sizeX-2, sizeY-2));
		newMaze.processCell(null);
		newMaze.setVisited(null);
		
		return (newMaze);
	} 

}
