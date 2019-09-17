package br.edu.ifsc.supermercado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static Random generator = new Random();

	public static void main(String[] args) {
		ExecutorService application = Executors.newFixedThreadPool(3);

		int quantidadeProdutos = generator.nextInt(30) + 10; // gerando uma quantidade entre 10 e 30 produtos

		EsteiraBuffer esteiraBuffer = new EsteiraBuffer(quantidadeProdutos);
		EmpacatomentoBuffer empacatomentoBuffer = new EmpacatomentoBuffer(quantidadeProdutos);
		try {
			application.execute(new Comprador(esteiraBuffer));
			application.execute(new Caixa(esteiraBuffer, empacatomentoBuffer));
			application.execute(new Empacotador(empacatomentoBuffer));
		} // end try
		catch (Exception exception) {
			exception.printStackTrace();
		} // end catch

		application.shutdown(); // terminate application when threads end
		while(!application.isTerminated()) {
			
		}
			
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[ HH:mm:ss ]");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Fim da simulação : " + dtf.format(now));
	}
}
