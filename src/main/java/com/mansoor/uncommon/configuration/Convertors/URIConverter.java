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

package com.mansoor.uncommon.configuration.Convertors;

import com.mansoor.uncommon.configuration.util.Preconditions;
import com.mansoor.uncommon.configuration.util.Throwables;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Muhammad Ashraf
 * @since 2/11/12
 */
public class URIConverter implements Converter<URI> {
    /**
     * Converts a value to type T
     *
     * @param input value to be converted
     * @return converted value
     */
    public URI convert(final String input) {
        URI result = null;
        if (Preconditions.isNotNull(input)) {
            try {
                result = new URI(input);
            } catch (URISyntaxException e) {
                Throwables.propertyConversionException("conversion failed! ", e);
            }
        }
        return result;
    }

    /**
     * Converts type T to String
     *
     * @param input input to be converted
     * @return String
     */
    public String toString(final URI input) {
        return Preconditions.isNotNull(input) ? input.toString() : null;
    }
}