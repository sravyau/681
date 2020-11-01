package edu.umb.cs681.hw08;

public class RunnableConSingleton implements Runnable {

	public void run() {
		System.out.println(ConSingleton.getInstance());
	}

	public static void main(String[] args) {
		new Thread(new RunnableConSingleton()).start();
		new Thread(new RunnableConSingleton()).start();
		new Thread(new RunnableConSingleton()).start();
		new Thread(new RunnableConSingleton()).start();
		new Thread(new RunnableConSingleton()).start();
	}

}