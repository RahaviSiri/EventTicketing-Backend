package eventticketing.eventease_backend.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter(autoApply = false)
// This marks the class as a JPA converter. autoApply = false means it only applies where explicitly mentioned using @Convert(...).
public class MapToJsonConverter implements AttributeConverter<Map<String, Object>, String> {
    // Converts the Java Map to a JSON string using Jackson's ObjectMapper.
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Serialize the Map to a JSON string before saving it to the DB.
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            if (attribute == null) return null;
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting map to JSON string.", e);
        }
    }

    // Deserialize the JSON string from the DB back into a Map when reading the entity.
    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null) return null;
            return objectMapper.readValue(dbData, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error reading JSON string.", e);
        }
    }
}
