package com.location.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "paiement")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private ContratLocation contrat;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_paiement")
    private Date datePaiement;

    private Double montant;

    @Column(name = "mode_paiement", length = 50)
    private String modePaiement;

    @Column(name = "numero_recu", length = 50)
    private String numeroRecu;

    @Column(name = "mois_paiement")
    private Integer moisPaiement;

    @Column(name = "annee_paiement")
    private Integer anneePaiement;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_echeance")
    private Date dateEcheance;

    @Column(length = 30)
    private String statut;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ContratLocation getContrat() {
        return contrat;
    }

    public void setContrat(ContratLocation contrat) {
        this.contrat = contrat;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getNumeroRecu() {
        return numeroRecu;
    }

    public void setNumeroRecu(String numeroRecu) {
        this.numeroRecu = numeroRecu;
    }

    public Integer getMoisPaiement() {
        return moisPaiement;
    }

    public void setMoisPaiement(Integer moisPaiement) {
        this.moisPaiement = moisPaiement;
    }

    public Integer getAnneePaiement() {
        return anneePaiement;
    }

    public void setAnneePaiement(Integer anneePaiement) {
        this.anneePaiement = anneePaiement;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}