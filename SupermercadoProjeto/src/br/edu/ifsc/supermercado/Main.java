package br.edu.ifsc.supermercado;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

	public static void main(String[] args) {
				ExecutorService application = Executors.newFixedThreadPool(2);

				
				ArrayList<Produto> carrinho = new ArrayList<>();
				
				// try to start producer and consumer giving each of them access
				// to sharedLocation
				try {
					application.execute(new Comprador(carrinho));
				//	application.execute(new Consumer(sharedLocation));
				} // end try
				catch (Exception exception) {
					exception.printStackTrace();
				} // end catch

				application.shutdown(); // terminate application when threads end
	}

}
