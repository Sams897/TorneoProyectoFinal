package model;


public class Torneo {
    public String NameEquipoL;
    public String NameEquipoV;
    public int GolEquipoL;
    public int GolEquipoV;
    public String MejorJugador;
    public int Mingol;
    public String Estadio;
    public int PuntosEqL;
    public int PuntosEqV;

    public Torneo(String nameEquipoL, String nameEquipoV, int golEquipoL, int golEquipoV, String mejorJugador, int mingol, String estadio, int puntosEqL, int puntosEqV) {
        this.NameEquipoL = nameEquipoL;
        this.NameEquipoV = nameEquipoV;
        this.GolEquipoL = golEquipoL;
        this.GolEquipoV = golEquipoV;
        this.MejorJugador = mejorJugador;
        this.Mingol = mingol;
        this.Estadio = estadio;
        this.PuntosEqL = puntosEqL;
        this.PuntosEqV = puntosEqV;
    }

    // Getters y setters
    public String getNameEquipoL() {
        return NameEquipoL;
    }

    public String getNameEquipoV() {
        return NameEquipoV;
    }

    public int getGolEquipoL() {
        return GolEquipoL;
    }

    public int getGolEquipoV() {
        return GolEquipoV;
    }

    public String getMejorJugador() {
        return MejorJugador;
    }

    public int getMingol() {
        return Mingol;
    }

    public void setMingol(int mingol) {
        this.Mingol = mingol;
    }

    public String getEstadio() {
        return Estadio;
    }

    public int getPuntosEqL() {
        return PuntosEqL;
    }

    public void setPuntosEqL(int puntosEqL) {
        this.PuntosEqL = puntosEqL;
    }

    public int getPuntosEqV() {
        return PuntosEqV;
    }

    public void setPuntosEqV(int puntosEqV) {
        this.PuntosEqV = puntosEqV;
    }
}
