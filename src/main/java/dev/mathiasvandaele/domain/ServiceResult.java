package dev.mathiasvandaele.domain;

import java.util.List;

public interface ServiceResult {
    <T extends Queryable<T>> List<T> toList(Class<T> clazz);
    <T extends Queryable<T>> T toSingle(Class<T> clazz);
}