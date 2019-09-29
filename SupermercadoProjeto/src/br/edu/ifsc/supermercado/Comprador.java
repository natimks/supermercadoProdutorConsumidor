package br.edu.ifsc.supermercado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Comprador implements Runnable {
	private static Random generator = new Random();
	private ArrayList<Produto> carrinho;
	private EsteiraBuffer esteira;
	private ContaBuffer conta;
	private double valorConta = 0.0;

	public Comprador(EsteiraBuffer esteira,ContaBuffer conta) {
		carrinho = new ArrayList<>();
		this.esteira = esteira;
		this.conta  = conta;
	}

	public void run() {
		Produtos.criarProdutos();
		createMessage("Iniciando compras");
		for (int i = 0; i < esteira.getSize(); i++) {
			try {
				Thread.sleep(RandomUtils.generateRandomIntIntRange(1000, 2000));
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
				createMessage("Depositando item " + produtoRetirado.getNome() + " na esteira");
				esteira.set(produtoRetirado);
				valorConta += conta.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // ACABOU DE COLOCAR OS PRODUTOS NA ESTEIRA
		createMessage("Pagando conta de R$ " + valorConta);
	}

	public void createMessage(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[ HH:mm:ss ]");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Comprador : " + dtf.format(now) + " " + message);
	}
}
