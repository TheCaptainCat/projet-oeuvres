package com.cgp.projetoeuvres.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "oeuvrepret", schema = "baseoeuvre", catalog = "")
public class WorkForLending {
    @Id
    @Column(name = "id_oeuvrepret")
    private int id;
    @Column(name = "titre_oeuvrepret")
    private String title;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        if (!owner.getWorksForLending().contains(this)) {
            owner.getWorksForLending().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkForLending that = (WorkForLending) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
