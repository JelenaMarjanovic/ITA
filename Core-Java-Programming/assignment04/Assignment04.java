package assignment04;

public class Assignment04 {

    public static void main(String[] args) {
        
        // Instanciranje klasa Chocolate i Wine
		Chocolate cokolada = new Chocolate("Soko Štark - Najlepše želje", "8600939313602", 79.15, 90);
		Wine vino = new Wine("Tikveš - T'ga za jug", "5310008000182", 325.75, 0.75);

		// Ispis informacija o proizvodima na standardnom izlazu
		System.out.println(cokolada);
		System.out.println(vino);
        
    }
    
}