package zharkov.projects.utils;

import zharkov.projects.model.PublicationVisibility;

import javax.persistence.AttributeConverter;
import java.util.NoSuchElementException;

public class PublicationVisibilityToIntConverter implements AttributeConverter<PublicationVisibility, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PublicationVisibility publicationVisibility) {
        return publicationVisibility.getDatabaseInteger();
    }

    @Override
    public PublicationVisibility convertToEntityAttribute(Integer databaseInteger) {
        for (PublicationVisibility pv : PublicationVisibility.values()) {
            if (pv.getDatabaseInteger() == databaseInteger) {
                return pv;
            }
        }
        throw new NoSuchElementException();
    }
}
