package com.yahacode.hiddenblade.app.doc.excel.annotation;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * Excel cell style
 *
 * @author zengyongli
 * @since 2022/05/18
 */
public @interface Style {

    HorizontalAlignment alignment() default HorizontalAlignment.CENTER;

    boolean bold() default false;

    short fontSize() default 11;

    short fillColor() default -1;

    BorderStyle border() default BorderStyle.NONE;
}
