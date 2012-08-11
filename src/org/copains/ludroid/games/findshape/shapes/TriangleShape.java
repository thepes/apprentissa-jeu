package org.copains.ludroid.games.findshape.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

public class TriangleShape extends Shape {

	public TriangleShape() {
		super();
		// TODO: randomize type of triangle (rectangle, isocele ...)
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		// for now drawing always the same triangle
		float sizeX = getWidth();
		float sizeY = getHeight();
		float lines[] = { sizeX/2, 0, 0, sizeY-1,
				0, sizeY-1, sizeX-1, sizeY-1,
				sizeX-1, sizeY-1, sizeX/2, 0};
		canvas.drawLines(lines, paint);
	}

}
