package com.location.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unite_location")
public class UniteLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "immeuble_id")
    private Immeuble immeuble;

    @Column(name = "reference_unite", length = 50)
    private String reference;

    @Column(name = "nombre_pieces", nullable = false)
    private Integer nombrePieces;

    private Double superficie;

    private Double loyer;

    @Column(name = "loyer_mensuel")
    private Double loyerMensuel;

    @Column(length = 30)
    private String statut;

    @Column(length = 255)
    private String equipements;

    @OneToMany(mappedBy = "uniteLocation")
    private List<ContratLocation> contrats = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Immeuble getImmeuble() {
        return immeuble;
    }

    public void setImmeuble(Immeuble immeuble) {
        this.immeuble = immeuble;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getNombrePieces() {
        return nombrePieces;
    }

    public void setNombrePieces(Integer nombrePieces) {
        this.nombrePieces = nombrePieces;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

    public Double getLoyer() {
        return loyer;
    }

    public void setLoyer(Double loyer) {
        this.loyer = loyer;
    }

    public Double getLoyerMensuel() {
        return loyerMensuel;
    }

    public void setLoyerMensuel(Double loyerMensuel) {
        this.loyerMensuel = loyerMensuel;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEquipements() {
        return equipements;
    }

    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }

    public List<ContratLocation> getContrats() {
        return contrats;
    }

    public void setContrats(List<ContratLocation> contrats) {
        this.contrats = contrats;
    }
}