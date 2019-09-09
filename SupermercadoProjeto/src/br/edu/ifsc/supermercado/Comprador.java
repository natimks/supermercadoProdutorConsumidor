package br.edu.ifsc.supermercado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Comprador implements Runnable {
	private static Random generator = new Random();
	private ArrayList<Produto> carrinho;

	public Comprador(ArrayList<Produto> shared) {
		carrinho = shared;	
	}

	public void run() {
		Produtos.criarProdutos();
		createMessage("Iniciando compras");
		int quantidadeProdutos = generator.nextInt(30); // revisar de 10 a 30 produtos!
		for (int i = 0; i < quantidadeProdutos; i++) {
			try {
				Thread.sleep((generator.nextInt((2000 - 1000) + 1) + 1000)); 
				int posicaoProduto = generator.nextInt(10);
				Produto produto = Produtos.getProduto(posicaoProduto);
				carrinho.add(produto);
				createMessage("Colocando item "+ produto.getNome()+" no carrinho");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // ACABOU DE ESCOLHER OS PRODUTOS
		createMessage("Indo para o caixa");
		
		for (int i = 0; i < quantidadeProdutos; i++) {
			try {
				Thread.sleep(1000); 
				Produto produtoRetirado = carrinho.get(i);
				createMessage("Depositando item "+ produtoRetirado.getNome()+" no carrinho");
				esteira.set(produtoRetirado);
				carrinho.remove(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // ACABOU DE ESCOLHER OS PRODUTOS
		createMessage("Indo para o caixa");
		
		
	}

	public void createMessage(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[ HH:mm:ss ]");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Comprador : " + dtf.format(now) + " " + message);
	}
}
