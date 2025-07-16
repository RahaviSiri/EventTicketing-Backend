// package eventticketing.eventease_backend.converters;

// import jakarta.persistence.AttributeConverter;
// import jakarta.persistence.Converter;
// import org.postgresql.util.PGobject;

// @Converter(autoApply = true)
// public class JsonbConverter implements AttributeConverter<String, Object> {

//     @Override
//     public Object convertToDatabaseColumn(String attribute) {
//         if (attribute == null) return null;
//         PGobject pgObject = new PGobject();
//         try {
//             pgObject.setType("jsonb");
//             pgObject.setValue(attribute);
//         } catch (Exception e) {
//             throw new IllegalArgumentException("Failed to convert String to jsonb", e);
//         }
//         return pgObject;
//     }

//     @Override
//     public String convertToEntityAttribute(Object dbData) {
//         if (dbData == null) return null;
//         return dbData.toString();
//     }
// }
