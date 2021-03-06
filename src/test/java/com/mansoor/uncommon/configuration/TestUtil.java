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

import com.mansoor.uncommon.configuration.Convertors.encryption.KeyConfig;
import com.mansoor.uncommon.configuration.util.EncryptionUtil;

import java.security.KeyStore;

/**
 * @author Muhammad Ashraf
 * @since 2/12/12
 */
public final class TestUtil {
    private TestUtil() {
    }

    public static Configuration getPropertyConfiguration(final String location) {
        final PropertyConfiguration configuration = new PropertyConfiguration();
        configuration.load(TestUtil.class.getResource(location).getPath());
        return configuration;
    }

    public static Configuration getYamlConfiguration(final String location) {
        final Configuration configuration = new YamlConfiguration();
        configuration.load(TestUtil.class.getResource(location).getPath());
        return configuration;
    }

    public static Configuration getJsonConfiguration(final String location) {
        final Configuration configuration = new JsonConfiguration();
        configuration.load(TestUtil.class.getResource(location).getPath());
        return configuration;
    }

    public static KeyConfig createSymmetricKeyConfig() {
        return new KeyConfig.Builder()
                .keyAlias("secret")
                .keyPassword("123456789".toCharArray())
                .keyStorePassword("password".toCharArray())
                .keyStoreType(EncryptionUtil.JCEKS)
                .keyStorePath(TestUtil.class.getResource("/symmetrickeyStore.jceks").getPath())
                .createKeyCofig();
    }

    public static KeyConfig createX509KeyConfig() {
        return new KeyConfig.Builder()
                .keyAlias("uncommon-key")
                .keyPassword("password".toCharArray())
                .keyStorePassword("password".toCharArray())
                .keyStorePath(TestUtil.class.getResource("/uncommon-config-keystore.keystore").getPath())
                .keyStoreType(EncryptionUtil.JKS)
                .createKeyCofig();

    }

    public static KeyConfig createKeyConfig(final KeyStore keyStore){
        return new KeyConfig.Builder()
                .keyStore(keyStore)
                .keyAlias("uncommon-key")
                .keyPassword("password".toCharArray())
                .createKeyCofig();
    }
}
