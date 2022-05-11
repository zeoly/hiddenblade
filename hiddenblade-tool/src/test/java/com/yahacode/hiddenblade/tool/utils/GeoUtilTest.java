package com.yahacode.hiddenblade.tool.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeoUtilTest {

    @Test
    void bd09ToGcj02() {
        double[] doubles = GeoUtil.bd09ToGcj02(116.412681, 39.930647);
        Assertions.assertNotNull(doubles);
    }

    @Test
    void distance() {
        double distance = GeoUtil.distance(116.406351, 39.924285, 116.825419,39.943758);
        Assertions.assertNotNull(distance);
    }

    @Test
    void inChina() {
        Assertions.assertTrue(GeoUtil.inChina(73.549032,39.376793));
        Assertions.assertFalse(GeoUtil.inChina(0.0, 0.0));
    }
}