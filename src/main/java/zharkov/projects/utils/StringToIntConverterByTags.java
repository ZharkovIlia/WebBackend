package zharkov.projects.utils;

import zharkov.projects.model.Tags;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringToIntConverterByTags implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String name) {
        return Tags.getNameToDatabaseInt().get(name);
    }

    @Override
    public String convertToEntityAttribute(Integer databaseInt) {
        return Tags.getDatabaseIntToName().get(databaseInt);
    }
}
