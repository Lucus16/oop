/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw4.dutch;

import java.util.Scanner;

/**
 *
 * @author hessel
 */
public class Mens implements Speler{
    private String naam;
    private Kleur kleur;
    
    public Mens(String nm, Kleur kl){
        naam = nm;
        kleur = kl;
    }
    
    public int Speel(Bord b){
        Scanner scan = new Scanner(System.in);
        int kolom;
        do{
            System.out.println("Geef een geldig kolomnummer waar je wil spelen.");
            kolom = scan.nextInt();
        }
        while(kolom < 1 || kolom > 7);
        return kolom;
    }
    
    public String getNaam(){
        return naam;
    }
    
    public Kleur getKleur(){
        return kleur;
    }
}
