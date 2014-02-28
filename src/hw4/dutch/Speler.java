/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw4.dutch;

/**
 *
 * @author hessel
 */
public interface Speler {
    public int Speel(Bord b); //Geeft public int Speel(Bord b);aan in welke kolom de speler wil spelen
    public String getNaam(); //Geeft de naam van de speler
    public Kleur getKleur(); //Geeft de kleur waar de speler mee speelt
}
