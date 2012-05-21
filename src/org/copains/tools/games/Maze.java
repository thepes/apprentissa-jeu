package org.copains.tools.games;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.copains.tools.geometry.Coordinates;

public class Maze {
	
	private Coordinates startPoint;
	private Coordinates endPoint;
	private int sizeX;
	private int sizeY;
	private byte[][] maze;
	private byte[][] visited;
	
	public Maze(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public List<Coordinates> getNeighbors(Coordinates c) {
		ArrayList<Coordinates> neighbors = new ArrayList<Coordinates>();
		// DOWN 
		if (c.getY() + 2 < sizeY )
			if (visited[c.getX()][c.getY()+2] == 0)
				neighbors.add(new Coordinates(c.getX(), c.getY()+2));
		// UP
		if (c.getY() - 2 > 0 )
			if (visited[c.getX()][c.getY()-2] == 0)
				neighbors.add(new Coordinates(c.getX(), c.getY()-2));
		
		// LEFT
		if (c.getX() - 2 > 0 )
			if (visited[c.getX()-2][c.getY()] == 0)
				neighbors.add(new Coordinates(c.getX()-2, c.getY()));
		
		// RIGHT
		if (c.getX() + 2 < sizeX )
			if (visited[c.getX()+2][c.getY()] == 0)
				neighbors.add(new Coordinates(c.getX()+2, c.getY()));
		
		return neighbors;
	}
	
	public void processCell(Coordinates cell) {
		if (null == cell) {
			cell = endPoint;
		}
		visited[cell.getX()][cell.getY()] = 1;
		maze[cell.getX()][cell.getY()] = 0;
		List<Coordinates> neighbors = getNeighbors(cell);
		Collections.shuffle(neighbors);
		for (Coordinates neighbor : neighbors) {
			if (visited[neighbor.getX()][neighbor.getY()] == 1) {
				continue;
			}
			if (cell.getX() != neighbor.getX()) {
				if (neighbor.getX() > cell.getX())
					maze[cell.getX()+1][cell.getY()] = 0;
				else
					maze[cell.getX()-1][cell.getY()] = 0;
			}
			if (cell.getY() != neighbor.getY()) {
				if (neighbor.getY() > cell.getY())
					maze[cell.getX()][cell.getY()+1] = 0;
				else
					maze[cell.getX()][cell.getY()-1] = 0;
			}
			processCell(neighbor);
		}
	}

	
	public Coordinates getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Coordinates startPoint) {
		this.startPoint = startPoint;
	}

	public Coordinates getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Coordinates endPoint) {
		this.endPoint = endPoint;
	}

	public byte[][] getMaze() {
		return maze;
	}

	public void setMaze(byte[][] maze) {
		this.maze = maze;
	}

	public byte[][] getVisited() {
		return visited;
	}

	public void setVisited(byte[][] visited) {
		this.visited = visited;
	}

}
