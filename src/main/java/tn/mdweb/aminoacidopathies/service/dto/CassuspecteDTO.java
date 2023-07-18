package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Cassuspecte} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CassuspecteDTO implements Serializable {

    private Long id;

    private elienparente lienparente;

    private String lienparenteautre;

    private Boolean signes_neurologiques;

    private Boolean troubles_de_la_conscience;

    private Boolean retard_psychomoteur;

    private Boolean retard_mental;

    private Boolean signes_du_spectre_autistique;

    private Boolean epilepsie;

    private Boolean crise_pseudoporphyrique;

    private String autres_signes_neurologiques;

    private Boolean signes_hepatiques;

    private Boolean ictere;

    private Boolean ballonnement;

    private Boolean syndrome_hemorragique;

    private String autres_signes_hepatiques;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public elienparente getLienparente() {
        return lienparente;
    }

    public void setLienparente(elienparente lienparente) {
        this.lienparente = lienparente;
    }

    public String getLienparenteautre() {
        return lienparenteautre;
    }

    public void setLienparenteautre(String lienparenteautre) {
        this.lienparenteautre = lienparenteautre;
    }

    public Boolean getSignes_neurologiques() {
        return signes_neurologiques;
    }

    public void setSignes_neurologiques(Boolean signes_neurologiques) {
        this.signes_neurologiques = signes_neurologiques;
    }

    public Boolean getTroubles_de_la_conscience() {
        return troubles_de_la_conscience;
    }

    public void setTroubles_de_la_conscience(Boolean troubles_de_la_conscience) {
        this.troubles_de_la_conscience = troubles_de_la_conscience;
    }

    public Boolean getRetard_psychomoteur() {
        return retard_psychomoteur;
    }

    public void setRetard_psychomoteur(Boolean retard_psychomoteur) {
        this.retard_psychomoteur = retard_psychomoteur;
    }

    public Boolean getRetard_mental() {
        return retard_mental;
    }

    public void setRetard_mental(Boolean retard_mental) {
        this.retard_mental = retard_mental;
    }

    public Boolean getSignes_du_spectre_autistique() {
        return signes_du_spectre_autistique;
    }

    public void setSignes_du_spectre_autistique(Boolean signes_du_spectre_autistique) {
        this.signes_du_spectre_autistique = signes_du_spectre_autistique;
    }

    public Boolean getEpilepsie() {
        return epilepsie;
    }

    public void setEpilepsie(Boolean epilepsie) {
        this.epilepsie = epilepsie;
    }

    public Boolean getCrise_pseudoporphyrique() {
        return crise_pseudoporphyrique;
    }

    public void setCrise_pseudoporphyrique(Boolean crise_pseudoporphyrique) {
        this.crise_pseudoporphyrique = crise_pseudoporphyrique;
    }

    public String getAutres_signes_neurologiques() {
        return autres_signes_neurologiques;
    }

    public void setAutres_signes_neurologiques(String autres_signes_neurologiques) {
        this.autres_signes_neurologiques = autres_signes_neurologiques;
    }

    public Boolean getSignes_hepatiques() {
        return signes_hepatiques;
    }

    public void setSignes_hepatiques(Boolean signes_hepatiques) {
        this.signes_hepatiques = signes_hepatiques;
    }

    public Boolean getIctere() {
        return ictere;
    }

    public void setIctere(Boolean ictere) {
        this.ictere = ictere;
    }

    public Boolean getBallonnement() {
        return ballonnement;
    }

    public void setBallonnement(Boolean ballonnement) {
        this.ballonnement = ballonnement;
    }

    public Boolean getSyndrome_hemorragique() {
        return syndrome_hemorragique;
    }

    public void setSyndrome_hemorragique(Boolean syndrome_hemorragique) {
        this.syndrome_hemorragique = syndrome_hemorragique;
    }

    public String getAutres_signes_hepatiques() {
        return autres_signes_hepatiques;
    }

    public void setAutres_signes_hepatiques(String autres_signes_hepatiques) {
        this.autres_signes_hepatiques = autres_signes_hepatiques;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CassuspecteDTO)) {
            return false;
        }

        CassuspecteDTO cassuspecteDTO = (CassuspecteDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cassuspecteDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CassuspecteDTO{" +
            "id=" + getId() +
            ", lienparente='" + getLienparente() + "'" +
            ", lienparenteautre='" + getLienparenteautre() + "'" +
            ", signes_neurologiques='" + getSignes_neurologiques() + "'" +
            ", troubles_de_la_conscience='" + getTroubles_de_la_conscience() + "'" +
            ", retard_psychomoteur='" + getRetard_psychomoteur() + "'" +
            ", retard_mental='" + getRetard_mental() + "'" +
            ", signes_du_spectre_autistique='" + getSignes_du_spectre_autistique() + "'" +
            ", epilepsie='" + getEpilepsie() + "'" +
            ", crise_pseudoporphyrique='" + getCrise_pseudoporphyrique() + "'" +
            ", autres_signes_neurologiques='" + getAutres_signes_neurologiques() + "'" +
            ", signes_hepatiques='" + getSignes_hepatiques() + "'" +
            ", ictere='" + getIctere() + "'" +
            ", ballonnement='" + getBallonnement() + "'" +
            ", syndrome_hemorragique='" + getSyndrome_hemorragique() + "'" +
            ", autres_signes_hepatiques='" + getAutres_signes_hepatiques() + "'" +
            "}";
    }
}