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

    /**
     * cell alignment
     *
     * @return cell alignment
     */
    HorizontalAlignment alignment() default HorizontalAlignment.CENTER;

    /**
     * bold style
     *
     * @return whether cell is bold
     */
    boolean bold() default false;

    /**
     * cell font size
     *
     * @return font size
     */
    short fontSize() default 11;

    /**
     * cell foreground color, default none
     *
     * @return foreground color
     */
    short fillColor() default -1;

    /**
     * cell border style, default none
     *
     * @return border style
     */
    BorderStyle border() default BorderStyle.NONE;
}
