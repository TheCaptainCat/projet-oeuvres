package com.cgp.projetoeuvres.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "id_oeuvrevente", referencedColumnName = "id_oeuvrevente", insertable = false,
            updatable = false)
    private WorkForSale workForSale;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", insertable = false,
            updatable = false)

    private Adherent adherent;
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

    public String getFormatedStatus() {
        return status.equals("pending") ? "En attente" : status.equals("confirmed") ? "Confirmé" : "Indéterminé";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WorkForSale getWorkForSale() {
        return workForSale;
    }

    public void setWorkForSale(WorkForSale workForSale) {
        this.workForSale = workForSale;
        if (!workForSale.getBookings().contains(this)) {
            workForSale.getBookings().add(this);
        }
        this.workForSaleId = workForSale.getId();
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
        if (!adherent.getBookings().contains(this)) {
            adherent.getBookings().add(this);
        }
        this.adherentId = adherent.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking that = (Booking) o;

        if (!workForSale.equals(that.workForSale)) return false;
        if (!adherent.equals(that.adherent)) return false;
        if (bookingDate != null ? !bookingDate.equals(that.bookingDate) : that.bookingDate != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workForSale.hashCode();
        result = 31 * result + adherent.hashCode();
        result = 31 * result + (bookingDate != null ? bookingDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}