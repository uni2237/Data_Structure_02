package externalSort;

import fileHandling.Run;
import list.CircularQueue;
import list.Queue;

public class RunManager {
	
	private Queue<Run> _queue;
	private Queue<Run> queue(){
		return this._queue;
	}
	public void setQueue(Queue<Run> q){
		this._queue = q;
	}
	
	public RunManager(){
		this._queue=new CircularQueue();
	}
	
	public void setRunInformation(String aDirectoryPath, String aFilePrefix){
		Run.setDirectoryPath(aDirectoryPath);
		Run.setFilePrefix(aFilePrefix);
	}
	
	public void insertRunList(Integer[] aElementListForRun){
		Run eachRun = new Run();
		eachRun.makeRun(aElementListForRun);
		this.queue().add(eachRun);
	}
	
	public void insertRunList(Run aRun){
		if (aRun == null){
			return ;
		}
		this.queue().add(aRun);
	}
	
	public Run getRun(){
		return this.queue().remove();
	}
	
	public int runListLength(){
		return this.queue().size();
	}

}
