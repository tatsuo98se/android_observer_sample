package jp.co.myexample.util;

import java.util.Observable;
import java.util.Observer;

import jp.co.myexample.util.Event.ErrorEvent;
import jp.co.myexample.util.Event.ProgressEvent;

public abstract class ProgressObserver implements Observer {

	final public void update(Observable observable, Object data) {
		try{
			Event event = (Event)data;
			switch(event.getEventId()){
			case PreExecute:
				onPreExecute(data);
				break;
			case Progress:
			{
				ProgressEvent progressEvent = (ProgressEvent)event;
				onProgress(progressEvent.getMin(),
						progressEvent.getMax(),
						progressEvent.getCurrentProgress(),
						data);
				break;
			}
			case PostExecute:
				onPostExecute(data);
				break;
			case Error:
			{
				ErrorEvent errorEvent = (ErrorEvent)event;
				onError(errorEvent.getError(), data);
				break;
			}
			case Finally:
				onFinally(data);
				break;
			default:
				throw new IllegalArgumentException("Unexpected eventId has been specified.");
			}
		}
		catch(ClassCastException e){
			throw new IllegalArgumentException("Unexpected object has been spacified.", e);
		}
	}
	
	protected abstract void onPreExecute(Object data);
	protected abstract void onPostExecute(Object data);
	protected abstract void onProgress(int min, int max, int currentProgress, Object data);
	protected abstract void onError(Throwable e, Object data);
	protected abstract void onFinally(Object data);
}
