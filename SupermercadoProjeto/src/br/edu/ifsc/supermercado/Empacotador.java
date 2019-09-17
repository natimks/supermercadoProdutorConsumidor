package br.edu.ifsc.supermercado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Empacotador implements Runnable {
	private EmpacatomentoBuffer empacatomentoBuffer;
	double valorConta;

	public Empacotador(EmpacatomentoBuffer empacatomentoBuffer) {
		this.empacatomentoBuffer = empacatomentoBuffer;
	}

	public void run() {
		Produto produto = new Produto();
		try {
			for (int i = 0; i < empacatomentoBuffer.getSize(); i++) {
				Thread.sleep(2000);
				produto = empacatomentoBuffer.get();
				createMessage("Acomodando item " + produto.getNome() + " na sacola");
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		createMessage("Fim do empacotamento");
	}

	public void createMessage(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[ HH:mm:ss ]");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Ensacolador : " + dtf.format(now) + " " + message);
	}
} // end class Consumer
