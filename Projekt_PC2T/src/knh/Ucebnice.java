package knh;

import java.util.Scanner;

public class Ucebnice extends Kniha{
int rocnik;
public Ucebnice() {
	
}
public void setUcebnice(Scanner sc) {
	System.out.print("Zadejte nazev knihy: ");
	super.nazev = Knihovna.vyjimkyRetezce(sc);
	System.out.print("Zadejte autora/-y knihy: ");
	super.autor = Knihovna.vyjimkyRetezce(sc);
	System.out.print("Zadejte rok vydani knihy: ");
	super.rok = Knihovna.pouzeCelaCisla(sc);
	System.out.print("Zadejte rocnik ucebnice: ");
	this.rocnik = Knihovna.pouzeCelaCisla(sc);
	System.out.print("Zadejte stav dostupnosti knihy(k dispozici/vypujceno): ");
	super.stav_dostupnosti = Knihovna.vyjimkyRetezce(sc);
}
}
