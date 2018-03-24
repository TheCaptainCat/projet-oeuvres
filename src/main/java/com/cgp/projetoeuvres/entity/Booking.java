package com.cgp.projetoeuvres.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "reservation", schema = "baseoeuvre", catalog = "")
@IdClass(BookingKey.class)
public class Booking implements Serializable{
    @Id
    @Column(name = "id_oeuvrevente")
    private int workForSaleId;
    @Id
    @Column(name = "id_adherent")
    private int adherentId;
    @Column(name = "date_reservation")
    private Date bookingDate;
    @Column(name = "statut")
    private String status;

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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking that = (Booking) o;

        if (workForSaleId != that.workForSaleId) return false;
        if (adherentId != that.adherentId) return false;
        if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workForSaleId;
        result = 31 * result + adherentId;
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}