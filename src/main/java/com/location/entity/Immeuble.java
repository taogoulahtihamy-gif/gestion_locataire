package com.location.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "immeuble")
public class Immeuble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String nom;

    @Column(nullable = false, length = 255)
    private String adresse;

    @Column(name = "nombre_unites")
    private Integer nombreUnites;

    @Column(length = 255)
    private String equipements;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "immeuble")
    private List<UniteLocation> unites = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public Integer getNombreUnites() { return nombreUnites; }
    public void setNombreUnites(Integer nombreUnites) { this.nombreUnites = nombreUnites; }
    public String getEquipements() { return equipements; }
    public void setEquipements(String equipements) { this.equipements = equipements; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<UniteLocation> getUnites() { return unites; }
    public void setUnites(List<UniteLocation> unites) { this.unites = unites; }
}
