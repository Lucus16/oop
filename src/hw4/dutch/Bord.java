/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw4.dutch;

import hw4.BoardInfo;

/**
 *
 * @author hessel
 */
public class Bord {
    /**
     * De afmetingen van het speelbord
     */
    public final static int Hoogte = 6, Breedte = 7;

    /**
     * Het bord: een 2-dimensionale rij van velden
     */
    private Kleur bord [][];   // de kleur van de gespeelde stenen

    /**
     * constructor. Maakt een leeg bord
     */
    public Bord() {
        bord = new Kleur[Breedte][Hoogte];
    }

    /**
     * een copy constructor, maakt een kopie van het gegeven bord
     * @param ander: het te kopieren bord
     */
    public Bord(Bord ander) {
        bord = new Kleur[Breedte][Hoogte];
        for(int i = 0; i < Breedte; i++){
            for(int j = 0; j < Hoogte; j++){
                if(ander.bord[i][j] != null){
                    bord[i][j] = ander.bord[i][j];
                }
            }
        }
    }
    
    public Bord(BoardInfo b) {
    	bord = new Kleur[Breedte][Hoogte];
        for(int i = 0; i < Breedte; i++){
            for(int j = 0; j < Hoogte; j++){
                bord[i][j] = Kleur.fromColor(b.getSlot(i, j));
            }
        }
    }

    /**
     * speel een steen van gegeven kleur in gegeven kolom
     * Als de kolom al vol is verandert het bord niet
     * @param kol: de kolom waarin gespeeld moet worden
     * @param kleur: de te spelen kleur
     * @return geeft aan of het mag (kolom is niet vol)
     */
    public boolean speel (int kol, Kleur kleur) {
        for(int i = 0; i < Hoogte; i++){
            if(bord[kol][i] == null){
                bord[kol][i] = kleur;
                return true;
            }
        }
        return false;
    }
    
    public boolean kolLeeg(int kol){
        if(bord[kol][5] == null)
            return true;
        return false;
    }

    /**
     * heeft het spelen van de laatste steen in deze kolom een winnaar opgeleverd
     * @param kol: de te controleren kolom
     * @return op een of andere manier 4 op 1 rij door de top van deze kolom?
     */
    public boolean winnaar(int kol)  {
        int rij = 5;
        for(int i = 1; i < Hoogte && bord[kol][i - 1] != null; i++){
            if(bord[kol][i] == null)
                rij = i - 1;
        }
        return (horizontaal(kol,rij) || diagonaal(kol,rij) || verticaal(kol,rij));
    }
    
    private boolean verticaal(int kol, int rij){
        if(rij >= 3)
            for(int i = 1; bord[kol][rij - i] == bord[kol][rij]; i++)
                if(i == 3)
                    return true;
        return false;
    }
    
    private boolean horizontaal(int kol, int rij){
        int oprij = 1;
        for(int i = kol - 1; i >= 0 && bord[i][rij] == bord[kol][rij]; i--)
            oprij++;
        for(int i = kol + 1; i < 7 && bord[i][rij] == bord[kol][rij]; i++)
            oprij++;
        return (oprij >= 4);
    }
    
    private boolean diagonaal(int kol, int rij){
        int oprij = 1;
        for(int i = 1; kol - i >= 0 && rij - i >= 0 && bord[kol - i][rij - i] == bord[kol][rij]; i++)
            oprij++;
        for(int i = 1; kol + i < 7 && rij + i < 6 && bord[kol + i][rij + i] == bord[kol][rij]; i++)
            oprij++;
        boolean een = (oprij >= 4);
        oprij = 1;
        for(int i = 1; kol + i < 7 && rij - i >= 0 && bord[kol + i][rij - i] == bord[kol][rij]; i++)
            oprij++;
        for(int i = 1; kol - i >= 0 && rij + i < 6 && bord[kol - i][rij + i] == bord[kol][rij]; i++)
            oprij++;
        return (een || (oprij >= 4));
    }
    
    /**
     * kan er nog verder gespeeld worden
     * @return is het bord vol
     */
    public boolean vol () {
        for(Kleur[] K: bord){
            for(Kleur k: K){
                if(k == null)
                    return false;
            }
        }
        return true;
    }
    
    public Kleur getKleur(int x, int y) {
        return bord[x][y];
    }
    
    public String toString(){
        String out = "";
        for(int i = 5; i >= 0; i--){
            out += "|";
            for(int j = 0; j < Breedte; j++){
                if(bord[j][i] == null)
                    out += " |";
                else{
                    switch(bord[j][i]){
                        case Rood: out += "R|"; break;
                        case Geel: out += "G|"; break;
                        default: break;
                    }
                }
            }
            out += "\n";
        }
        out += "---------------\n";
        return out;
    }
}