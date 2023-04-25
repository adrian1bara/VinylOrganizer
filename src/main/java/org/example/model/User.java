package org.example.model;
import java.util.List;

public class User {
    public int id;
    public String nume;
    public String prenume;
    public String adresaEmail;
    public List<Vinil> listaViniluri;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresaEmail() {
        return adresaEmail;
    }

    public void setAdresaEmail(String adresaEmail) {
        this.adresaEmail = adresaEmail;
    }

    public List<Vinil> getListaViniluri() {
        return listaViniluri;
    }

    public void setListaViniluri(List<Vinil> listaViniluri) {
        this.listaViniluri = listaViniluri;
    }
}

