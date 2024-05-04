package knh;

import java.util.Scanner;

public class Romany extends Kniha{
String zanr;
public Romany() {
}
public void setRoman(Scanner sc) {
System.out.print("Zadejte nazev knihy: ");
super.nazev = Knihovna.vyjimkyRetezce(sc);
System.out.print("Zadejte autora/-y knihy: ");
super.autor = Knihovna.vyjimkyRetezce(sc);
System.out.print("Zadejte rok vydani knihy: ");
super.rok = Knihovna.pouzeCelaCisla(sc);
System.out.print("Zadejte zanr romanu: ");
this.zanr = Knihovna.vyjimkyRetezce(sc);
System.out.print("Zadejte stav dostupnosti knihy(k dispozici/vypujceno): ");
super.stav_dostupnosti = Knihovna.vyjimkyRetezce(sc);
}
}
