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

	// ��������̽����
	private GestureDetector dialogGesture;
	private static final int FLING_MIN_DISTANCE = 90;//�ƶ���С����  
    private static final int FLING_MIN_VELOCITY = 150;//�ƶ�����ٶ�  

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
		params.x = width;// ����x����
		params.y = 0;// ����y����
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
		// �û��ᴥ����������1��MotionEvent ACTION_DOWN����
		public boolean onDown(MotionEvent e) {
			return false;
		}

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// e1����1��ACTION_DOWN MotionEvent   
	        // e2�����һ��ACTION_MOVE MotionEvent   
	        // velocityX��X���ϵ��ƶ��ٶȣ�����/�룩   
	        // velocityY��Y���ϵ��ƶ��ٶȣ�����/�룩   
	          
	        // X�������λ�ƴ���FLING_MIN_DISTANCE�����ƶ��ٶȴ���FLING_MIN_VELOCITY������/��   
	        //���з�ͼƬ  
	        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {      
	        	// FIXME:dialog is null ?
	        	dialog.show(); 
	        }   
	        //����ͼƬ  
	        if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {      
	        	//FIXME:dialog is null ?
	        	dialog.dismiss();
	        }  
			return false;
		}

		// �û��������������ɶ��MotionEvent ACTION_DOWN����
		public void onLongPress(MotionEvent e) {
		}

		// �û����´����������϶�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE����
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			return false;
		}

		// �û��ᴥ����������δ�ɿ����϶�����һ��1��MotionEvent ACTION_DOWN����
		// ע���onDown()������ǿ������û���ɿ������϶���״̬
		public void onShowPress(MotionEvent e) {
		}

		// �û����ᴥ���������ɿ�����һ��1��MotionEvent ACTION_UP����
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
	}

}
