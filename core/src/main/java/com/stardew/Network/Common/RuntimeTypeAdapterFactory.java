package com.stardew.Network.Common;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Type adapter that maintains type information for a class hierarchy.
 * Useful for serializing/deserializing abstract classes or interfaces.
 */
public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final String typeFieldName;
    private final Map<String, Class<?>> labelToSubtype = new LinkedHashMap<>();
    private final Map<Class<?>, String> subtypeToLabel = new LinkedHashMap<>();
    private final boolean maintainType;

    private RuntimeTypeAdapterFactory(Class<?> baseType, String typeFieldName, boolean maintainType) {
        if (typeFieldName == null || baseType == null) {
            throw new NullPointerException();
        }
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
        this.maintainType = maintainType;
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName, boolean maintainType) {
        return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName, maintainType);
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName, false);
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType) {
        return new RuntimeTypeAdapterFactory<>(baseType, "type", false);
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type, String label) {
        if (type == null || label == null) {
            throw new NullPointerException();
        }
        if (subtypeToLabel.containsKey(type) || labelToSubtype.containsKey(label)) {
            throw new IllegalArgumentException("types and labels must be unique");
        }
        labelToSubtype.put(label, type);
        subtypeToLabel.put(type, label);
        return this;
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type) {
        return registerSubtype(type, type.getSimpleName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (!baseType.isAssignableFrom(type.getRawType())) {
            return null;
        }

        final Map<String, TypeAdapter<?>> labelToDelegate = new LinkedHashMap<>();
        final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate = new LinkedHashMap<>();
        for (Map.Entry<String, Class<?>> entry : labelToSubtype.entrySet()) {
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get(entry.getValue()));
            labelToDelegate.put(entry.getKey(), delegate);
            subtypeToDelegate.put(entry.getValue(), delegate);
        }

        return (TypeAdapter<R>) new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                Class<?> srcType = value.getClass();
                String label = subtypeToLabel.get(srcType);
                if (label == null) {
                    throw new JsonParseException("Cannot serialize " + srcType.getName() + "; did you forget to register a subtype?");
                }
                TypeAdapter<T> delegate = (TypeAdapter<T>) subtypeToDelegate.get(srcType);
                JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();

                if (maintainType) {
                    gson.toJson(jsonObject, out);
                    return;
                }

                JsonObject clone = new JsonObject();
                clone.add(typeFieldName, new JsonPrimitive(label));
                for (Map.Entry<String, JsonElement> e : jsonObject.entrySet()) {
                    clone.add(e.getKey(), e.getValue());
                }
                gson.toJson(clone, out);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = JsonParser.parseReader(in);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonElement labelJsonElement = maintainType ? jsonObject.get(typeFieldName) : jsonObject.remove(typeFieldName);

                if (labelJsonElement == null) {
                    throw new JsonParseException("Missing type field \"" + typeFieldName + "\"");
                }
                String label = labelJsonElement.getAsString();
                @SuppressWarnings("unchecked")
                TypeAdapter<T> delegate = (TypeAdapter<T>) labelToDelegate.get(label);
                if (delegate == null) {
                    throw new JsonParseException("No registered subtype found for label: " + label);
                }
                return delegate.fromJsonTree(jsonObject);
            }
        };
    }
}
