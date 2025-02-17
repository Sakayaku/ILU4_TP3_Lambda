package main;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	static int fact(int n){
			return IntStream.range(1,n+1)
					.reduce(1,(x,y) -> x*y);
		}
	public static void main(String[] args) {
		//b
		Stream.iterate(0, x->fact(x))
		.limit(10)
		//c
		.forEach(System.out::println);
		//IntStream.range(0, 10).map(?::fact).forEach(System.out::println);
		//d
		
		
		//e
		Stream.iterate(new Pair(1,1), p -> new Pair(p.n+1,p.f*(p.n+1)))
		.map(p -> p.f)
		.limit(10)
		.forEach(System.out::println);
		
		
		Produit frigo = new Produit() {
			@Override
			public int getPrix() {
				return 1000;
			}
		};
		Produit ordinateur = new Produit() {
			@Override
			public int getPrix() {
				return 600;
			}
		};
		Produit lampe = new Produit() {
			@Override
			public int getPrix() {
				return 20;
			}
		};
		Stream<Produit> s = Stream.of(frigo,ordinateur,lampe);
		s.map(Produit::getPrix).sum();
		//s.reducing(0,Produit::getPrix,Integer::sum);
	}

}
