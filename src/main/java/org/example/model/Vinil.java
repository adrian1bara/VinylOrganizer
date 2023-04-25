package org.example.model;

import org.example.comun.*;

import java.util.Date;

public class Vinil {

    public int id;

    public GenVinil gen;
    public TipVinil tip;
    public int pret;
    public StareVinil stare;
    public Date dataAparitie;
    public CuloareVinil culoare;
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

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
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

    public CuloareVinil getCuloare() {
        return culoare;
    }

    public void setCuloare(CuloareVinil culoare) {
        this.culoare = culoare;
    }

    public DimensiuneVinil getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(DimensiuneVinil dimensiune) {
        this.dimensiune = dimensiune;
    }
}
