package main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	
	public static class City implements Ville{

		private Pays pays;
		private int population;
		private String nom;
		
		public City(Pays pays, int population, String nom) {
			super();
			this.pays = pays;
			this.population = population;
			this.nom = nom;
		}
		
		@Override
		public Pays getPays() {
			return pays;
		}
		@Override
		public int getPopulation() {
			return population;
		}
		@Override
		public String toString() {
			return nom;
		}
		public void setPays(Pays pays) {
			this.pays=pays;
		}
		
		
	}
	
	record Country(List<Ville> getVilles, String getContinent, City getCapitale, int getPopulation ) implements Pays{
	}
	
	static public Ville capitaleLaPlusPeuplee(Stream<Pays> P) {
		return P.map(Pays::getCapitale).max(Comparator.comparing(Ville::getPopulation)).orElse(null);
	}
	
	private static Comparator<Pays> compContinent = Comparator.comparing(Pays::getContinent);
	private static Comparator<Pays> compPopulation = Comparator.comparing(Pays::getPopulation);
	
	static public List<Pays> triCPc(Stream<Pays> P) {
		return P.sorted(compContinent.thenComparing(compPopulation))
				.toList();
	}
	
	static public List<Pays> triCPd(Stream<Pays> P) {
		return P.sorted(compContinent.thenComparing(compPopulation.reversed()))
				.toList();
	}
	
	static public List<Ville> capitalesMoinsPeuplees(Stream<Pays> P) {
		return P.filter(p -> p.getVilles().stream().anyMatch(v -> v.getPopulation()>p.getCapitale().getPopulation()))
				.map(Pays::getCapitale)
				.toList();
	}
	//a
	static int fact(int n){
			return IntStream.range(1,n+1)
					.reduce(1,(acu,x) -> acu*x);
		}
	
	public static void main(String[] args) {
		//b avec un stream de listes
		IntStream.range(0,10)
		.map(x -> fact(x))
		.forEach(System.out::println);
		//c
		IntStream.range(0,10)
		.map(x -> fact(x))
		.forEach(System.out::println);
		
		System.out.println("On est à la question c");
		IntStream.range(0, 10).map(Main::fact).forEach(System.out::println);
		//d
		System.out.println("On est à la question d");
		System.out.println("On est à la question e");
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
		Supplier<Stream<Produit>> S= () -> Stream.of(frigo,ordinateur,lampe);
		System.out.println("Somme des prix des produits d’un stream avec sum()");
		System.out.println(S.get().mapToInt(Produit::getPrix).sum());
		System.out.println("Somme des prix des produits d’un stream avec reduce");
		System.out.println(S.get().mapToInt(Produit::getPrix).reduce((x,y)->x+y).orElse(-1));
		
		City paris=new City(null, 2087600, "Paris"); 
		City toulouse=new City(null, 511684, "Toulouse"); 
		City clermontFerrand=new City(null, 150443, "Clermont-Ferrand"); 
		City pau=new City(null, 77066, "Pau"); 
		
		City berlin=new City(null, 3577000, "Berlin"); 
		City munich=new City(null, 1604384, "Munich"); 
		City hambourg=new City(null, 1787280, "Hambourg"); 
		City cologne=new City(null, 1149000, "Cologne"); 
		
		City tokyo=new City(null, 37100000, "Tokyo"); 
		City kyoto=new City(null, 1436247, "Kyoto"); 
		City osaka=new City(null, 2757642, "Osaka"); 
		City kobe=new City(null, 1501678, "Kobe"); 
		
		City pekin=new City(null, 22189000, "Pékin"); 
		City chongqing=new City(null, 17774000, "Chongqing"); 
		City shanghai=new City(null, 29868000, "Shanghai"); 
		City hangzou=new City(null, 8419840, "Hangzou"); 
		
		City canberra=new City(null, 478000, "Canberra"); 
		City melbourne=new City(null, 5316000, "Melbourne"); 
		City brisbane=new City(null, 5560500, "Brisbane"); 
		City perth=new City(null, 2143000, "Perth"); 
		
		Country france=new Country(Arrays.asList(paris,toulouse,clermontFerrand,pau),"Europe",paris,68606000);
		Country allemagne=new Country(Arrays.asList(berlin,munich,hambourg,cologne),"Europe",berlin,83445000);
		Country japon=new Country(Arrays.asList(tokyo,kyoto,osaka,kobe),"Asie",tokyo,124885175);
		Country chine=new Country(Arrays.asList(pekin,chongqing,shanghai,hangzou),"Asie",pekin,1408000000);
		Country australie=new Country(Arrays.asList(canberra,melbourne,brisbane,perth),"Océanie",canberra,27200000);
		
		paris.setPays(france);
		toulouse.setPays(france);
		clermontFerrand.setPays(france);
		pau.setPays(france);
		berlin.setPays(allemagne);
		munich.setPays(allemagne);
		hambourg.setPays(allemagne);
		cologne.setPays(allemagne);
		tokyo.setPays(japon);
		kyoto.setPays(japon);
		osaka.setPays(japon);
		kobe.setPays(japon);
		pekin.setPays(chine);
		chongqing.setPays(chine);
		shanghai.setPays(chine);
		hangzou.setPays(chine);
		canberra.setPays(australie);
		melbourne.setPays(australie);
		brisbane.setPays(australie);
		perth.setPays(australie);
		
		Supplier<Stream<Pays>> P= () -> Stream.of(france,allemagne,japon,chine,australie);
		System.out.println("\nCapitale la plus peuplee :");
		System.out.println(Main.capitaleLaPlusPeuplee(P.get()).toString());
		System.out.println("\nTri par continent puis par population croissante :");
		System.out.println(Main.triCPc(P.get()));
		System.out.println("\nTri par continent puis par population décroissante :");
		System.out.println(Main.triCPd(P.get()));
		System.out.println("\nCapitales moins peuplées que d'autres villes du pays :");
		System.out.println(Main.capitalesMoinsPeuplees(P.get()));
	}

}
