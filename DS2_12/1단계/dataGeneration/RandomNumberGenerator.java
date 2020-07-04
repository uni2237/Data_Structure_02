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
	// �־��� aMaxSize ��ŭ�� ������ ���� ���� �غ� �Ѵ�.
	public void prepare(int aMaxSize){
		this.setMaxSize(aMaxSize);
	}
	
	// 0 ���� (aMaxSize -1) ������ ������ ���� �ϳ� ��´�.
	public int randomNumber()
	{
		int randomNumber = this.randomGenerator().nextInt(this.maxSize());
		return randomNumber;
	}
	
	
}
