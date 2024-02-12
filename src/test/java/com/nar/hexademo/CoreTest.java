package com.nar.hexademo;

import com.sha.shacore.util.NumberUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoreTest {

    @Test
    void test_multiply() {
        //core dependency
        int r = NumberUtils.multiply(4, 5);

        assertThat(r).isEqualTo(20);
    }
}
