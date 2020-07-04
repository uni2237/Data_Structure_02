package dataGeneration;

import java.util.Random;

public class RandomNumberGenerator {

	public static final int DEFAULT_MAX_DATA_GENERATOR_SIZE = 1000;

	Random _randomGenerator;
	private int _maxSize;

	private int maxSize() {
		return this._maxSize;
	}

	private void setMaxSize(int aMaxSize) {
		this._maxSize = aMaxSize;
	}

	private Random randomGenerator() {
		return this._randomGenerator;
	}

	private void SetRandomGenerator(Random randomGen) {
		this._randomGenerator = randomGen;
	}

	public RandomNumberGenerator() {
		this.SetRandomGenerator(new Random());
	}

	public void prepare(int aMaxSize) {
		this.setMaxSize(aMaxSize);
	}

	public int randomNumber() {
		int randomNumber = this.randomGenerator().nextInt(this.maxSize());
		return randomNumber;
	}

}
