package com.aldebaran.order.core.assemblers;

import ma.glasnost.orika.MapperFacade;


public abstract class AbstractOrikaAssembler implements OrikaAssembler {

    protected MapperFacade mapperFacade;

    @Override
    public void assign(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }
}