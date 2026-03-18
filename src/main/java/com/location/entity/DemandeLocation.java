package com.location.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "demande_location")
public class DemandeLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;

    @ManyToOne
    @JoinColumn(name = "unite_id")
    private UniteLocation uniteLocation;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_demande")
    private Date dateDemande = new Date();

    private String statut = "EN_ATTENTE";
    @Column(length = 1000)
    private String commentaire;

    public Long getId() { return id; }
    public Locataire getLocataire() { return locataire; }
    public void setLocataire(Locataire locataire) { this.locataire = locataire; }
    public UniteLocation getUniteLocation() { return uniteLocation; }
    public void setUniteLocation(UniteLocation uniteLocation) { this.uniteLocation = uniteLocation; }
    public Date getDateDemande() { return dateDemande; }
    public void setDateDemande(Date dateDemande) { this.dateDemande = dateDemande; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
}
