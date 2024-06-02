package com.api.test.model;


public class Torneo {
    //Attributes
    public String NameEquipoL;
    public String NameEquipoV;
    public String GolEquipoL;
    public String GolEquipoV;
    public String MejorJugador;
    public String Mingol;
    public String Estadio;
    public int PuntosEqL;
    public int PuntosEqV;

    //Constructor
    public Torneo(String nameequipoL, String nameequipoV, String golequipoL, String golequipoV, String mejorjugador, String mingol, String estadio, int PuntoseqL, int PuntoseqV) {
        this.NameEquipoL = nameequipoL;
        this.NameEquipoV = nameequipoV;
        this.GolEquipoL = golequipoL;
        this.GolEquipoV = golequipoV;
        this.MejorJugador = mejorjugador;
        this.Mingol = mingol;
        this.Estadio =estadio;
        this.PuntosEqL =PuntoseqL;
        this.PuntosEqV =PuntoseqV;
    }   

    
}
