package com.javacodegeeks.androidcanvasexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AndroidCanvasExample extends Activity {

	private CanvasView customCanvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
	}

	public void clearCanvas(View v) {
		customCanvas.clearCanvas();
	}

}