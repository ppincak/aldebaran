package com.aldebaran.omanager.core.assemblers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OrikaConfiguration {

    @Autowired
    private List<OrikaAssembler> assemblers;

    @Bean
    public MapperFactory mapperFactory() {
        return new DefaultMapperFactory.Builder().build();
    }

    // TODO move
    @Bean
    public ConverterFactory converterFactory(MapperFactory mapperFactory) {
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new OrikaInstantConverter());
        return converterFactory;
    }

    @Bean
    public MapperFacade mapperFacade(MapperFactory mapperFactory) {
        for(OrikaAssembler assembler: assemblers) {
            assembler.register(mapperFactory);
        }
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        for(OrikaAssembler assembler: assemblers) {
            assembler.assign(mapperFacade);
        }
        return mapperFacade;
    }
}