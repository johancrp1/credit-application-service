package com.example.credit_application_service.application.mapper;

import com.example.credit_application_service.domain.model.EvaluacionRiesgoModel;
import com.example.credit_application_service.infrastructure.entity.EvaluacionRiesgoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EvaluacionEntityMapper {

    EvaluacionRiesgoModel toDomain(EvaluacionRiesgoEntity entity);

    EvaluacionRiesgoEntity toEntity(EvaluacionRiesgoModel model);
}
