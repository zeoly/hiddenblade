package com.yahacode.hiddenblade.app.doc.excel.annotation;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel related column
 *
 * @author zengyongli
 * @since 2022/05/18
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * column name
     *
     * @return display header name, default field name
     */
    String name() default "";

    /**
     * zero-based column index, must not be duplicated
     *
     * @return column index
     */
    int index();

    /**
     * column width, char unit
     *
     * @return column width
     */
    int width() default -1;

    /**
     * date-time format pattern
     *
     * @return pattern
     */
    String dateTimePattern() default "";

    /**
     * sheet header style
     *
     * @return cell style
     */
    Style headerStyle() default @Style(bold = true, fontSize = 14, fillColor = 55, border = BorderStyle.THIN);

    /**
     * data cell style
     *
     * @return cell style
     */
    Style cellStyle() default @Style(alignment = HorizontalAlignment.LEFT);

    /**
     * column for export/import
     *
     * @return purpose
     */
    ColumnPurpose purpose() default ColumnPurpose.BOTH;
}
