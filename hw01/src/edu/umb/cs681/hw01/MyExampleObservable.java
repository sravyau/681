package edu.umb.cs681.hw01;

public class MyExampleObservable {
	public static void main(String args[]) {

		StockQuoteObservable stockQuote = new StockQuoteObservable();
		stockQuote.addObserver((Observable o, Object obj) -> {
			System.out.println("Stock Observer 10 notified.");
		});
		stockQuote.changeQuote("Facebook", 10);
		stockQuote.addObserver((Observable o, Object obj) -> {
			System.out.println("Stock Observer 20 notified.");
		});
		stockQuote.changeQuote("Google", 20);

		DJIAQuoteObservable djiaQuote = new DJIAQuoteObservable();
		djiaQuote.addObserver((Observable o, Object obj) -> {
			System.out.println("DJIA Observer 10 notified.");
		});
		djiaQuote.addObserver((Observable o, Object obj) -> {
			System.out.println("DJIA Observer 20 notified.");
		});
		djiaQuote.changeQuote("ABCD", 143);
		djiaQuote.changeQuote("EFGH", 33);

	}
}