package com.epul.projetoeuvres.persistence;

import com.epul.projetoeuvres.persistence.entities.AdherentEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class Service extends EntityService {
    public void insertAdherent(AdherentEntity unAdherent) {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entityManager.persist(unAdherent);
            transac.commit();
            entityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AdherentEntity> consulterListeAdherents() {
        List<AdherentEntity> mesAdherents = null;
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesAdherents = (List<AdherentEntity>) entityManager.createQuery("SELECT a FROM AdherentEntity a ORDER BY a.nomAdherent").getResultList();
            entityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesAdherents;
    }

    public AdherentEntity adherentById(int numero) {
        List<AdherentEntity> adherents = null;
        AdherentEntity adherent = new AdherentEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            adherents = (List<AdherentEntity>) entityManager.createQuery("SELECT a FROM AdherentEntity a WHERE a.idAndherent=" + numero).getResultList();
            adherent = adherents.get(0);
            entityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adherent;
    }
}
