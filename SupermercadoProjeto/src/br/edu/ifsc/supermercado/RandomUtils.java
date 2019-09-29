package br.edu.ifsc.supermercado;

import java.util.Random;

public class RandomUtils {
	public static int generateRandomIntIntRange(int min, int max) {
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
}
