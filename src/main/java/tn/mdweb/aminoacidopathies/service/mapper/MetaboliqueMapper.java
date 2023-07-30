package tn.mdweb.aminoacidopathies.service.mapper;

import org.mapstruct.*;
import tn.mdweb.aminoacidopathies.domain.Fiche;
import tn.mdweb.aminoacidopathies.domain.Metabolique;
import tn.mdweb.aminoacidopathies.service.dto.FicheDTO;
import tn.mdweb.aminoacidopathies.service.dto.MetaboliqueDTO;

/**
 * Mapper for the entity {@link Metabolique} and its DTO {@link MetaboliqueDTO}.
 */
@Mapper(componentModel = "spring")
public interface MetaboliqueMapper extends EntityMapper<MetaboliqueDTO, Metabolique> {
    @Mapping(target = "fiche", source = "fiche", qualifiedByName = "ficheId")
    MetaboliqueDTO toDto(Metabolique s);

    @Named("ficheId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FicheDTO toDtoFicheId(Fiche fiche);
}
