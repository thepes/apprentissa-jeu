package org.copains.tools.geometry;

public class CoordinatesConverter {
	
	/**
	 * convert on screen coordinates to grid coordinates
	 * @param c the on screen coordinates 
	 * @param stepX the width of a cell
	 * @param stepY the height of a cell
	 * @return the grid coordinate
	 */
	public static final Coordinates convert(Coordinates c, int stepX, int stepY) {
		Coordinates ret = new Coordinates(c.getX()/stepX, c.getY()/stepY);
		return ret;
	}

}
