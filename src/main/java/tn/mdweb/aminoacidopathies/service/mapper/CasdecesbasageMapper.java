package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Casdecesbasage;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.service.dto.CasdecesbasageDTO;
import tn.mdweb.aminoacidopathies.service.dto.FicheDTO;

/**
 * Mapper for the entity {@link Casdecesbasage} and its DTO {@link CasdecesbasageDTO}.
 */
@Mapper(componentModel = "spring")
public interface CasdecesbasageMapper extends EntityMapper<CasdecesbasageDTO, Casdecesbasage> {
    @Mapping(target = "fiche", source = "fiche", qualifiedByName = "ficheId")
    CasdecesbasageDTO toDto(Casdecesbasage s);

    @Named("ficheId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FicheDTO toDtoFicheId(Fiche fiche);
}
