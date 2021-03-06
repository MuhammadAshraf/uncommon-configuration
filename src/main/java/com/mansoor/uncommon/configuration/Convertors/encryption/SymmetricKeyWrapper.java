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

package com.mansoor.uncommon.configuration.Convertors.encryption;

/**
 * Wrapper used by {@link SymmetricKeyConverter} to encrypt and decrypt value
 * @author Muhammad Ashraf
 * @since 0.1
 */
public class SymmetricKeyWrapper {
    private final String plainText;

    public SymmetricKeyWrapper(final String encryptedText) {
        this.plainText = encryptedText;
    }

    public String getPlainText() {
        return plainText;
    }

    public String toString() {
        return plainText;
    }
}
