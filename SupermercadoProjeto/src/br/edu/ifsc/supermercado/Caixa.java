package br.edu.ifsc.supermercado;

import java.util.ArrayList;
import java.util.Random;

public class Caixa implements Runnable 
{ 
	   private static Random generator = new Random();
	   private ArrayList<Produtos> carrinho; // reference to shared object
	   private CarrinhoBuffer carrinhoBuffer;

	   // constructor
	   public Caixa( Buffer shared )
	   {
		   carrinhoBuffer = (CarrinhoBuffer) shared;
	   } // end Consumer constructor

	   // read sharedLocation's value four times and sum the values
	   public void run()
	   {
	      Produto produto = new Produto();
	 
	      for ( int count = 1; count <= 10; count++ ) 
	      {
	         // sleep 0 to 3 seconds, read value from buffer and add to sum
	         try 
	         {
	            Thread.sleep( generator.nextInt( 1000 ) );    
	            produto= carrinhoBuffer.get();
	            System.out.printf( "\t\t\t%2d\n", produto );
	         } // end try
	         // if sleeping thread interrupted, print stack trace
	         catch ( InterruptedException exception ) 
	         {
	            exception.printStackTrace();
	         } // end catch
	      } // end for

	      System.out.printf( "\n%s %s.\n%s\n", 
	         "Consumer read values totaling", produto.toString(), "Terminating Consumer." );
//	      if(!esteira.isEmpty())
//	    	  for(int i : esteira)
//	    		  Produto produtoRetirado = esteira.get(i);
//	              
//	    		  print("Tirando item " + produtoRetirado + "esteira e colocando no empacotamento");
//	    		  valorTotal += produtoRetirado.getValor();
//	    		  empacotamento.set(produtoRetirado);
//	    		  esteira.remove(i);
//	     else
//	    	 Thread.sleep();
	   } // end method run
	} // end class Consumer
