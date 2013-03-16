package jp.co.myexample.android.ui;

import jp.co.myexample.util.ProgressObservable;
import jp.co.myexample.util.ProgressObserver;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Context;

public class ProgressNotificator extends ProgressObserver {
	
	private NotificationManager mNotificationManager;
	private Builder mBuilder;
	private ProgressObservable mObservable;
	private int mNotificationId = NotificationIdGenerator.generate();

	public ProgressNotificator(Context context, ProgressObservable observable){
		mNotificationManager
			= (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		mObservable = observable;
		mBuilder = new Builder(context);
		mBuilder.setContentTitle("test").setOngoing(true).setSmallIcon(android.R.drawable.ic_delete);
		mObservable.addObserver(this);
	}

	@Override
	protected void onPreExecute(Object data) {
		mBuilder.setProgress(0, 0, true)
		.setContentText("onPreExecute");
		mNotificationManager.notify(mNotificationId, mBuilder.build());
	}

	@Override
	protected void onPostExecute(Object data) {
		mBuilder.setProgress(0, 0, false)
		.setContentText("onPostExecute");
		mNotificationManager.notify(mNotificationId, mBuilder.build());
	}

	@Override
	protected void onProgress(int min, int max, int currentProgress, Object data) {
		mBuilder.setProgress(max - min, currentProgress, false)
		.setContentText("onProgress");
		mNotificationManager.notify(mNotificationId, mBuilder.build());
	}

	@Override
	protected void onError(Throwable e, Object data) {
		mBuilder.setProgress(0, 0, false)
		.setContentText("onError");
		mNotificationManager.notify(mNotificationId, mBuilder.build());
	}

	@Override
	protected void onFinally(Object data) {
		mBuilder.setOngoing(false);
		mNotificationManager.notify(mNotificationId, mBuilder.build());
		mObservable.deleteObserver(this);
	}
	
}
