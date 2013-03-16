package jp.co.myexample.android.ui;

public final class NotificationIdGenerator {
	
	private static int MAX_ID = 1000;
	private static int mCurrentId = 0; 
	
	public static int generate(){
		if(mCurrentId >= MAX_ID){
			mCurrentId = 0;
		}
		return mCurrentId++;
	}

}
