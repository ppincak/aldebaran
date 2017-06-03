package com.aldebaran.omanager.core.assemblers;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;


public interface OrikaAssembler {

    void register(MapperFactory factory);

    void assign(MapperFacade facade);
}