package tn.mdweb.aminoacidopathies.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import tn.mdweb.aminoacidopathies.domain.enumeration.eResultat;
import tn.mdweb.aminoacidopathies.domain.enumeration.efait;
import tn.mdweb.aminoacidopathies.domain.enumeration.elaboratoire;
import tn.mdweb.aminoacidopathies.domain.enumeration.ename;

/**
 * A DTO for the {@link tn.mdweb.aminoacidopathies.domain.Metabolique} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MetaboliqueDTO implements Serializable {

    private Long id;

    private ename name;

    private efait fait;

    private elaboratoire laboratoire;

    private eResultat resultat;

    private FicheDTO fiche;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ename getName() {
        return name;
    }

    public void setName(ename name) {
        this.name = name;
    }

    public efait getFait() {
        return fait;
    }

    public void setFait(efait fait) {
        this.fait = fait;
    }

    public elaboratoire getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(elaboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }

    public eResultat getResultat() {
        return resultat;
    }

    public void setResultat(eResultat resultat) {
        this.resultat = resultat;
    }

    public FicheDTO getFiche() {
        return fiche;
    }

    public void setFiche(FicheDTO fiche) {
        this.fiche = fiche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MetaboliqueDTO)) {
            return false;
        }

        MetaboliqueDTO metaboliqueDTO = (MetaboliqueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, metaboliqueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MetaboliqueDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fait='" + getFait() + "'" +
            ", laboratoire='" + getLaboratoire() + "'" +
            ", resultat='" + getResultat() + "'" +
            ", fiche=" + getFiche() +
            "}";
    }
}
