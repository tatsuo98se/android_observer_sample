package jp.co.myexample;

import jp.co.myexample.util.ProgressObservable;

public class TestProgress extends ProgressObservable {

	@Override
	public void execute() throws Exception {
		try{
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
