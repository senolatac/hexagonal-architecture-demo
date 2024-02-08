package com.nar.hexademo;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by AhmetK on 26.11.2018.
 */
public final class TestUtils
{
    public static String getResourceContent(String path) throws IOException
    {
        return IOUtils.toString(TestUtils.class.getClassLoader().getResourceAsStream(path),
                StandardCharsets.UTF_8);
    }
}
