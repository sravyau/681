package edu.umb.cs681.hw12;

public class MultiThread implements Runnable {

	public static void main(String[] args) {
		Thread t1 = new Thread(new MultiThread());
		Thread t2 = new Thread(new MultiThread());

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public void run() {
		Customer customer = new Customer(new Address("65 north point", "Boston", "MA", 02125));
		customer.setAddress(customer.getAddress().change("24 norma Way", "Quincy", "MA", 02122));
		customer.getAddress();
		customer.setAddress(new Address("10 museum Rd", "Cambridge", "MA", 02141));
		System.out.println(customer.getAddress());
	}
}