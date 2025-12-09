package com.example.credit_application_service.application.mapper;

import com.example.credit_application_service.domain.model.AfiliadoModel;
import com.example.credit_application_service.infrastructure.entity.AfiliadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AfiliadoEntityMapper {

    @Mapping(source = "estado", target = "estado", qualifiedByName = "entityToDomainEstado")
    AfiliadoModel toDomain(AfiliadoEntity entity);

    @Mapping(source = "estado", target = "estado", qualifiedByName = "domainToEntityEstado")
    AfiliadoEntity toEntity(AfiliadoModel model);

    @Named("entityToDomainEstado")
    default AfiliadoModel.EstadoAfiliado entityToDomainEstado(AfiliadoEntity.EstadoAfiliadoEntity estado) {
        return estado == null ? null : AfiliadoModel.EstadoAfiliado.valueOf(estado.name());
    }

    @Named("domainToEntityEstado")
    default AfiliadoEntity.EstadoAfiliadoEntity domainToEntityEstado(AfiliadoModel.EstadoAfiliado estado) {
        return estado == null ? null : AfiliadoEntity.EstadoAfiliadoEntity.valueOf(estado.name());
    }
}
