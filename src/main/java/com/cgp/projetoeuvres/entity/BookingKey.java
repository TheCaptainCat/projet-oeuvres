package com.cgp.projetoeuvres.entity;

import javax.persistence.*;
import java.io.Serializable;

public class BookingKey implements Serializable {

    @Id
    @Column(name = "id_oeuvrevente", insertable = false, updatable = false)
    private int workForSaleId;
    @Id
    @Column(name = "id_adherent", insertable = false, updatable = false)
    private int adherentId;

    public BookingKey() {
    }

    public BookingKey(int workForSaleId, int adherentId) {
        this.workForSaleId = workForSaleId;
        this.adherentId = adherentId;
    }

    public int getWorkForSaleId() {
        return workForSaleId;
    }

    public void setWorkForSaleId(int workForSaleId) {
        this.workForSaleId = workForSaleId;
    }

    public int getAdherentId() {
        return adherentId;
    }

    public void setAdherentId(int adherentId) {
        this.adherentId = adherentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingKey that = (BookingKey) o;

        if (workForSaleId != that.workForSaleId) return false;
        return adherentId == that.adherentId;
    }

    @Override
    public int hashCode() {
        int result = workForSaleId;
        result = 31 * result + adherentId;
        return result;
    }
}