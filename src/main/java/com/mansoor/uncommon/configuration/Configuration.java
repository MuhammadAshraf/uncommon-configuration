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

package com.mansoor.uncommon.configuration;

import com.mansoor.uncommon.configuration.Convertors.ConverterRegistry;

import java.io.File;
import java.io.InputStream;

/**
 * @author mansoor
 * @since 2/9/12
 */
public interface Configuration {
    <E> E get(Class<E> type, String key);

    void load(File file);

    void load(InputStream inputStream);

    ConverterRegistry getConverterRegistry();
}
