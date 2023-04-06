package com.xyz.roemahduren.util;

import com.xyz.roemahduren.domain.annotation.validation.Email;
import com.xyz.roemahduren.domain.annotation.validation.NotBlank;
import com.xyz.roemahduren.domain.annotation.validation.NotNull;
import com.xyz.roemahduren.domain.annotation.validation.Size;
import com.xyz.roemahduren.domain.model.response.ErrorValidationModel;
import com.xyz.roemahduren.exception.ValidationException;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String EMAIL_REGEX_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public static void validate(Object obj) throws ValidationException {
        Class<?> clazz = obj.getClass();
        Set<ErrorValidationModel> validationModels = new HashSet<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Set<String> errorMessages = new HashSet<>();

            if (field.isAnnotationPresent(NotBlank.class)) {
                try {
                    Object value = field.get(obj);

                    if (value.equals("") || value.toString().isEmpty()) {
                        NotBlank annotation = field.getAnnotation(NotBlank.class);
                        errorMessages.add(annotation.message());
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    Object value = field.get(obj);

                    if (value == null || value.toString().isEmpty()) {
                        NotNull annotation = field.getAnnotation(NotNull.class);
                        errorMessages.add(annotation.message());
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Email.class)) {
                try {
                    Object value = field.get(obj);
                    Pattern compile = Pattern.compile(EMAIL_REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = compile.matcher(value.toString());

                    if (!matcher.matches()) {
                        Email annotation = field.getAnnotation(Email.class);
                        errorMessages.add(annotation.message());
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(Size.class)) {
                try {
                    Object value = field.get(obj);
                    Size annotation = field.getAnnotation(Size.class);

                    if (value.toString().length() < annotation.min() || value.toString().length() > annotation.max()) {
                        errorMessages.add(annotation.message());
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

            if (!errorMessages.isEmpty()) {
                ErrorValidationModel errorValidationModel = new ErrorValidationModel(field.getName(), errorMessages);
                validationModels.add(errorValidationModel);
            }
        }

        if (validationModels.size() != 0) {
            throw new ValidationException(validationModels);
        }
    }

    public static String getMessage(Set<String> messages) {
        return String.join(" - ", messages);
    }

}
