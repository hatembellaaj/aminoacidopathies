package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Casconfirme;
import tn.mdweb.aminoacidopathies.domain.Cassuspecte;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.service.dto.CasconfirmeDTO;
import tn.mdweb.aminoacidopathies.service.dto.CassuspecteDTO;
import tn.mdweb.aminoacidopathies.service.dto.FicheDTO;

/**
 * Mapper for the entity {@link Cassuspecte} and its DTO {@link CassuspecteDTO}.
 */
@Mapper(componentModel = "spring")
public interface CassuspecteMapper extends EntityMapper<CassuspecteDTO, Cassuspecte> {
    @Mapping(target = "fiche", source = "fiche", qualifiedByName = "ficheId")
    CasconfirmeDTO toDto(Casconfirme s);

    @Named("ficheId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FicheDTO toDtoFicheId(Fiche fiche);
}
