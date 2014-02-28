/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw4.dutch;

import java.util.Random;

/**
 *
 * @author hessel
 */
public class Bot implements Speler{
    private String naam;
    private Kleur kleur;
    
    public Bot(String nm, Kleur kl){
        naam = nm;
        kleur = kl;
    }
    
    private int art1(Bord b){
        for(int i = 0; i < 7; i++){
            Bord c = new Bord(b);
            c.speel(i, kleur);
            if(c.winnaar(i)){
                return (i + 1);
            }
        }
        return -1;
    }
    
    private int art2(Bord b){
        for(int i = 0; i < 7; i++){
            Bord c = new Bord(b);
            c.speel(i, kleur.ander());
            if(c.winnaar(i)){
                return (i + 1);
            }
        }
        return -1;
    }
    
    private int art3(Bord b){
        int nietDoen = -1;
        for(int n = 0; n < 7; n++){
            for(int m = 0; m < 7; m++){
                Bord c = new Bord(b);
                c.speel(n, kleur.ander());
                c.speel(m, kleur.ander());
                if(c.winnaar(m) && n != m){
                    return (m + 1);
                }
                if(n == m)
                    nietDoen = m;
            }
        }
        while(true){
            int zet;
            Random generator = new Random();
            zet = generator.nextInt(7);
            if(b.kolLeeg(zet) && zet != nietDoen){
                return (zet + 1);
            }
        }
    }
    
    public int Speel(Bord b){
        if(art1(b) != -1)
            return art1(b);
        if(art2(b) != -1)
            return art2(b);
        return art3(b);
    }
    
    public String getNaam(){
        return naam;
    }
    
    public Kleur getKleur(){
        return kleur;
    }
}
