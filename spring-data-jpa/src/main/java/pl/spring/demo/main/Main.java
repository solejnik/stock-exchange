package pl.spring.demo.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.spring.demo.brockeroffice.BrockerOffice;
import pl.spring.demo.provider.TitleProvider;

public class Main {
	private static ApplicationContext ctx;


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ctx = new ClassPathXmlApplicationContext("spring/context.xml");
		BrockerOffice bean = ctx.getBean("brockerOffice", BrockerOffice.class);
		TitleProvider title = ctx.getBean("titleProvider", TitleProvider.class);
		System.out.println(title.getTitle());
		System.out.println("Witaj w symulatorze gieldy. Tutaj mozesz przetestowac rozne strategie gry na gieldzie."
				+ "\nSymulator dostarcza mozliwosc wyboru osobno strategii kupna oraz strategii sprzedazy akcji."
				+ "\nOto one : (nacisnij dowolny klawisz)");
		scanner.nextLine();
		System.out.println("\n*kupna"
				+ "\n1 - kupowanie akcji za 20% zawartosci portfela gdzie cena akcji jest mniejsza niz srednia cena akcji z 10 notowanych dni"
				+ "\n2 - kupowanie akcji za 80% zawartosci portfela gdzie cena akcji jest mniejsza niz srednia cena akcji z 10 notowanych dni"
				+ "\n3 - kupowanie akcji za 20% zawartosci portfela gdzie cena akcji jest mniejsza niz srednia cena akcji z 30 notowanych dni"
				+ "\n4 - kupowanie akcji za 80% zawartosci portfela gdzie cena akcji jest mniejsza niz srednia cena akcji z 30 notowanych dni"
				+ "\n*sprzedazy"
				+ "\n1 - sprzedawanie 25% posiadanych akcji gdzie cena akcji jest wieksza niz srednia cena akcji z 5 notowanych dni"
				+ "\n2 - sprzedawanie 75% posiadanych akcji gdzie cena akcji jest wieksza niz srednia cena akcji z 5 notowanych dni"
				+ "\n3 - sprzedawanie 25% posiadanych akcji gdzie cena akcji jest wieksza niz srednia cena akcji z 25 notowanych dni"
				+ "\n4 - sprzedawanie 75% posiadanych akcji gdzie cena akcji jest wieksza niz srednia cena akcji z 25 notowanych dni (nacisnij dowolny klawisz)");
		scanner.nextLine();
		String buyStrategyString;
		do {
			System.out.println("Wybierz numer stragerii kupna");
			buyStrategyString = scanner.nextLine();
		} while (!(isIntegerRegex(buyStrategyString) && Integer.parseInt(buyStrategyString) >= 1
				&& Integer.parseInt(buyStrategyString) <= 4));
		String sellStrategyString;
		do {
			System.out.println("Wybierz numer stragerii sprzedazy");
			sellStrategyString = scanner.nextLine();
		} while (!(isIntegerRegex(sellStrategyString) && Integer.parseInt(sellStrategyString) >= 1
				&& Integer.parseInt(sellStrategyString) <= 4));
		String printDailyStocks;
		do {
			System.out.println("Wyswietlac dzienne akcje? (tak lub nie)");
			printDailyStocks = scanner.nextLine();
		} while (!(printDailyStocks.equals("tak")||printDailyStocks.equals("nie")));
		boolean printDecision = printDailyStocks.equals("tak")?true:false;
		 bean.initBrockerOffice();
		 bean.play(Integer.parseInt(buyStrategyString),Integer.parseInt(sellStrategyString),printDecision);
		 scanner.close();
	}

	public static boolean isIntegerRegex(String str) {
		return str.matches("^[0-9]+$");
	}
}
