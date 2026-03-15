package ADO1;

import java.util.Scanner;

public class problema1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner leia = new Scanner(System.in);
		
		float valortotal,produto1,produto2,produto3,desconto;
		
		System.out.println("\nDigite o valor do produto 1");
		produto1 = leia.nextInt();
		
		System.out.println("\nDigite o valor do produto 2");
		produto2 = leia.nextInt();
		
		System.out.println("\nDigite o valor do produto 3");
		produto3 = leia.nextInt();
		
		valortotal = (produto1+produto2+produto3);
		
		System.out.println("\nvalor total é:" + valortotal);
		
		if (valortotal >= 300) {
			desconto = (float) (valortotal * 0.20);
			
		
	} else  desconto = (float) (valortotal * 0.15);
		
		System.out.println("\nVocê ganhou um desconto de:" + desconto);
		
		System.out.printf("\nValor total é de:" + (valortotal - desconto));
		
    }

}
