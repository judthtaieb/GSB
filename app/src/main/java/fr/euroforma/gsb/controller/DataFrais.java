package fr.euroforma.gsb.controller;

public class DataFrais {
    private String libelle;
    private String montant;
    private String datefrais;
    private String datesaisie;

    public DataFrais(String libelle,String montant,String datefrais ,String datesaisie){

        this.libelle = libelle ;
        this.montant= montant;
        this.datefrais=datefrais;
        this.datesaisie=datesaisie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDatefrais() {
        return datefrais;
    }

    public void setDatefrais(String datefrais) {
        this.datefrais = datefrais;
    }

    public String getDatesaisie() {
        return datesaisie;
    }

    public void setDatesaisie(String datesaisie) {
        this.datesaisie = datesaisie;
    }
}

