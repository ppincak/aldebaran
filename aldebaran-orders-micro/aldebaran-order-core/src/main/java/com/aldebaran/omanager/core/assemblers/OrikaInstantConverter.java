package com.aldebaran.omanager.core.assemblers;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class OrikaInstantConverter extends BidirectionalConverter<Instant, LocalDateTime> {

    @Override
    public LocalDateTime convertTo(Instant source,
                                   Type<LocalDateTime> destinationType,
                                   MappingContext mappingContext) {
        return LocalDateTime.ofInstant(source, ZoneOffset.UTC);
    }

    @Override
    public Instant convertFrom(LocalDateTime source,
                               Type<Instant> destinationType,
                               MappingContext mappingContext) {
        return source.toInstant(ZoneOffset.UTC);
    }
}
