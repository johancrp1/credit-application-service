package com.example.credit_application_service.application.mapper;

import com.example.credit_application_service.domain.model.SolicitudCreditoModel;
import com.example.credit_application_service.domain.model.EstadoSolicitudEnum;
import com.example.credit_application_service.infrastructure.entity.SolicitudCreditoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {EvaluacionEntityMapper.class})
public interface SolicitudEntityMapper {

    @Mapping(source = "estado", target = "estado", qualifiedByName = "entityToDomainEstado")
    SolicitudCreditoModel toDomain(SolicitudCreditoEntity entity);

    @Mapping(source = "estado", target = "estado", qualifiedByName = "domainToEntityEstado")
    SolicitudCreditoEntity toEntity(SolicitudCreditoModel model);

    @Named("entityToDomainEstado")
    default EstadoSolicitudEnum entityToDomainEstado(SolicitudCreditoEntity.EstadoSolicitudEnumEntity estado) {
        return estado == null ? null : EstadoSolicitudEnum.valueOf(estado.name());
    }

    @Named("domainToEntityEstado")
    default SolicitudCreditoEntity.EstadoSolicitudEnumEntity domainToEntityEstado(EstadoSolicitudEnum estado) {
        return estado == null ? null : SolicitudCreditoEntity.EstadoSolicitudEnumEntity.valueOf(estado.name());
    }
}
