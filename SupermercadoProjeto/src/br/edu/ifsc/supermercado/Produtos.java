package br.edu.ifsc.supermercado;

import java.util.ArrayList;

public class Produtos {
	private static ArrayList<Produto> produtos;
	
	public static void criarProdutos() {
		produtos = new ArrayList<>();
		String [] descricaoProdutos = {"Abacaxi","Laranja","Limão","Banana","Maça","Uva","Pera","Manga","Abobora","Rucula","Melancia"};
		double [] valoresProdutos = {10,9,8,7,6,5,4,3,2,1};
		for(int i =0; i< 10;i++) {
			produtos.add(new Produto(descricaoProdutos[i], valoresProdutos[i])); 
		}
	}
	public static ArrayList<Produto> getProdutos(int quantidade) {
		ArrayList<Produto> result = new ArrayList<>();
		for(int i =0; i< quantidade;i++) {
			int posicao = (int)Math.random()%9;
			result.add(produtos.get(posicao));
		}
		return result;
	}
	public static Produto getProduto(int posicao) {
		return produtos.get(posicao);
	}
}
