package com.example.androidtreeviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button dialogBtn;
	TreeViewDialog dialog;

	// 构建手势探测器
	private GestureDetector dialogGesture;
	private static final int FLING_MIN_DISTANCE = 90;//移动最小距离  
    private static final int FLING_MIN_VELOCITY = 150;//移动最大速度  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dialogBtn = (Button) findViewById(R.id.dialogBtn);
		dialog = new TreeViewDialog(MainActivity.this, R.style.TreeViewDialog);
		
		Window win = dialog.getWindow();
		LayoutParams params = new LayoutParams();
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		params.x = width;// 设置x坐标
		params.y = 0;// 设置y坐标
		win.setAttributes(params);
		win.setWindowAnimations(R.style.dialog_animation_style);
		
		dialogGesture = new GestureDetector(this, new ViewGestureListener());

		dialogBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog.show();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public boolean onTouchEvent(MotionEvent event) {
		return dialogGesture.onTouchEvent(event);
	}

	class ViewGestureListener implements OnGestureListener {
		// 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
		public boolean onDown(MotionEvent e) {
			return false;
		}

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// e1：第1个ACTION_DOWN MotionEvent   
	        // e2：最后一个ACTION_MOVE MotionEvent   
	        // velocityX：X轴上的移动速度（像素/秒）   
	        // velocityY：Y轴上的移动速度（像素/秒）   
	          
	        // X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒   
	        //向有翻图片  
	        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {      
	        	// FIXME:dialog is null ?
	        	dialog.show(); 
	        }   
	        //向左翻图片  
	        if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {      
	        	//FIXME:dialog is null ?
	        	dialog.dismiss();
	        }  
			return false;
		}

		// 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
		public void onLongPress(MotionEvent e) {
		}

		// 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			return false;
		}

		// 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
		// 注意和onDown()的区别，强调的是没有松开或者拖动的状态
		public void onShowPress(MotionEvent e) {
		}

		// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
	}

}
