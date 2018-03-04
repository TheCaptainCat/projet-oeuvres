package com.epul.projetoeuvres.models;

import com.epul.projetoeuvres.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private String firstname;
    private String city;

    public Member(int id, String name, String firstname, String city) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.city = city;
    }

    public static List<Member> queryMembers() {
        Database database = Database.getInstance();
        ResultSet rs;
        List<Member> members = new ArrayList<>();
        try {
            rs = database.executeQuery("select * from adherent");
            while (rs.next()) {
                members.add(new Member(rs.getInt("id_adherent"), rs.getString("nom_adherent"),
                        rs.getString("prenom_adherent"), rs.getString("ville_adherent")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("Member{id=%d, name='%s', firstname='%s', city='%s'}", id, name, firstname, city);
    }
}
