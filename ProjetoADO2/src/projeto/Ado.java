package projeto;

import java.util.Scanner;

public class Ado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int HP = 100;
        int XP = 0;
        int opcao;

        Scanner leia = new Scanner(System.in);

        System.out.println("\n--------------------");
        System.out.println("\n DUNGEON EXPLORER ");
        System.out.println("\n--------------------");

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("\n1 -   INSTRUCOES");
            System.out.println("\n2 -     JOGAR");
            System.out.println("\n3 -    CREDITOS");
            System.out.println("\n4 -  SAIR DO GAME");
            System.out.println("\n  ESCOLHA SUA OPCAO: ");
            opcao = leia.nextInt();

            switch (opcao) {
            case 1: // INSTRUÇÕES
                System.out.println("\n1 -  INSTRUCOES");
                System.out.println("\nExplore para ganhar pontos de XP");
                System.out.println("\nDescanse para recuperar seu HP");
                System.out.println("\nCaso seu HP chegar a 0 o personagem MORRERA e sera o fim do jogo");
                break;

            case 2: // JOGAR
                int SubOpcao;
                do {
                    System.out.println("\n2 -    JOGAR");
                    System.out.println("\nHP: " + HP + " | XP: " + XP);
                    System.out.println("1 -         Explorar");
                    System.out.println("2 -         Descansar");
                    System.out.println("3 - Voltar ao menu principal");
                    System.out.print("         Escolha uma opcao: ");
                    SubOpcao = leia.nextInt();

                    switch (SubOpcao) {
                    case 1: // EXPLORAR
                        HP -= 10;
                        XP += 20;
                        System.out.println("\nVoce explorou a Dungeon e apanhou de um inimigo! -10 HP | +20 XP");

                        if (HP <= 0) {
                            HP = 0;
                            System.out.println("HP restante: " + HP);
                            System.out.println("\nVoce morreu! Game Over.");
                            opcao = 4;
                            SubOpcao = 3;
                        } else {
                            System.out.println("HP restante: " + HP + " | XP: " + XP);
                        }
                        break;

                    case 2: // DESCANSAR
                        if (HP == 100) {
                            System.out.println("\nVoce ja esta com HP maximo!");
                        } else {
                            HP += 15;
                            if (HP > 100) HP = 100;
                            System.out.println("\nVoce descansou e recuperou 15 HP!");
                            System.out.println("HP: " + HP + " | XP: " + XP);
                        }
                        break;

                    case 3: // VOLTAR
                        System.out.println("\nVoltando ao menu principal...");
                        break;

                    default:
                        System.out.println("\nOpcao invalida!");
                        break;
                    }

                } while (SubOpcao != 3 && HP > 0);
                break;

            case 3: // CRÉDITOS
                System.out.println("\n3 -    CREDITOS");
                System.out.println("\nDesenvolvido por: Otavio Campos Gomes");
                System.out.println("Projeto: ADO 2");
                break;

            case 4: // SAIR
                System.out.println("\nSaindo do jogo... Ate logo!");
                break;

            default:
                System.out.println("\nOpcao invalida!");
                break;
            }

        } while (opcao != 4);

        
    }

	}


