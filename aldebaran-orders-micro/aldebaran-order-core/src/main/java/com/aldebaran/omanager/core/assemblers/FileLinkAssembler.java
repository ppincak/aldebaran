package com.aldebaran.omanager.core.assemblers;

import com.aldebaran.omanager.core.entities.FileLink;
import com.aldebaran.omanager.core.model.FileLinkResponse;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;


@Component
public class FileLinkAssembler extends AbstractOrikaAssembler {

    public String assembleImageUrl(FileLink fileLink) {
        return null;
    }

    @Override
    public void register(MapperFactory factory) {
        factory
            .classMap(FileLink.class, FileLinkResponse.class)
            .byDefault()
            .register();
    }
}