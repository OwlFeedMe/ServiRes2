/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cspor
 */
public class Miembro {
        private String Name;
        private int Nguerras;
        private int Estrellas;

    @Override
    public String toString() {
        return "Name=" + Name + ", # Guerras=" + Nguerras + ", # Estrellas=" + Estrellas + ", PorE=" + PorE + ", PorA=" + PorA + ", Puntos=" + puntos ;
    }
        private double PorE;
        private double PorA;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getNguerras() {
        return Nguerras;
    }

    public void setNguerras(int Nguerras) {
        this.Nguerras = Nguerras;
    }

    public int getEstrellas() {
        return Estrellas;
    }

    public void setEstrellas(int Estrellas) {
        this.Estrellas = Estrellas;
    }

    public double getPorE() {
        return PorE;
    }

    public void setPorE(double PorE) {
        this.PorE = PorE;
    }

    public double getPorA() {
        return PorA;
    }

    public void setPorA(double PorA) {
        this.PorA = PorA;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
        private int puntos;
        
}
