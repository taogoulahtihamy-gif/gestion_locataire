package com.location.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contrat_location")
public class ContratLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;

    @ManyToOne
    @JoinColumn(name = "unite_id")
    private UniteLocation uniteLocation;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut")
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin")
    private Date dateFin;

    @Column(length = 30)
    private String statut;

    @OneToMany(mappedBy = "contrat")
    private List<Paiement> paiements = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Locataire getLocataire() { return locataire; }
    public void setLocataire(Locataire locataire) { this.locataire = locataire; }
    public UniteLocation getUniteLocation() { return uniteLocation; }
    public void setUniteLocation(UniteLocation uniteLocation) { this.uniteLocation = uniteLocation; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public List<Paiement> getPaiements() { return paiements; }
    public void setPaiements(List<Paiement> paiements) { this.paiements = paiements; }
}
