package com.grade.manage.service.util;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * @author Studios TKOH!
 */
public final class ServiceUtils {

    private ServiceUtils() {
    }

    public static <V> void patchIfNotNull(V newValue, Consumer<V> setter) {
        if (newValue != null) {
            setter.accept(newValue);
        }
    }

    public static <T> T orElseNotFound(Optional<T> opt, String msg) {
        return opt.orElseThrow(() -> new EntityNotFoundException(msg));
    }
}
