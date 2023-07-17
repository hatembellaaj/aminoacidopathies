package tn.mdweb.aminoacidopathies.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tn.mdweb.aminoacidopathies.domain.enumeration.eactivite;
import tn.mdweb.aminoacidopathies.domain.enumeration.ecasfamiliaux;
import tn.mdweb.aminoacidopathies.domain.enumeration.ecirconstance;
import tn.mdweb.aminoacidopathies.domain.enumeration.econsanguinite;
import tn.mdweb.aminoacidopathies.domain.enumeration.ecouverture;
import tn.mdweb.aminoacidopathies.domain.enumeration.egouvernorat;
import tn.mdweb.aminoacidopathies.domain.enumeration.egouvernoratmere;
import tn.mdweb.aminoacidopathies.domain.enumeration.elieudeces;
import tn.mdweb.aminoacidopathies.domain.enumeration.eniveauscolarisation;
import tn.mdweb.aminoacidopathies.domain.enumeration.escolarisetype;
import tn.mdweb.aminoacidopathies.domain.enumeration.esexe;
import tn.mdweb.aminoacidopathies.domain.enumeration.estatut;

/**
 * A Fiche.
 */
@Entity
@Table(name = "fiche")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fiche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "datemaj", nullable = false)
    private LocalDate datemaj;

    @Column(name = "type_observation")
    private String type_observation;

    @Column(name = "identifiant_registre")
    private String identifiant_registre;

    @Column(name = "date_enregistrement")
    private LocalDate date_enregistrement;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private esexe sexe;

    @Column(name = "date_naissance")
    private LocalDate date_naissance;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private estatut statut;

    @Column(name = "date_deces")
    private LocalDate date_deces;

    @Enumerated(EnumType.STRING)
    @Column(name = "circonstance_deces")
    private ecirconstance circonstance_deces;

    @Column(name = "autre_circonstance_deces")
    private String autre_circonstance_deces;

    @Enumerated(EnumType.STRING)
    @Column(name = "lieu_deces")
    private elieudeces lieu_deces;

    @Enumerated(EnumType.STRING)
    @Column(name = "consanguinite")
    private econsanguinite consanguinite;

    @Enumerated(EnumType.STRING)
    @Column(name = "origine_geo_pere_gouvernorat")
    private egouvernorat origine_geo_pere_gouvernorat;

    @Enumerated(EnumType.STRING)
    @Column(name = "origine_geo_mere_gouvernorat")
    private egouvernoratmere origine_geo_mere_gouvernorat;

    @Column(name = "origine_geo_pere_deleguation")
    private String origine_geo_pere_deleguation;

    @Column(name = "origine_geo_mere_deleguation")
    private String origine_geo_mere_deleguation;

    @Enumerated(EnumType.STRING)
    @Column(name = "couverture_sociale")
    private ecouverture couverture_sociale;

    @Column(name = "autre_couverture_sociale")
    private String autre_couverture_sociale;

    @Enumerated(EnumType.STRING)
    @Column(name = "activite")
    private eactivite activite;

    @Column(name = "btravail")
    private Boolean btravail;

    @Column(name = "travail")
    private String travail;

    @Column(name = "scolarise")
    private Boolean scolarise;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_scolarise")
    private escolarisetype type_scolarise;

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau_scolarisation")
    private eniveauscolarisation niveau_scolarisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_familiaux")
    private ecasfamiliaux cas_familiaux;

    @Column(name = "nbcasconfirme")
    private Integer nbcasconfirme;

    @Column(name = "nbcassuspectes")
    private Integer nbcassuspectes;

    @Column(name = "nbcasdecedes")
    private Integer nbcasdecedes;

    @ManyToOne(optional = false)
    @NotNull
    private Pathologie pathologie;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Fiche id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatemaj() {
        return this.datemaj;
    }

    public Fiche datemaj(LocalDate datemaj) {
        this.setDatemaj(datemaj);
        return this;
    }

    public void setDatemaj(LocalDate datemaj) {
        this.datemaj = datemaj;
    }

    public String getType_observation() {
        return this.type_observation;
    }

    public Fiche type_observation(String type_observation) {
        this.setType_observation(type_observation);
        return this;
    }

    public void setType_observation(String type_observation) {
        this.type_observation = type_observation;
    }

    public String getIdentifiant_registre() {
        return this.identifiant_registre;
    }

    public Fiche identifiant_registre(String identifiant_registre) {
        this.setIdentifiant_registre(identifiant_registre);
        return this;
    }

    public void setIdentifiant_registre(String identifiant_registre) {
        this.identifiant_registre = identifiant_registre;
    }

    public LocalDate getDate_enregistrement() {
        return this.date_enregistrement;
    }

    public Fiche date_enregistrement(LocalDate date_enregistrement) {
        this.setDate_enregistrement(date_enregistrement);
        return this;
    }

    public void setDate_enregistrement(LocalDate date_enregistrement) {
        this.date_enregistrement = date_enregistrement;
    }

    public esexe getSexe() {
        return this.sexe;
    }

    public Fiche sexe(esexe sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(esexe sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDate_naissance() {
        return this.date_naissance;
    }

    public Fiche date_naissance(LocalDate date_naissance) {
        this.setDate_naissance(date_naissance);
        return this;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public estatut getStatut() {
        return this.statut;
    }

    public Fiche statut(estatut statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(estatut statut) {
        this.statut = statut;
    }

    public LocalDate getDate_deces() {
        return this.date_deces;
    }

    public Fiche date_deces(LocalDate date_deces) {
        this.setDate_deces(date_deces);
        return this;
    }

    public void setDate_deces(LocalDate date_deces) {
        this.date_deces = date_deces;
    }

    public ecirconstance getCirconstance_deces() {
        return this.circonstance_deces;
    }

    public Fiche circonstance_deces(ecirconstance circonstance_deces) {
        this.setCirconstance_deces(circonstance_deces);
        return this;
    }

    public void setCirconstance_deces(ecirconstance circonstance_deces) {
        this.circonstance_deces = circonstance_deces;
    }

    public String getAutre_circonstance_deces() {
        return this.autre_circonstance_deces;
    }

    public Fiche autre_circonstance_deces(String autre_circonstance_deces) {
        this.setAutre_circonstance_deces(autre_circonstance_deces);
        return this;
    }

    public void setAutre_circonstance_deces(String autre_circonstance_deces) {
        this.autre_circonstance_deces = autre_circonstance_deces;
    }

    public elieudeces getLieu_deces() {
        return this.lieu_deces;
    }

    public Fiche lieu_deces(elieudeces lieu_deces) {
        this.setLieu_deces(lieu_deces);
        return this;
    }

    public void setLieu_deces(elieudeces lieu_deces) {
        this.lieu_deces = lieu_deces;
    }

    public econsanguinite getConsanguinite() {
        return this.consanguinite;
    }

    public Fiche consanguinite(econsanguinite consanguinite) {
        this.setConsanguinite(consanguinite);
        return this;
    }

    public void setConsanguinite(econsanguinite consanguinite) {
        this.consanguinite = consanguinite;
    }

    public egouvernorat getOrigine_geo_pere_gouvernorat() {
        return this.origine_geo_pere_gouvernorat;
    }

    public Fiche origine_geo_pere_gouvernorat(egouvernorat origine_geo_pere_gouvernorat) {
        this.setOrigine_geo_pere_gouvernorat(origine_geo_pere_gouvernorat);
        return this;
    }

    public void setOrigine_geo_pere_gouvernorat(egouvernorat origine_geo_pere_gouvernorat) {
        this.origine_geo_pere_gouvernorat = origine_geo_pere_gouvernorat;
    }

    public egouvernoratmere getOrigine_geo_mere_gouvernorat() {
        return this.origine_geo_mere_gouvernorat;
    }

    public Fiche origine_geo_mere_gouvernorat(egouvernoratmere origine_geo_mere_gouvernorat) {
        this.setOrigine_geo_mere_gouvernorat(origine_geo_mere_gouvernorat);
        return this;
    }

    public void setOrigine_geo_mere_gouvernorat(egouvernoratmere origine_geo_mere_gouvernorat) {
        this.origine_geo_mere_gouvernorat = origine_geo_mere_gouvernorat;
    }

    public String getOrigine_geo_pere_deleguation() {
        return this.origine_geo_pere_deleguation;
    }

    public Fiche origine_geo_pere_deleguation(String origine_geo_pere_deleguation) {
        this.setOrigine_geo_pere_deleguation(origine_geo_pere_deleguation);
        return this;
    }

    public void setOrigine_geo_pere_deleguation(String origine_geo_pere_deleguation) {
        this.origine_geo_pere_deleguation = origine_geo_pere_deleguation;
    }

    public String getOrigine_geo_mere_deleguation() {
        return this.origine_geo_mere_deleguation;
    }

    public Fiche origine_geo_mere_deleguation(String origine_geo_mere_deleguation) {
        this.setOrigine_geo_mere_deleguation(origine_geo_mere_deleguation);
        return this;
    }

    public void setOrigine_geo_mere_deleguation(String origine_geo_mere_deleguation) {
        this.origine_geo_mere_deleguation = origine_geo_mere_deleguation;
    }

    public ecouverture getCouverture_sociale() {
        return this.couverture_sociale;
    }

    public Fiche couverture_sociale(ecouverture couverture_sociale) {
        this.setCouverture_sociale(couverture_sociale);
        return this;
    }

    public void setCouverture_sociale(ecouverture couverture_sociale) {
        this.couverture_sociale = couverture_sociale;
    }

    public String getAutre_couverture_sociale() {
        return this.autre_couverture_sociale;
    }

    public Fiche autre_couverture_sociale(String autre_couverture_sociale) {
        this.setAutre_couverture_sociale(autre_couverture_sociale);
        return this;
    }

    public void setAutre_couverture_sociale(String autre_couverture_sociale) {
        this.autre_couverture_sociale = autre_couverture_sociale;
    }

    public eactivite getActivite() {
        return this.activite;
    }

    public Fiche activite(eactivite activite) {
        this.setActivite(activite);
        return this;
    }

    public void setActivite(eactivite activite) {
        this.activite = activite;
    }

    public Boolean getBtravail() {
        return this.btravail;
    }

    public Fiche btravail(Boolean btravail) {
        this.setBtravail(btravail);
        return this;
    }

    public void setBtravail(Boolean btravail) {
        this.btravail = btravail;
    }

    public String getTravail() {
        return this.travail;
    }

    public Fiche travail(String travail) {
        this.setTravail(travail);
        return this;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    public Boolean getScolarise() {
        return this.scolarise;
    }

    public Fiche scolarise(Boolean scolarise) {
        this.setScolarise(scolarise);
        return this;
    }

    public void setScolarise(Boolean scolarise) {
        this.scolarise = scolarise;
    }

    public escolarisetype getType_scolarise() {
        return this.type_scolarise;
    }

    public Fiche type_scolarise(escolarisetype type_scolarise) {
        this.setType_scolarise(type_scolarise);
        return this;
    }

    public void setType_scolarise(escolarisetype type_scolarise) {
        this.type_scolarise = type_scolarise;
    }

    public eniveauscolarisation getNiveau_scolarisation() {
        return this.niveau_scolarisation;
    }

    public Fiche niveau_scolarisation(eniveauscolarisation niveau_scolarisation) {
        this.setNiveau_scolarisation(niveau_scolarisation);
        return this;
    }

    public void setNiveau_scolarisation(eniveauscolarisation niveau_scolarisation) {
        this.niveau_scolarisation = niveau_scolarisation;
    }

    public ecasfamiliaux getCas_familiaux() {
        return this.cas_familiaux;
    }

    public Fiche cas_familiaux(ecasfamiliaux cas_familiaux) {
        this.setCas_familiaux(cas_familiaux);
        return this;
    }

    public void setCas_familiaux(ecasfamiliaux cas_familiaux) {
        this.cas_familiaux = cas_familiaux;
    }

    public Integer getNbcasconfirme() {
        return this.nbcasconfirme;
    }

    public Fiche nbcasconfirme(Integer nbcasconfirme) {
        this.setNbcasconfirme(nbcasconfirme);
        return this;
    }

    public void setNbcasconfirme(Integer nbcasconfirme) {
        this.nbcasconfirme = nbcasconfirme;
    }

    public Integer getNbcassuspectes() {
        return this.nbcassuspectes;
    }

    public Fiche nbcassuspectes(Integer nbcassuspectes) {
        this.setNbcassuspectes(nbcassuspectes);
        return this;
    }

    public void setNbcassuspectes(Integer nbcassuspectes) {
        this.nbcassuspectes = nbcassuspectes;
    }

    public Integer getNbcasdecedes() {
        return this.nbcasdecedes;
    }

    public Fiche nbcasdecedes(Integer nbcasdecedes) {
        this.setNbcasdecedes(nbcasdecedes);
        return this;
    }

    public void setNbcasdecedes(Integer nbcasdecedes) {
        this.nbcasdecedes = nbcasdecedes;
    }

    public Pathologie getPathologie() {
        return this.pathologie;
    }

    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    public Fiche pathologie(Pathologie pathologie) {
        this.setPathologie(pathologie);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fiche)) {
            return false;
        }
        return id != null && id.equals(((Fiche) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fiche{" +
            "id=" + getId() +
            ", datemaj='" + getDatemaj() + "'" +
            ", type_observation='" + getType_observation() + "'" +
            ", identifiant_registre='" + getIdentifiant_registre() + "'" +
            ", date_enregistrement='" + getDate_enregistrement() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", date_naissance='" + getDate_naissance() + "'" +
            ", statut='" + getStatut() + "'" +
            ", date_deces='" + getDate_deces() + "'" +
            ", circonstance_deces='" + getCirconstance_deces() + "'" +
            ", autre_circonstance_deces='" + getAutre_circonstance_deces() + "'" +
            ", lieu_deces='" + getLieu_deces() + "'" +
            ", consanguinite='" + getConsanguinite() + "'" +
            ", origine_geo_pere_gouvernorat='" + getOrigine_geo_pere_gouvernorat() + "'" +
            ", origine_geo_mere_gouvernorat='" + getOrigine_geo_mere_gouvernorat() + "'" +
            ", origine_geo_pere_deleguation='" + getOrigine_geo_pere_deleguation() + "'" +
            ", origine_geo_mere_deleguation='" + getOrigine_geo_mere_deleguation() + "'" +
            ", couverture_sociale='" + getCouverture_sociale() + "'" +
            ", autre_couverture_sociale='" + getAutre_couverture_sociale() + "'" +
            ", activite='" + getActivite() + "'" +
            ", btravail='" + getBtravail() + "'" +
            ", travail='" + getTravail() + "'" +
            ", scolarise='" + getScolarise() + "'" +
            ", type_scolarise='" + getType_scolarise() + "'" +
            ", niveau_scolarisation='" + getNiveau_scolarisation() + "'" +
            ", cas_familiaux='" + getCas_familiaux() + "'" +
            ", nbcasconfirme=" + getNbcasconfirme() +
            ", nbcassuspectes=" + getNbcassuspectes() +
            ", nbcasdecedes=" + getNbcasdecedes() +
            "}";
    }
}
