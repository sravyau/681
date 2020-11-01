package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConSingleton {
	private ConSingleton() {
	};

	private static ConSingleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();

	public static ConSingleton getInstance() {
		lock.lock();
		try {
			if (instance == null)
				instance = new ConSingleton();
			return instance;
		} finally {
			lock.unlock();
		}
	}
}