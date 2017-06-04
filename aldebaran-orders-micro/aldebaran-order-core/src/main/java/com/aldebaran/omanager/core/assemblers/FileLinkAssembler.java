package com.aldebaran.omanager.core.assemblers;

import com.aldebaran.omanager.core.entities.FileLink;
import com.aldebaran.omanager.core.model.FileLinkResponse;
import com.aldebaran.omanager.core.model.ImageModel;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FileLinkAssembler extends AbstractOrikaAssembler {

    public String assembleImageUrl(FileLink fileLink) {
        return null;
    }

    public List<FileLinkResponse> toResponse(List<FileLink> fileLinks) {
        return mapperFacade.mapAsList(fileLinks, FileLinkResponse.class);
    }

    @Override
    public void register(MapperFactory factory) {
        factory
            .classMap(FileLink.class, FileLinkResponse.class)
            .byDefault()
            .register();

        factory
            .classMap(ImageModel.class, FileLink.class)
            .byDefault()
            .register();
    }
}