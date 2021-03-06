/*
 * Copyright 2012. Muhammad M. Ashraf
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mansoor.uncommon.configuration.util;

import java.util.Collection;

/**
 * @author Muhammad Ashraf
 * @since 2/9/12
 */
public final class Preconditions {
    private Preconditions() {
    }

    public static boolean isNull(final Object reference) {
        return reference == null;
    }

    public static boolean isNotNull(final Object reference) {
        return !isNull(reference);
    }

    public static void checkNull(final Object reference, final String msg) {
        if (isNull(reference)) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void checkArgument(final boolean condition, final String msg) {
        if (!condition) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void checkBlank(final String ref, final String msg) {
        if (ref == null || ref.equals("") || ref.equals(" ")) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static boolean isEmpty(final Collection ref) {
        return ref == null || ref.isEmpty();
    }

    public static boolean isNotEmpty(final Collection ref) {
        return !isEmpty(ref);
    }
}
