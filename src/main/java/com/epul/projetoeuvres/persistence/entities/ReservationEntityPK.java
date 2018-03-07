package com.epul.projetoeuvres.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReservationEntityPK implements Serializable {
    private int idOeuvrevente;
    private int idAdherent;

    @Column(name = "id_oeuvrevente")
    @Id
    public int getIdOeuvrevente() {
        return idOeuvrevente;
    }

    public void setIdOeuvrevente(int idOeuvrevente) {
        this.idOeuvrevente = idOeuvrevente;
    }

    @Column(name = "id_adherent")
    @Id
    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationEntityPK that = (ReservationEntityPK) o;
        return idOeuvrevente == that.idOeuvrevente &&
                idAdherent == that.idAdherent;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idOeuvrevente, idAdherent);
    }
}
