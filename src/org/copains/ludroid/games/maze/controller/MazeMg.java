package org.copains.ludroid.games.maze.controller;

import org.copains.tools.games.Maze;
import org.copains.tools.games.MazeGenerator;
import org.copains.tools.geometry.Coordinates;

public class MazeMg {
	
	private static MazeMg instance = null;
	
	private Maze maze;
	private int sizeX;
	private int sizeY;
	private boolean gameStarted;
	private boolean gameWon = false;
	private Coordinates currentPlayerPosition;
	private Coordinates previousPlayerPosition;
	
	private MazeMg() {
		gameStarted = false;
	}
	
	public void initGame(int sizeX, int sizeY) {
		maze = MazeGenerator.generateMaze(sizeX, sizeY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		maze.reinitVisited();
		currentPlayerPosition = maze.getStartPoint();
		previousPlayerPosition = currentPlayerPosition;
		maze.setVisitedCell(currentPlayerPosition);
		gameStarted = true;
	}
	
	public boolean isWall(Coordinates c) {
		if ((c.getX() >= sizeX) || (c.getY() >= sizeY))
			return true;
		if ((c.getX() < 0) || (c.getY() <0))
			return true;
		return (maze.getMaze()[c.getX()][c.getY()] == 1);
	}

	public void reinitPreviousPosition() {
		previousPlayerPosition = currentPlayerPosition;
	}

	public void handleMove(Coordinates destCell) {
		maze.setVisitedCell(destCell);
		currentPlayerPosition = destCell;
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

	public Coordinates getPreviousPlayerPosition() {
		return previousPlayerPosition;
	}

	public void setPreviousPlayerPosition(Coordinates previousPlayerPosition) {
		this.previousPlayerPosition = previousPlayerPosition;
	}

	public Coordinates getCurrentPlayerPosition() {
		return currentPlayerPosition;
	}

	public void setCurrentPlayerPosition(Coordinates currentPlayerPosition) {
		this.currentPlayerPosition = currentPlayerPosition;
	}

	public void setGameWon(boolean b) {
		gameWon = b;
		if (gameWon) {
			gameStarted = false;
		}
	}

	public boolean isGameWon() {
		return gameWon;
	}

}
