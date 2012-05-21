package org.copains.ludroid.games.maze.controller;

import org.copains.tools.games.Maze;
import org.copains.tools.games.MazeGenerator;

public class MazeMg {
	
	private static MazeMg instance = null;
	
	private Maze maze;
	private int sizeX;
	private int sizeY;
	private boolean gameStarted;
	
	private MazeMg() {
		gameStarted = false;
	}
	
	public void initGame(int sizeX, int sizeY) {
		maze = MazeGenerator.generateMaze(sizeX, sizeY);
		gameStarted = true;
	}
	
	public static MazeMg getInstance() {
		if (null == instance) {
			instance = new MazeMg();
		}
		return (instance);
	}

	/**
	 * @return the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * @param maze the maze to set
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	/**
	 * @return the sizeX
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * @param sizeX the sizeX to set
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * @return the sizeY
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * @param sizeY the sizeY to set
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * @return the gameStarted
	 */
	public boolean isGameStarted() {
		return gameStarted;
	}

	/**
	 * @param gameStarted the gameStarted to set
	 */
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

}
