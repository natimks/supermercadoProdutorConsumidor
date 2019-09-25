package br.edu.ifsc.supermercado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Main {
	private static Random generator = new Random();

	public static void main(String[] args) {
		int quantidadeProdutos = generator.nextInt(30) + 10; // gerando uma quantidade entre 10 e 30 produtos

		EsteiraBuffer esteiraBuffer = new EsteiraBuffer(quantidadeProdutos);
		EmpacatomentoBuffer empacatomentoBuffer = new EmpacatomentoBuffer(quantidadeProdutos);
		Thread comprador = new Thread(new Comprador(esteiraBuffer));
		Thread caixa = new Thread(new Caixa(esteiraBuffer, empacatomentoBuffer));
		Thread empacotador = new Thread(new Empacotador(empacatomentoBuffer));
		try {
			comprador.start();
			caixa.start();
			empacotador.start();
			empacotador.join();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[ HH:mm:ss ]");
			LocalDateTime now = LocalDateTime.now();
			System.out.println("Fim da simulação : " + dtf.format(now));	
		} // end try
		catch (Exception exception) {
			exception.printStackTrace();
		} // end catch
		
	}
}
