package com.aldebaran.order.core.assemblers;

import com.aldebaran.order.core.entities.FileLink;
import com.aldebaran.order.core.model.FileLinkResponse;
import com.aldebaran.order.core.model.ImageModel;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FileLinkAssembler extends AbstractOrikaAssembler {

    public String assembleImageUrl(FileLink fileLink) {
        if(fileLink == null) {
            return null;
        }
        return "/files/" + fileLink.getId();
    }

    public List<FileLinkResponse> toResponse(List<FileLink> fileLinks) {
        return mapperFacade.mapAsList(fileLinks, FileLinkResponse.class);
    }

    @Override
    public void register(MapperFactory factory) {
        factory
            .classMap(FileLink.class, FileLinkResponse.class)
            .customize(new CustomMapper<FileLink, FileLinkResponse>() {
                @Override
                public void mapAtoB(FileLink fileLink,
                                    FileLinkResponse fileLinkResponse,
                                    MappingContext context) {
                    fileLinkResponse.setUrl(assembleImageUrl(fileLink));
                }
            })
            .byDefault()
            .register();

        factory
            .classMap(ImageModel.class, FileLink.class)
            .byDefault()
            .register();
    }
}