package com.cgp.projetoeuvres.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proprietaire", schema = "baseoeuvre", catalog = "")
public class Owner {
    @Id
    @Column(name = "id_proprietaire")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nom_proprietaire")
    private String surname;
    @Column(name = "prenom_proprietaire")
    private String firstname;
    @OneToMany(mappedBy="owner", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private List<WorkForLending> worksForLending;
    @OneToMany(mappedBy="owner", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private List<WorkForSale> worksForSale;


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

    public List<WorkForLending> getWorksForLending() {
        return worksForLending;
    }

    public void addWorkForLending(WorkForLending workForLending) {
        this.worksForLending.add(workForLending);
        if (workForLending.getOwner() != this) {
            workForLending.setOwner(this);
        }
    }

    public List<WorkForSale> getWorksForSale() {
        return worksForSale;
    }

    public void addWorkForSale(WorkForSale workForSale) {
        this.worksForSale.add(workForSale);
        if (workForSale.getOwner() != this) {
            workForSale.setOwner(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner that = (Owner) o;

        if (id != that.id) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null)
            return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        return result;
    }
}