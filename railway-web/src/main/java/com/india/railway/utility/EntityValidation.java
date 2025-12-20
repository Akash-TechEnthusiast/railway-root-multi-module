package com.india.railway.utility;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
public class EntityValidation {

    public List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        if (obj == null) {
            errors.add("Object must not be null");
            return errors;
        }

        validateObject(obj, errors, obj.getClass().getSimpleName());
        return errors;
    }

    private void validateObject(Object obj, List<String> errors, String path) {

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value;

            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                continue;
            }

            String fieldPath = path + "." + field.getName();

            // ---------------- Null Check ----------------
            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                errors.add(fieldPath + " : " +
                        field.getAnnotation(NotNull.class).message());
                continue;
            }

            // ---------------- String Checks ----------------
            if (value instanceof String str) {

                if (field.isAnnotationPresent(NotBlank.class) && str.isBlank()) {
                    errors.add(fieldPath + " : " +
                            field.getAnnotation(NotBlank.class).message());
                }

                if (field.isAnnotationPresent(Size.class)) {
                    Size size = field.getAnnotation(Size.class);
                    if (str.length() < size.min() || str.length() > size.max()) {
                        errors.add(fieldPath + " : " + size.message());
                    }
                }

                if (field.isAnnotationPresent(Pattern.class)) {
                    Pattern p = field.getAnnotation(Pattern.class);
                    if (!str.matches(p.regexp())) {
                        errors.add(fieldPath + " : " + p.message());
                    }
                }

                if (field.isAnnotationPresent(Email.class)) {
                    if (!str.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                        errors.add(fieldPath + " : Invalid email format");
                    }
                }
            }

            // ---------------- Number Checks ----------------
            if (value instanceof Number num) {

                if (field.isAnnotationPresent(Min.class)) {
                    Min min = field.getAnnotation(Min.class);
                    if (num.longValue() < min.value()) {
                        errors.add(fieldPath + " : " + min.message());
                    }
                }

                if (field.isAnnotationPresent(Max.class)) {
                    Max max = field.getAnnotation(Max.class);
                    if (num.longValue() > max.value()) {
                        errors.add(fieldPath + " : " + max.message());
                    }
                }

                if (field.isAnnotationPresent(DecimalMin.class)) {
                    DecimalMin min = field.getAnnotation(DecimalMin.class);
                    if (new BigDecimal(num.toString())
                            .compareTo(new BigDecimal(min.value())) < 0) {
                        errors.add(fieldPath + " : " + min.message());
                    }
                }

                if (field.isAnnotationPresent(DecimalMax.class)) {
                    DecimalMax max = field.getAnnotation(DecimalMax.class);
                    if (new BigDecimal(num.toString())
                            .compareTo(new BigDecimal(max.value())) > 0) {
                        errors.add(fieldPath + " : " + max.message());
                    }
                }
            }

            // ---------------- Date Checks ----------------
            if (value instanceof LocalDate date) {
                if (field.isAnnotationPresent(Past.class) && !date.isBefore(LocalDate.now())) {
                    errors.add(fieldPath + " : " +
                            field.getAnnotation(Past.class).message());
                }
            }

            // ---------------- Collection Checks ----------------
            if (value instanceof Collection<?> col) {
                if (field.isAnnotationPresent(Size.class)) {
                    Size size = field.getAnnotation(Size.class);
                    if (col.size() < size.min() || col.size() > size.max()) {
                        errors.add(fieldPath + " : " + size.message());
                    }
                }

                // validate nested elements
                for (Object element : col) {
                    validateObject(element, errors, fieldPath);
                }
            }

            // ---------------- Nested Object Validation ----------------
            if (value != null
                    && !isPrimitiveOrWrapper(value.getClass())
                    && !(value instanceof String)
                    && !(value instanceof Number)
                    && !(value instanceof LocalDate)
                    && !(value instanceof Collection)) {

                validateObject(value, errors, fieldPath);
            }
        }
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive()
                || type == Boolean.class
                || type == Byte.class
                || type == Character.class
                || type == Short.class
                || type == Integer.class
                || type == Long.class
                || type == Float.class
                || type == Double.class;
    }
}
