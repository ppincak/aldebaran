package com.aldebaran.omanager.core.assemblers;

import com.aldebaran.data.domain.Price;
import com.aldebaran.data.domain.Timestamps;
import com.aldebaran.omanager.core.model.PriceModel;
import com.aldebaran.omanager.core.model.TimestampsModel;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;


@Component
public class GeneralAssembler extends AbstractOrikaAssembler {

    @Override
    public void register(MapperFactory factory) {
        factory
            .classMap(Timestamps.class, TimestampsModel.class)
            .byDefault()
            .register();

        factory
            .classMap(Price.class, PriceModel.class)
            .byDefault()
            .register();
    }
}