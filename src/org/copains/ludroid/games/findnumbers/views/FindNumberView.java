package org.copains.ludroid.games.findnumbers.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class FindNumberView extends View {

	public FindNumberView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public FindNumberView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FindNumberView(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		int sizeX, sizeY;
		
		sizeX = canvas.getWidth();
		sizeY = canvas.getHeight();
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setARGB(255, 255, 0, 0);
		paint.setTextSize(160);
		canvas.drawText("2", 50, 200, paint);
	}

}
