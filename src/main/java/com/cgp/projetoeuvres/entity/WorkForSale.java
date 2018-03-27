package com.cgp.projetoeuvres.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oeuvrevente", schema = "baseoeuvre", catalog = "")
public class WorkForSale {
    @Id
    @Column(name = "id_oeuvrevente")
    private int id;
    @Column(name = "titre_oeuvrevente")
    private String title;
    @Column(name = "etat_oeuvrevente")
    private String state;
    @Column(name = "prix_oeuvrevente")
    private double price;
    @OneToMany(mappedBy="workForSale", cascade= {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonManagedReference
    private List<Booking> bookings;
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonBackReference // Pour éviter une récursion lors de la conversion en JSON
    @JoinColumn(name="id_proprietaire")
    private Owner owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        if (!owner.getWorksForSale().contains(this)) {
            owner.getWorksForSale().add(this);
        }
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        if (booking.getWorkForSale() != this) {
            booking.setWorkForSale(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkForSale that = (WorkForSale) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (state != null ? !state.equals(that.state) : that.state != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
