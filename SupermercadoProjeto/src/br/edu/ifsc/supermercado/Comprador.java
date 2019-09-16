package br.edu.ifsc.supermercado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Comprador implements Runnable {
	private static Random generator = new Random();
	private ArrayList<Produto> carrinho;
	private EsteiraBuffer esteira;
	private double valorConta = 0.0;

	public Comprador(ArrayList<Produto> shared, EsteiraBuffer esteira) {
		carrinho = shared;
		this.esteira = esteira;
	}

	public void run() {
		Produtos.criarProdutos();
		createMessage("Iniciando compras");
		for (int i = 0; i < esteira.getSize(); i++) {
			try {
				Thread.sleep((generator.nextInt((2000 - 1000) + 1) + 1000));
				int posicaoProduto = generator.nextInt(10);
				Produto produto = Produtos.getProduto(posicaoProduto);
				carrinho.add(produto);
				createMessage("Colocando item " + produto.getNome() + " no carrinho");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // ACABOU DE ESCOLHER OS PRODUTOS
		createMessage("Indo para o caixa");
		int sizeCarrinho = carrinho.size() - 1;
		for (int j = sizeCarrinho; j >= 0; j--) {
			try {
				Thread.sleep(1000);
				Produto produtoRetirado = carrinho.remove(j);
				createMessage(
						"Depositando item " + produtoRetirado.getNome() + produtoRetirado.getValor() + " na esteira");
				valorConta += produtoRetirado.getValor();
				esteira.set(produtoRetirado);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // ACABOU DE ESCOLHER OS PRODUTOS
		createMessage("Pagando conta de R$ " + valorConta);
	}

	public void createMessage(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[ HH:mm:ss ]");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Comprador : " + dtf.format(now) + " " + message);
	}
}
