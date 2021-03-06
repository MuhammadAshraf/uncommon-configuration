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

import com.mansoor.uncommon.configuration.Convertors.Converter;
import com.mansoor.uncommon.configuration.TestUtil;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * @author Muhammad Ashraf
 * @since 3/11/12
 */
public class X509CertConverterTest {

    private Converter<X509Wrapper> converter;

    @Before
    public void setUp() throws Exception {
        final KeyConfig config = TestUtil.createX509KeyConfig();
        converter = new X509CertConverter(config);
    }

    @Test
    public void testSymmetricalEncryption() throws Exception {
        final String plainTextPassword = "super secret password";
        final String encryptedPassword = converter.toString(new X509Wrapper(plainTextPassword));
        assertThat(encryptedPassword, is(not(equalTo(plainTextPassword))));
        final String decryptedPassword = converter.convert(encryptedPassword).getPlainText();
        assertThat(plainTextPassword, is(equalTo(decryptedPassword)));

    }
}
