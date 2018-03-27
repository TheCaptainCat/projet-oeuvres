package com.cgp.projetoeuvres.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "adherent", schema = "baseoeuvre", catalog = "")
public class Adherent {

    @Id
    @Column(name = "id_adherent")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom_adherent")
    private String surname;
    @Column(name = "prenom_adherent")
    private String firstname;
    @Column(name = "ville_adherent")
    private String city;
    @OneToMany(mappedBy="adherent", cascade= {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonManagedReference
    private List<Booking> bookings;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String nom) {
        this.surname = nom;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String prenom) {
        this.firstname = prenom;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String ville) {
        this.city = ville;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        if (booking.getAdherent() != this) {
            booking.setAdherent(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adherent that = (Adherent) o;

        if (id != that.id) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
