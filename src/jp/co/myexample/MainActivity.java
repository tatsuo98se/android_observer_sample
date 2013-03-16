package jp.co.myexample;

import jp.co.myexample.android.ui.ProgressNotificator;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button mButton;
	
	
	private void setUp(){
		mButton = (Button)findViewById(R.id.button1);
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new Thread(){
					@Override
					public void run() {
						TestProgress progress = new TestProgress();
						new ProgressNotificator(MainActivity.this, progress);
						progress.run(null);
					}
				}.start();
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUp();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
