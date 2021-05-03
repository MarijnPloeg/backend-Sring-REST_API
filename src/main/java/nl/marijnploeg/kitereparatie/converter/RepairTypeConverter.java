package nl.marijnploeg.kitereparatie.converter;

import nl.marijnploeg.kitereparatie.model.Enums.RepairType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RepairTypeConverter implements AttributeConverter<RepairType, String > {
    @Override
    public String convertToDatabaseColumn(RepairType value) {
        if (value.equals(null)) {
            return null;
        }
        return value.getType();
    }

    @Override
    public RepairType convertToEntityAttribute(String value) {
        if (value.equals(null)) {
            return null;
        }
        return RepairType.fromType(value);
    }

}
