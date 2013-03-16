package jp.co.myexample.util;

public class Event {
	
	public enum EventId{
		PreExecute,
		PostExecute,
		Progress,
		Error,
		Finally,
	}

	private Object mData;
	private EventId mEventId;
	
	public Event(EventId eventId, Object additinalData){
		mEventId = eventId;
		mData = additinalData;
	}
	
	public EventId getEventId(){
		return mEventId;
	}
	public Object getData(){
		return mData;
	}
	
	public static class ProgressEvent extends Event{
		private int mMin;
		private int mMax;
		private int mCurrentProgress;
		public ProgressEvent(int min, int max, int currentProgress, Object addtionalData){
			super(EventId.Progress, addtionalData);
			mMin = min;
			mMax = max;
			mCurrentProgress = currentProgress;
		}
		
		public int getMin(){
			return mMin;
		}
		public int getMax(){
			return mMax;
		}
		public int getCurrentProgress(){
			return mCurrentProgress;
		}
	}
	
	public static class ErrorEvent extends Event{
		private Throwable mError;
		public ErrorEvent(Throwable e, Object additionalData){
			super(EventId.Error, additionalData);
			mError = e;
		}
		public Throwable getError(){
			return mError;
		}
	}
	
}
