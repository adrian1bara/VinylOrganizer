package org.example.model;
import java.util.List;

public class User {
    public int id;
    public String nume;
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

    public List<Vinil> getListaViniluri() {
        return listaViniluri;
    }

    public void setListaViniluri(List<Vinil> listaViniluri) {
        this.listaViniluri = listaViniluri;
    }
}

