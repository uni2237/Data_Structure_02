package dataGeneration;

import java.util.Random;

public class RandomNumberGenerator {
	
	public static final int DEFAULT_MAX_DATA_GENERATOR_SIZE = 1000;
	
	Random _randomGenerator;
	private int _maxSize;
	
	// Getters & Setters
	private int maxSize(){
		return this._maxSize;
	}
	
	private void setMaxSize(int aMaxSize){
		this._maxSize = aMaxSize;
	}
	
	private Random randomGenerator(){
		return this._randomGenerator;
	}
	
	private void SetRandomGenerator(Random randomGen){
		this._randomGenerator = randomGen;
	}
	
	// Constructor
	public RandomNumberGenerator(){
		this.SetRandomGenerator(new Random());
	}
	
	// Public Methods
	// 주어진 aMaxSize 만큼의 무작위 수를 얻을 준비를 한다.
	public void prepare(int aMaxSize){
		this.setMaxSize(aMaxSize);
	}
	
	// 0 부터 (aMaxSize -1) 까지의 무작위 수를 하나 얻는다.
	public int randomNumber()
	{
		int randomNumber = this.randomGenerator().nextInt(this.maxSize());
		return randomNumber;
	}
	
	
}
