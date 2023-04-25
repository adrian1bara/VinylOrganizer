package org.example;

import org.example.comun.*;
import org.example.model.User;
import org.example.model.Vinil;

import java.util.*;

public class VinilLogic {
    private Map<User, List<Vinil>> userVinilMap;
    private List<Vinil> viniluriDisponibile;
    public void adaugareVinil(Vinil vinilNou, User user){
        viniluriDisponibile.add(vinilNou);
        userVinilMap.put(user, viniluriDisponibile);
    }

    public Vinil construiesteVinil(String gen, String tip, int pret, String stare, Date dataAparitiei, String culoare, String dimensiune){
        Vinil vinil = new Vinil();
        Random random = new Random();
        int max = 55;
        vinil.setId(random.nextInt(max));
        vinil.setGen(GenVinil.valueOf(gen));
        vinil.setTip(TipVinil.valueOf(tip));
        vinil.setPret(pret);
        vinil.setStare(StareVinil.valueOf(stare));
        vinil.setDataAparitie(dataAparitiei);
        vinil.setCuloare(CuloareVinil.valueOf(culoare));
        vinil.setDimensiune(DimensiuneVinil.valueOf(dimensiune));
        return vinil;
    }

    public int recomandarePret(Vinil vinil){
        int pretRecomandat = 0;
        int sumaRecomndata;
        int pretInitial = vinil.getPret();
        Calendar before90s = Calendar.getInstance();
        before90s.set(Calendar.MONTH, 01);
        before90s.set(Calendar.DATE, 01);
        before90s.set(Calendar.YEAR, 1990);
        Date dataDeReferinta = before90s.getTime();

        Calendar after00s = Calendar.getInstance();
        before90s.set(Calendar.MONTH, 01);
        before90s.set(Calendar.DATE, 01);
        before90s.set(Calendar.YEAR, 2000);
        Date dataDeReferinta2 = before90s.getTime();

        boolean veryGoodCondition = vinil.getStare().equals(StareVinil.NEW) || vinil.getStare().equals(StareVinil.MINT);
        boolean goodCondition = vinil.getStare().equals(StareVinil.NEAR_MINT);
        boolean notSoGoodCondition = vinil.getStare().equals(StareVinil.NOT_SO_CLEAN);

        boolean isBefore90s = vinil.getDataAparitie().before(dataDeReferinta);
        boolean isAfter00s = vinil.getDataAparitie().after(dataDeReferinta2);

        // inainte de 1990
        if( isBefore90s ){
            if( vinil.getTip().equals(TipVinil.ORIGINAL)){
                if ( veryGoodCondition) {
                    // pretul creste cu 20%
                    sumaRecomndata = (int)(pretInitial*(20.0f/100.0f));
                    pretRecomandat = pretInitial + sumaRecomndata;
                } else if ( goodCondition) {
                    // pretul creste cu 10%
                    sumaRecomndata = (int)(pretInitial*(10.0f/100.0f));
                    pretRecomandat = pretInitial + sumaRecomndata;
                } else if ( notSoGoodCondition) {
                    // pretul scade cu 15%
                    sumaRecomndata = (int)(pretInitial*(15.0f/100.0f));
                    pretRecomandat = pretInitial - sumaRecomndata;
                }
            } else {
                // pretul scade cu 10%
                sumaRecomndata = (int)(pretInitial*(10.0f/100.0f));
                pretRecomandat = pretInitial - sumaRecomndata;
            }

        }
        // dupa 1990 dar inainte de 2000
        else if ( !isBefore90s && !isAfter00s ) {
            if( vinil.getTip().equals(TipVinil.ORIGINAL)) {
                if (veryGoodCondition) {
                    // pretul creste cu 15%
                    sumaRecomndata = (int) (pretInitial * (15.0f / 100.0f));
                    pretRecomandat = pretInitial + sumaRecomndata;
                } else if (goodCondition) {
                    // pretul creste cu 5%
                    sumaRecomndata = (int) (pretInitial * (5.0f / 100.0f));
                    pretRecomandat = pretInitial + sumaRecomndata;
                } else if (notSoGoodCondition) {
                    // pretul scade cu 10%
                    sumaRecomndata = (int) (pretInitial * (10.0f / 100.0f));
                    pretRecomandat = pretInitial - sumaRecomndata;
                }
            } else {
                // pretul scade cu 5%
                sumaRecomndata = (int)(pretInitial*(5.0f/100.0f));
                pretRecomandat = pretInitial - sumaRecomndata;
            }
        }
        // dupa 2000
        else if (isAfter00s) {
            if( vinil.getTip().equals(TipVinil.ORIGINAL)) {
                if (veryGoodCondition) {
                    // pretul creste cu 10%
                    sumaRecomndata = (int) (pretInitial * (10.0f / 100.0f));
                    pretRecomandat = pretInitial + sumaRecomndata;
                } else if (goodCondition) {
                    // pretul creste cu 5%
                    sumaRecomndata = (int) (pretInitial * (5.0f / 100.0f));
                    pretRecomandat = pretInitial + sumaRecomndata;
                } else if (notSoGoodCondition) {
                    // pretul scade cu 15%
                    sumaRecomndata = (int) (pretInitial * (15.0f / 100.0f));
                    pretRecomandat = pretInitial - sumaRecomndata;
                }
            } else {
                // pretul scade cu 10%
                sumaRecomndata = (int)(pretInitial*(10.0f/100.0f));
                pretRecomandat = pretInitial - sumaRecomndata;
            }
        }

        return pretRecomandat;
    }

    public void salvarePretRecomandat (Vinil vinil, User user, int pretRecomandat){
        vinil.setPret(pretRecomandat);
        int index = viniluriDisponibile.indexOf(vinil);
        viniluriDisponibile.set(index, vinil);
        userVinilMap.replace(user, viniluriDisponibile);
    }

    public List<Vinil> afisareListaViniluri (User user) {
        return userVinilMap.get(user);
    }
}
