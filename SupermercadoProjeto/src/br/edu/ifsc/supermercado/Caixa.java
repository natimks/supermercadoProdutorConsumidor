package br.edu.ifsc.supermercado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Caixa implements Runnable {
	private EsteiraBuffer esteiraBuffer;
	private EmpacatomentoBuffer empacatomentoBuffer;
	double valorConta;

	public Caixa(EsteiraBuffer shared, EmpacatomentoBuffer empacatomentoBuffer) {
		esteiraBuffer = shared;
		this.empacatomentoBuffer = empacatomentoBuffer;
	}

	public void run() {
		Produto produto = new Produto();
		try {
			for (int i = 0; i < esteiraBuffer.getSize(); i++) {
				Thread.sleep(RandomUtils.generateRandomIntIntRange(2000, 4000));
				produto = esteiraBuffer.get();
				createMessage("Passando item " + produto.getNome());
				empacatomentoBuffer.set(produto);
				valorConta += produto.getValor();
			}
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		createMessage("Informando conta de R$ " + valorConta);
	}

	public void createMessage(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[ HH:mm:ss ]");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Caixa : " + dtf.format(now) + " " + message);
	}
}
