package br.edu.ifsc.supermercado;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static Random generator = new Random();

	public static void main(String[] args) {
		ExecutorService application = Executors.newFixedThreadPool(3);

		int quantidadeProdutos = generator.nextInt(10); // revisar de 10 a 30 produtos!
		ArrayList<Produto> carrinho = new ArrayList<>();
		EsteiraBuffer esteiraBuffer = new EsteiraBuffer(quantidadeProdutos);
		EmpacatomentoBuffer empacatomentoBuffer = new EmpacatomentoBuffer(quantidadeProdutos);
		// try to start producer and consumer giving each of them access
		// to sharedLocation
		try {
			application.execute(new Comprador(carrinho, esteiraBuffer));
			application.execute(new Caixa(esteiraBuffer, empacatomentoBuffer));
			application.execute(new Empacotador(empacatomentoBuffer));
		} // end try
		catch (Exception exception) {
			exception.printStackTrace();
		} // end catch

		application.shutdown(); // terminate application when threads end
	}

}
