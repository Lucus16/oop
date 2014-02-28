/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw4.dutch;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author hessel
 */
public class Spel {
    private Bord spel;
    private Speler[] spelers;
    
    public Spel(){
        spel = new Bord();
        spelers = new Speler[2];
    }
    
    public void Spelen(){
        Scanner scan = new Scanner(System.in);
        String input = "";
        speler(1, scan, input);
        speler(2, scan, input);
        Random generator = new Random();
        int beurt = generator.nextInt(2);
        int kol;
        do{
        System.out.print(spel.toString());
        System.out.println(spelers[beurt].getNaam() + " is aan de beurt.");
        Bord b = new Bord(spel);
        kol = spelers[beurt].Speel(b) - 1;
        spel.speel(kol, spelers[beurt].getKleur());
        beurt = (beurt + 1) % 2;
        }
        while(!(spel.winnaar(kol) || spel.vol()));
        System.out.print(spel.toString());
        System.out.print("Gewonnen!");
    }
    
    private void speler(int i, Scanner scan, String input){
        System.out.println("Is speler " + i + " een computer of een mens? c/m");
        input = scan.next();
        if(input.equals("c")){
            System.out.println("Met welke kleur speelt deze computer(Rood/Geel)?");
            Kleur kleur;
            if(scan.next().equals("Rood"))
                kleur = Kleur.Rood;
            else
                kleur = Kleur.Geel;
            if(i == 2 && kleur == spelers[0].getKleur())
                kleur = kleur.ander();
            spelers[i - 1] = new Bot("Computer" + i, kleur);
        }
        if(input.equals("m")){
            System.out.println("Wat is de naam en de kleur(Rood/Geel)?");
            String naam = scan.next();
            Kleur kleur;
            if(scan.next().equals("Rood"))
                kleur = Kleur.Rood;
            else
                kleur = Kleur.Geel;
            if(i == 2 && kleur == spelers[0].getKleur())
                kleur = kleur.ander();
            spelers[i-1] = new Mens(naam, kleur);
        }
    }
}
