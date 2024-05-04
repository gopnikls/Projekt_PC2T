package knh;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Knihovna {
public static String vyjimkyRetezce(Scanner sc) {
	String retezec;
	sc = new Scanner(System.in);
	try {
		retezec = sc.nextLine().trim();
	}catch(Exception e) {
		System.out.println("Nastala vyjimka typu "+e.toString());
		sc.nextLine();
		retezec = vyjimkyRetezce(sc);
	}
	return retezec;
}
public static int pouzeCelaCisla(Scanner sc){
	sc = new Scanner(System.in);
	int cislo = 0;
	try{
		cislo = sc.nextInt();
	}
	catch(Exception e){
		System.out.print("Zadejte prosim cele cislo: ");
		sc.nextLine();
		cislo = pouzeCelaCisla(sc);
	}
	return cislo;
}

public static void vypisInfo(int ind, Kniha[] knhobj) {
	try {
		if(knhobj[ind].stav_dostupnosti.equalsIgnoreCase("vypujceno") || knhobj[ind].stav_dostupnosti.equalsIgnoreCase("vyp"))
		System.out.println("Nazev: "+knhobj[ind].nazev + " || Autor/-y: "+knhobj[ind].autor + " || Zanr: " + ((Romany)knhobj[ind]).zanr + " || Rok: "+knhobj[ind].rok + " || Stav dostupnosti: vypujceno");
		else if(knhobj[ind].stav_dostupnosti.equalsIgnoreCase("k dispozici") || knhobj[ind].stav_dostupnosti.equalsIgnoreCase("k disp"))
			System.out.println("Nazev: "+knhobj[ind].nazev + " || Autor/-y: "+knhobj[ind].autor + " || Zanr: " + ((Romany)knhobj[ind]).zanr + " || Rok: "+knhobj[ind].rok + " || Stav dostupnosti: k dispozici");
		}catch(Exception e) {
			if(knhobj[ind].stav_dostupnosti.equalsIgnoreCase("vypujceno") || knhobj[ind].stav_dostupnosti.equalsIgnoreCase("vyp"))
				System.out.println("Nazev: "+knhobj[ind].nazev + " || Autor/-y: "+knhobj[ind].autor + " || Rocnik: " + ((Ucebnice)knhobj[ind]).rocnik + " || Rok: "+knhobj[ind].rok + " || Stav dostupnosti: vypujceno");
			else if(knhobj[ind].stav_dostupnosti.equalsIgnoreCase("k dispozici") || knhobj[ind].stav_dostupnosti.equalsIgnoreCase("k disp"))
				System.out.println("Nazev: "+knhobj[ind].nazev + " || Autor/-y: "+knhobj[ind].autor + " || Rocnik: " + ((Ucebnice)knhobj[ind]).rocnik + " || Rok: "+knhobj[ind].rok + " || Stav dostupnosti: k dispozici");
		}
	}

public static void main(String [] args) {
	Scanner sc = new Scanner(System.in);
	int volba;
	boolean run = true;
	int ind=0;
	Kniha[]knhobj = new Kniha[10000];
	while(run){
		System.out.println("Menu:");
		System.out.println("[1] Pridat novou knihu");
		System.out.println("[2] Smazat knihu"); 
		System.out.println("[3] Upravit informace o knize");
		System.out.println("[4] Vyhledat knihu");
		System.out.println("[5] Zobrazit vsechny knihy"); 
		System.out.println("[6] Zobrazit knihy podle autora");
		System.out.println("[7] Zobrazit knihy podle zanru");
		System.out.println("[8] Zobrazit vypujcene knihy");
		System.out.println("[9] Ulozit informace o knize do souboru");
		System.out.println("[10] Nacist informace o knize ze souboru");
		System.out.println("[11] Ukonceni aplikace");
		
		volba = pouzeCelaCisla(sc);
		switch (volba) {
			case 1:
				System.out.print("Zadejte typ knihy(roman/ucebnice): ");
				String typ = vyjimkyRetezce(sc);
				if(typ.equalsIgnoreCase("roman")) {
					knhobj[ind] = new Romany();
					knhobj[ind].setRoman(sc);
					++ind;
				}else if(typ.equalsIgnoreCase("ucebnice")) {
					knhobj[ind] = new Ucebnice();
					knhobj[ind].setUcebnice(sc);
					++ind;
				}else {
					System.out.println("Takovy typ neexistuje");
				}
				break;
			case 2:
				System.out.print("Zadejte nazev knihy, kterou chcete smazat: ");
				String name_to_del = vyjimkyRetezce(sc);
				boolean flag = false;
				for(int i=0;i<ind;++i) {
					if(name_to_del.equalsIgnoreCase(knhobj[i].nazev)) {
						for(int j=i;j<ind;++j) {
							knhobj[j]=knhobj[j+1];
						}
						knhobj[ind-1]=null;
						--ind;
						System.out.println("Kniha byla uspesne smazana");
						flag = true;
						break;
					}
				}
				if(!flag){
					System.out.println("Smazani se nepodarilo, zkuste znovu\n(Kniha nebyla nalezena)");
				}
				break;
			case 3:
				System.out.print("Zadejte nazev knihy, kterou chcete upravit: ");
				String nazev = vyjimkyRetezce(sc);
				int podvolba = 0;
				int ind_knihy = -1;
				
				for(int i=0;i<ind;++i) {
				if(knhobj[i].nazev.equalsIgnoreCase(nazev))
					ind_knihy=i;
				}
				
				if(ind_knihy == -1)
					System.out.println("Zadna kniha s takovym nazvem nebyla nalezena");
				else {
				System.out.println("Mozne upravy:");
				System.out.println("[1] Upravit autora/-y");
				System.out.println("[2] Upravit rok vydani");
				System.out.println("[3] Upravit stav dostupnosti");
				podvolba = pouzeCelaCisla(sc);
				switch(podvolba) {
					case 1:
						System.out.print("Zadejte nove/-ho autora/-y: ");
						knhobj[ind_knihy].autor = vyjimkyRetezce(sc);
						break;
					case 2:
						System.out.print("Zadejte novy rok vydani: ");
						knhobj[ind_knihy].rok = pouzeCelaCisla(sc);
						break;
					case 3:
						boolean flag1 = false;
						while(!flag1) {
						System.out.println("Jste si jisty/-a, ze chcete zmenit stav dostupnosti?\n(ano/ne)");
						String an = vyjimkyRetezce(sc);
						if(an.equalsIgnoreCase("ano")) {
							flag1 = true;
							if(knhobj[ind_knihy].stav_dostupnosti.equalsIgnoreCase("k dispozici") || knhobj[ind_knihy].stav_dostupnosti.equalsIgnoreCase("k disp"))
								knhobj[ind_knihy].stav_dostupnosti = "vypujceno";
							else knhobj[ind_knihy].stav_dostupnosti = "k dispozici";
						}else if(an.equalsIgnoreCase("ne"))
							flag1=true;
						else System.out.println("Zadal/-a jste spatnou odpoved, zkuste znovu");
						}
						System.out.println("Zmenene informace o knize: ");
						vypisInfo(ind_knihy, knhobj);
						break;
				}
				}
				
				break;
			case 4:
				System.out.print("Zadejte nazev knihy, kterou chcete vyhledat: ");
				String nazev2 = vyjimkyRetezce(sc);
				int ind_knihy2 = -1;
				
				for(int i=0;i<ind;++i) {
				if(knhobj[i].nazev.equalsIgnoreCase(nazev2))
					ind_knihy2=i;
				}
				if(ind_knihy2 == -1) {
					System.out.println("Zadna kniha s takovym nazvem nebyla nalezena");
				}else {
				vypisInfo(ind_knihy2, knhobj);
				}
				break;
			case 5:
				String []poleNazvu = new String[ind];		
				for(int i=0;i<ind;++i) {
					poleNazvu[i]=knhobj[i].nazev;
				}
				
				Arrays.sort(poleNazvu);
				for(int i=0;i<ind;++i) {
					int temp_ind=0;
					while(!poleNazvu[i].equals(knhobj[temp_ind].nazev)) {
						++temp_ind;
					}
					vypisInfo(temp_ind, knhobj);
					}
				break;
				
			case 6:
				System.out.print("Zadejte autora: ");
				String zvautor = vyjimkyRetezce(sc);
				int pppoleRoku=0;
				
				for(int i=0;i<ind;++i) 
					if(zvautor.equalsIgnoreCase(knhobj[i].autor))
						++pppoleRoku;
				
				if(pppoleRoku>0) {
				int poleRoku[] = new int[pppoleRoku];
				int pRi = 0;
				
				for(int i=0;i<ind;++i) {
					if(zvautor.equalsIgnoreCase(knhobj[i].autor)) {
					poleRoku[pRi]=knhobj[i].rok;
					++pRi;
					}
				}
				
				Arrays.sort(poleRoku);
				
				for(int i=0;i<pRi;++i) {
					int temp_ind=0;
					for(int j=0;j<ind;++j)
						if(poleRoku[i]==knhobj[j].rok && zvautor.equalsIgnoreCase(knhobj[j].autor))
					    temp_ind=j;
					vypisInfo(temp_ind, knhobj);
					}
				}else if(pppoleRoku==0)
					System.out.println("Zadne knihy tohoto autora nebyly nalezeny");
				break;
				
			case 7:
				System.out.print("Zadejte zanr: ");
				boolean exZanru = false;
				String zvzanr = vyjimkyRetezce(sc);
				for(int i=0;i<ind;++i) {
					if((knhobj[i] instanceof Romany) && zvzanr.equalsIgnoreCase(((Romany)knhobj[i]).zanr)) {
						vypisInfo(i, knhobj);
						exZanru = true;
					}
				}
				if(!exZanru)
					System.out.println("Zadne knihy tohoto zanru nebyly nalezeny");
				break;
				
			case 8:
				boolean exVypujcenych = false;
				for(int i=0;i<ind;++i) {
					if(knhobj[i].stav_dostupnosti.equalsIgnoreCase("vypujceno") || knhobj[i].stav_dostupnosti.equalsIgnoreCase("vyp")) {
						if((knhobj[i].getClass().getSimpleName()).equalsIgnoreCase("Romany"))
							System.out.print("Typ: Roman || ");
						else if((knhobj[i].getClass().getSimpleName()).equalsIgnoreCase("Ucebnice"))
							System.out.print("Typ: Ucebnice || ");
						vypisInfo(i, knhobj);
						exVypujcenych = true;
					}
				}
				if(!exVypujcenych)
					System.out.println("Zadne vypujcene knihy nebyly nalezeny");
				break;
			case 9:
				System.out.print("Zadejte nazev knihy, kterou chcete ulozit do souboru: ");
				String zvnazev = vyjimkyRetezce(sc);
				int indKnh = -1;
				for(int i=0; i<ind;++i)
					if(zvnazev.equalsIgnoreCase(knhobj[i].nazev)) {
						indKnh = i;
						break;
					}
				if(indKnh == -1)
					System.out.println("Zadna kniha s takovym nazvem nebyla nalezena");
				else {
					try {
						FileWriter fw = new FileWriter("kniha.txt");
						BufferedWriter bw = new BufferedWriter(fw);
						try {
							if(knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("vypujceno") || knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("vyp"))
								bw.write("Typ: Roman" + "\nNazev: "+knhobj[indKnh].nazev + "\nAutor/-y: "+knhobj[indKnh].autor + "\nZanr: " + ((Romany)knhobj[indKnh]).zanr + "\nRok: "+knhobj[indKnh].rok + "\nStav dostupnosti: vypujceno ");
							else if(knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("k dispozici") || knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("k disp"))
								bw.write("Typ: Roman" + "\nNazev: "+knhobj[indKnh].nazev + "\nAutor/-y: "+knhobj[indKnh].autor + "\nZanr: " + ((Romany)knhobj[indKnh]).zanr + "\nRok: "+knhobj[indKnh].rok + "\nStav dostupnosti: k dispozici");
							}catch(Exception e) {
							if(knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("vypujceno") || knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("vyp"))
								bw.write("Typ: Ucebnice" + "\nNazev: "+knhobj[indKnh].nazev + "\nAutor/-y: "+knhobj[indKnh].autor + "\nRocnik: " + ((Ucebnice)knhobj[indKnh]).rocnik + "\nRok: "+knhobj[indKnh].rok + "\nStav dostupnosti: vypujceno");
							else if(knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("k dispozici") || knhobj[indKnh].stav_dostupnosti.equalsIgnoreCase("k disp"))
								bw.write("Typ: Ucebnice" + "\nNazev: "+knhobj[indKnh].nazev + "\nAutor/-y: "+knhobj[indKnh].autor + "\nRocnik: " + ((Ucebnice)knhobj[indKnh]).rocnik + "\nRok: "+knhobj[indKnh].rok + "\nStav dostupnosti: k dispozici");
							}
						bw.close();
						fw.close();
						System.out.println("Ulozeni do souboru probehlo uspesne\n(informace o knize byly ulozeny do souboru 'kniha.txt')");
				
					} catch (IOException e) {
						System.out.println("Stala se chyba: ");
						e.printStackTrace();
						System.out.println("Zkuste znovu");
					}
				}
				break;
			case 10:
				String soubor="kniha.txt";
				System.out.print("Zadejte nazev souboru  v formatu 'NAZEV.txt' nebo zadejte '-', jestlize chcete nacist informace ze souboru 'kniha.txt': ");
				String zvsoubor=vyjimkyRetezce(sc);
				boolean ifsoub = false;
				if(zvsoubor.equalsIgnoreCase("-")) {
					ifsoub = true;
				}else if(zvsoubor.contains(".txt")) {
					soubor=zvsoubor.trim();
					ifsoub = true;
				}
				if(!ifsoub)
					System.out.println("Zadal/-a jste spatny nazev souboru");
				else {
				try {
					FileReader fr = new FileReader(soubor);
					BufferedReader br = new BufferedReader(fr);
					String radek = br.readLine();
					
					if(radek.contains("Roman")) {
						knhobj[ind] = new Romany();
						while((radek = br.readLine())!=null) {
							int indDvojtecka = radek.indexOf(':');
							String novyradek = radek.substring(indDvojtecka+1);
							if(radek.contains("Nazev")) {
								knhobj[ind].nazev = novyradek.trim();
							}else if(radek.contains("Autor")) {
								knhobj[ind].autor = novyradek.trim();
							}else if(radek.contains("Zanr")) {
								((Romany)knhobj[ind]).zanr = novyradek.trim();
							}else if(radek.contains("Rok")) {
								knhobj[ind].rok = Integer.parseInt(novyradek.trim());
							}else if(radek.contains("Stav")) {
								knhobj[ind].stav_dostupnosti = novyradek.trim();
							}
						}
						++ind;
						System.out.println("Informace o knize byly uspesne nacteny");
					}else if(radek.contains("Ucebnice")) {
						knhobj[ind] = new Ucebnice();
						while((radek = br.readLine())!=null) {
							int indDvojtecka = radek.indexOf(':');
							String novyradek = radek.substring(indDvojtecka+1);
							if(radek.contains("Nazev")) {
								knhobj[ind].nazev = novyradek.trim();
							}else if(radek.contains("Autor")) {
								knhobj[ind].autor = novyradek.trim();
							}else if(radek.contains("Rocnik")) {
								((Ucebnice)knhobj[ind]).rocnik = Integer.parseInt(novyradek.trim());
							}else if(radek.contains("Rok")) {
								knhobj[ind].rok = Integer.parseInt(novyradek.trim());
							}else if(radek.contains("Stav")) {
								knhobj[ind].stav_dostupnosti = novyradek.trim();
							}
						}
						++ind;
						System.out.println("Informace o knize byly uspesne nacteny");
					}else System.out.println("Nepovedlo se nacist knihu, informace o knize v souboru musi byt v takovem formatu:\nTyp: ...\nNazev: ...\nAutor:/-y: ...\nZanr/Rocnik: ...\nRok: ...\nStav dostupnosti: ...");
					br.close();
					fr.close();
				}catch(IOException e) {
					System.out.println("Stala se chyba:");
					e.printStackTrace();
					System.out.println("Nejspise takovy soubor neexistuje\nZkuste znovu");
				}
				}
				break;
			case 11:
			run =false;
				break;
		}
	}
	sc.close();
	}
}