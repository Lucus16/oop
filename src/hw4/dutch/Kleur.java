/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw4.dutch;

import hw4.Color;

/**
 *
 * @author Hessel Bongers & Rick Schouten
 */
public enum Kleur {
    Rood(), Geel(), Leeg();
    
    public Kleur ander(){
        if(this == Geel)
            return Rood;
        return Geel;
    }
    public static Kleur fromColor(Color c) {
    	switch (c) {
    	case YELLOW:
    		return Geel;
    	case RED:
    		return Rood;
    	case NONE:
    		return Leeg;
    	default:
    		return null;
    	}
    }
    
    public String toString(){
        if(this == Rood)
            return "rood";
        return "geel";
    }
}
