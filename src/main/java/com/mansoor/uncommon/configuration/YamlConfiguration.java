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

import com.mansoor.uncommon.configuration.Convertors.Converter;
import com.mansoor.uncommon.configuration.Convertors.ConverterRegistry;
import com.mansoor.uncommon.configuration.Convertors.DefaultConverterRegistry;
import com.mansoor.uncommon.configuration.exceptions.PropertyConversionException;
import com.mansoor.uncommon.configuration.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Muhammad Ashraf
 * @since 2/25/12
 */
public class YamlConfiguration extends BaseConfiguration implements Configuration {
    private final Map<String, Object> properties;
    private static final Logger log = LoggerFactory.getLogger(YamlConfiguration.class);

    protected YamlConfiguration(final ConverterRegistry converterRegistry) {
        super(converterRegistry);
        properties = new HashMap<String, Object>();
    }

    protected YamlConfiguration() {
        super(new DefaultConverterRegistry());
        properties = new HashMap<String, Object>();
    }

    public YamlConfiguration(final ConverterRegistry converterRegistry, final long pollingRate, final TimeUnit timeUnit) {
        super(converterRegistry);
        Preconditions.checkArgument(pollingRate > 0, "Polling rate must be greater than 0");
        Preconditions.checkNull(timeUnit, "No Time Unit Specified");
        properties = new HashMap<String, Object>();
        executorService.scheduleAtFixedRate(new FilePoller(), pollingRate, pollingRate, timeUnit);
    }

    public YamlConfiguration(final long pollingRate, final TimeUnit timeUnit) {
        super(new DefaultConverterRegistry());
        Preconditions.checkArgument(pollingRate > 0, "Polling rate must be greater than 0");
        Preconditions.checkNull(timeUnit, "No Time Unit Specified");
        properties = new HashMap<String, Object>();
        executorService.scheduleAtFixedRate(new FilePoller(), pollingRate, pollingRate, timeUnit);
    }

    public <E> E get(final Class<E> type, final String key) {
        final Converter<E> converter = converterRegistry.getConverter(type);
        return getAndConvert(converter, key);
    }

    private <E> E getAndConvert(final Converter<E> converter, final String key) {
        try {
            return converter.convert(getValueAsString(properties.get(key)));
        } catch (Exception e) {
            throw new PropertyConversionException("conversion failed", e);
        }
    }

    private String getValueAsString(final Object value) {
        String result = null;
        if (Preconditions.isNotNull(value)) {
            if (String.class.isAssignableFrom(value.getClass())) {
                result = (String) value;
            } else {
                result = value.toString();
            }
        }
        return result;

    }

    public <E> List<E> getList(final Class<E> type, final String key) {
        final String property = getValueAsString(properties.get(key));
        return super.getList(type, property);
    }


    @SuppressWarnings("unchecked")
    public <E> void set(final String key, final E input) {
        if (Preconditions.isNotNull(input)) {
            final Converter<E> converter = converterRegistry.getConverter((Class<E>) input.getClass());
            setProperty(key, converter.toString(input));
        }
    }

    private void setProperty(final String key, final String value) {
        lock.lock();
        try {
            properties.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    public <E> void setList(final String key, final List<E> input) {

    }

    public <E> void setList(final String key, final E... input) {

    }

    @SuppressWarnings("unchecked")
    public void load(final File file) {
        Preconditions.checkNull(file, "File is null");
        final Yaml yaml = new Yaml();
        config = file;
        lastModified = file.lastModified();
        lock.lock();
        try {
            final Object data = yaml.load(new FileInputStream(file));
            properties.putAll((Map<String, Object>) data);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } finally {
            lock.unlock();
        }

    }

    public void load(final String path) {
        Preconditions.checkBlank(path, "file path is null or empty");
        load(new File(path));
    }

    public ConverterRegistry getConverterRegistry() {
        return converterRegistry;
    }

    public void reload() {

    }

    public void stopPolling() {

    }

    /**
     * Saves the configuration to the given path
     *
     * @param path path where the file will be saved
     * @return file where the config is saved
     */
    public File save(final String path) {
        return null;
    }

    public void clear() {

    }

    class FilePoller implements Runnable {
        public void run() {
            log.info("Polling File");
            final File temp = new File(config.getAbsolutePath());
            if (temp.exists() && temp.lastModified() > lastModified) {
                lastModified = temp.lastModified();
                log.info("Reload Required");
                reload();
            } else {
                log.info("Not reloading file as no change has been detected since last load");
            }
        }
    }
}
