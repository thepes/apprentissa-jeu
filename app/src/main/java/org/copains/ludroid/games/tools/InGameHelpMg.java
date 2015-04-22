package org.copains.ludroid.games.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;

public class InGameHelpMg {
	
	private static InGameHelpMg instance = null;
	
	private Rect reservedRectForHelp;
	
	private InGameHelpMg() {
		
	}
	
	public static InGameHelpMg getInstance() {
		if (null == instance) {
			instance = new InGameHelpMg();
		}
		return instance;
	}
	
	public void drawHelpButton(Canvas canvas, Paint paint) {
		int sizeX = canvas.getWidth();
		int sizeY = canvas.getHeight();
		int textSize = Math.min(sizeX, sizeY) / 6;
		reservedRectForHelp = new Rect(1, 1, textSize, textSize);
		paint.setTextSize(textSize);
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		RectF rf = new RectF(reservedRectForHelp);
		canvas.drawRoundRect(rf, 10, 10, paint);
		paint.setTextAlign(Align.CENTER);
		FontMetricsInt fmi = paint.getFontMetricsInt();
		canvas.drawText("?", reservedRectForHelp.right / 2,reservedRectForHelp.bottom-(fmi.bottom/2), paint);
	}

	/**
	 * checks if the help "button" is clicked.
	 * @param x the X coordinates
	 * @param y the Y coordiantes
	 * @return true if the button is clicked
	 */
	public boolean isHelpClicked(int x, int y) {
		return (reservedRectForHelp.contains(x, y));
	}

	/**
	 * @return the reservedRectForHelp
	 */
	public Rect getReservedRectForHelp() {
		return reservedRectForHelp;
	}

	/**
	 * @param reservedRectForHelp the reservedRectForHelp to set
	 */
	public void setReservedRectForHelp(Rect reservedRectForHelp) {
		this.reservedRectForHelp = reservedRectForHelp;
	}

}
