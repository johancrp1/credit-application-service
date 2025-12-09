package com.example.credit_application_service.application.mapper;

import com.example.credit_application_service.domain.model.SolicitudCreditoModel;
import com.example.credit_application_service.domain.model.EstadoSolicitudEnum;
import com.example.credit_application_service.infrastructure.entity.SolicitudCreditoEntity;
import com.example.credit_application_service.infrastructure.entity.EstadoSolicitudEnumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EvaluacionEntityMapper.class})
public interface SolicitudEntityMapper {

    @Mapping(source = "estado", target = "estado")
    SolicitudCreditoModel toDomain(SolicitudCreditoEntity entity);

    @Mapping(source = "estado", target = "estado")
    SolicitudCreditoEntity toEntity(SolicitudCreditoModel model);

    // MapStruct reconoce estos métodos automáticamente para los enums
    default EstadoSolicitudEnum map(EstadoSolicitudEnumEntity estado) {
        return estado == null ? null : EstadoSolicitudEnum.valueOf(estado.name());
    }

    default EstadoSolicitudEnumEntity map(EstadoSolicitudEnum estado) {
        return estado == null ? null : EstadoSolicitudEnumEntity.valueOf(estado.name());
    }
}
