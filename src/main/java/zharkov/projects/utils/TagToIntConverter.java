package zharkov.projects.utils;

import zharkov.projects.model.entities.Tag;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.NoSuchElementException;

@Converter
public class TagToIntConverter implements AttributeConverter<Tag, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Tag tag) {
        return tag.getDatabaseId();
    }

    @Override
    public Tag convertToEntityAttribute(Integer s) {
        for (Tag tag : Tag.values()) {
            if (tag.getDatabaseId() == s) {
                return tag;
            }
        }
        throw new NoSuchElementException();
    }
}
