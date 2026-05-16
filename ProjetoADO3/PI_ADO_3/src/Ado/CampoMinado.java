package Ado;

import java.util.Scanner;

public class CampoMinado {

	static final int BOMBA = -1;
	static final char OCULTO = '#';
	static final char REVELADO = ' ';
	static final char BANDEIRA = 'F';
	static final char EXPLOSAO = '*';

	static int[][] tabuleiro;
	static char[][] visivel;

	static int linhas, colunas, totalBombas;
	static int celulasRestantes;
	static boolean jogoEncerrado = false;

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);

		System.out.println("*********************************");
		System.out.println("*     CAMPO MINADO – Java       *");
		System.out.println("*   Projeto Integrador I  ADO3  *");
		System.out.println("*-------------------------------*\n");

		System.out.println("Escolha  dificuldade:");
		System.out.println("  1 - Biribinha    (8x8,   10 bombas)");
		System.out.println("  2 - Granada    (10x10, 20 bombas)");
		System.out.println("  3 -Bombastica  (12x16, 40 bombas)");
		System.out.print("Opção: ");
		int nivel = entrada.nextInt();

		switch (nivel) {
		case 1:
			linhas = 8;
			colunas = 8;
			totalBombas = 10;
			break;
		case 2:
			linhas = 10;
			colunas = 10;
			totalBombas = 20;
			break;
		case 3:
			linhas = 12;
			colunas = 16;
			totalBombas = 40;
			break;
		default:
			System.out.println("Opção inválida. Usando nível Fácil.");
			linhas = 8;
			colunas = 8;
			totalBombas = 10;
		}

		inicializarMatrizes();
		celulasRestantes = linhas * colunas - totalBombas;

		while (!jogoEncerrado) {
			imprimirTabuleiro();
			System.out.println("\nComandos:  R <linha> <coluna>  = Revelar");
			System.out.println("           F <linha> <coluna>  = Bandeira");
			System.out.print(">> ");

			String cmd = entrada.next().toUpperCase();
			int linha = entrada.nextInt() - 1;
			int col = entrada.nextInt() - 1;

			if (linha < 0 || linha >= linhas || col < 0 || col >= colunas) {
				System.out.println("Coordenadas fora do tabuleiro! Tente novamente.");
				continue;
			}

			if (cmd.equals("R")) {
				revelar(linha, col);
			} else if (cmd.equals("F")) {
				alternarBandeira(linha, col);
			} else {
				System.out.println("Comando inválido. Use R ou F.");
			}

			if (!jogoEncerrado && celulasRestantes == 0) {
				imprimirTabuleiro();
				System.out.println("\n PARABÉNS, VOCÊ GANHOU! Todas as casas seguras foram reveladas!");
				jogoEncerrado = true;
			}
		}

		entrada.close();
	}

	static void inicializarMatrizes() {

		tabuleiro = new int[linhas][colunas];
		visivel = new char[linhas][colunas];

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				visivel[i][j] = OCULTO;
			}
		}

		int bombasColocadas = 0;
		while (bombasColocadas < totalBombas) {
			int li = (int) (Math.random() * linhas);
			int co = (int) (Math.random() * colunas);
			if (tabuleiro[li][co] != BOMBA) {
				tabuleiro[li][co] = BOMBA;
				bombasColocadas++;
			}
		}

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (tabuleiro[i][j] != BOMBA) {
					tabuleiro[i][j] = contarBombasVizinhas(i, j);
				}
			}
		}
	}

	static int contarBombasVizinhas(int li, int co) {
		int count = 0;

		for (int di = -1; di <= 1; di++) {
			for (int dj = -1; dj <= 1; dj++) {
				int ni = li + di;
				int nj = co + dj;
				if (ni >= 0 && ni < linhas && nj >= 0 && nj < colunas) {
					if (tabuleiro[ni][nj] == BOMBA)
						count++;
				}
			}
		}
		return count;
	}

	static void revelar(int li, int co) {

		if (visivel[li][co] == BANDEIRA) {
			System.out.println("Remova a bandeira antes de revelar!");
			return;
		}
		if (visivel[li][co] != OCULTO) {
			System.out.println("Célula já revelada.");
			return;
		}

		if (tabuleiro[li][co] == BOMBA) {

			visivel[li][co] = EXPLOSAO;
			revelarTodasBombas();
			imprimirTabuleiro();
			System.out.println("\n BOOM! Você pisou em uma bomba. Game Over!");
			jogoEncerrado = true;
			return;
		}

		revelarCascata(li, co);
	}

	static void revelarCascata(int li, int co) {

		if (li < 0 || li >= linhas || co < 0 || co >= colunas)
			return;
		if (visivel[li][co] != OCULTO)
			return;

		if (tabuleiro[li][co] == 0) {
			visivel[li][co] = REVELADO;
		} else {
			visivel[li][co] = (char) ('0' + tabuleiro[li][co]);
		}
		celulasRestantes--;

		if (tabuleiro[li][co] == 0) {
			for (int di = -1; di <= 1; di++) {
				for (int dj = -1; dj <= 1; dj++) {
					revelarCascata(li + di, co + dj);
				}
			}
		}
	}

	static void alternarBandeira(int li, int co) {
		if (visivel[li][co] == OCULTO) {
			visivel[li][co] = BANDEIRA;
			System.out.println("Bandeira colocada em (" + (li + 1) + ", " + (co + 1) + ")");
		} else if (visivel[li][co] == BANDEIRA) {
			visivel[li][co] = OCULTO;
			System.out.println("Bandeira removida de (" + (li + 1) + ", " + (co + 1) + ")");
		} else {
			System.out.println("Não é possível colocar bandeira aqui.");
		}
	}

	static void revelarTodasBombas() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (tabuleiro[i][j] == BOMBA && visivel[i][j] != EXPLOSAO) {
					visivel[i][j] = 'B';
				}
			}
		}
	}

	static void imprimirTabuleiro() {
		System.out.println();

		System.out.print("    ");
		for (int j = 0; j < colunas; j++) {
			System.out.printf("%3d", j + 1);
		}
		System.out.println();

		System.out.print("    ");
		for (int j = 0; j < colunas; j++)
			System.out.print("---");
		System.out.println();

		for (int i = 0; i < linhas; i++) {
			System.out.printf("%2d |", i + 1);
			for (int j = 0; j < colunas; j++) {
				System.out.printf("%3c", visivel[i][j]);
			}
			System.out.println();
		}

		System.out.println("\nCélulas restantes: " + celulasRestantes + "  |  Bombas: " + totalBombas);
	}
}