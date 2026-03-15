package ADO1;

import java.util.Scanner;

public class problema2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner leia = new Scanner(System.in);
		
		int ano;
		
		System.out.println("\nDigite um ano");
		ano = leia.nextInt();
		
		if (ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0)) {
			System.out.println("Ano bissexto");
		} else {
			System.out.println("Não é ano bissexto");
		}
		
		
		
		
			
		
	}

}
