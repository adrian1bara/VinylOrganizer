package org.example.model;

import org.example.comun.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vinil {

    public int id;

    public GenVinil gen;
    public TipVinil tip;
    public double pret;
    public StareVinil stare;
    public Date dataAparitie;
    public DimensiuneVinil dimensiune;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GenVinil getGen() {
        return gen;
    }

    public void setGen(GenVinil gen) {
        this.gen = gen;
    }

    public TipVinil getTip() {
        return tip;
    }

    public void setTip(TipVinil tip) {
        this.tip = tip;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public StareVinil getStare() {
        return stare;
    }

    public void setStare(StareVinil stare) {
        this.stare = stare;
    }

    public Date getDataAparitie() {
        return dataAparitie;
    }

    public void setDataAparitie(Date dataAparitie) {
        this.dataAparitie = dataAparitie;
    }

    public DimensiuneVinil getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(DimensiuneVinil dimensiune) {
        this.dimensiune = dimensiune;
    }

    @Override
    public String toString() {
        return "Vinil: " +
                "id=" + id +
                ", gen=" + gen +
                ", tip=" + tip +
                ", pret=" + pret +
                ", stare=" + stare +
                ", dataAparitie=" + new SimpleDateFormat("dd/MM/yyyy").format(dataAparitie) +
                ", dimensiune=" + dimensiune;
    }
}
