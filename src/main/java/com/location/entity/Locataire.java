package com.location.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locataire")
public class Locataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String nom;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(length = 30)
    private String telephone;

    @Column(length = 255)
    private String adresse;

    @OneToMany(mappedBy = "locataire")
    private List<ContratLocation> contrats = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public List<ContratLocation> getContrats() { return contrats; }
    public void setContrats(List<ContratLocation> contrats) { this.contrats = contrats; }
}
