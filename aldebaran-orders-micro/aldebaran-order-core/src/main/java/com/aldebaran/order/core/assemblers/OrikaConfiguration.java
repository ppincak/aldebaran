package com.aldebaran.order.core.assemblers;

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
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(new OrikaInstantConverter());
        return mapperFactory;
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