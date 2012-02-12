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

import com.mansoor.uncommon.configuration.Configuration;
import com.mansoor.uncommon.configuration.Exceptions.PropertyConversionException;
import com.mansoor.uncommon.configuration.PropertyConfiguration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * @author Muhammad Ashraf
 * @since 2/11/12
 */
public class IntegerConverterTest {

    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        configuration = new PropertyConfiguration();
        configuration.load(this.getClass().getResourceAsStream("/testProp.properties"));
    }

    @Test
    public void testGetInteger() throws Exception {
        final Integer one = configuration.get(Integer.class, "one");
        assertNotNull("Null value returned!", one);
        final Integer expected = 1;
        assertEquals("Incorrect value returned", expected, one);
    }


    @org.junit.Test(expected = PropertyConversionException.class)
    public void testIntegerConversionException() throws Exception {
        configuration.get(Integer.class, "integerException");
        fail("expected conversion exception");
    }
}
