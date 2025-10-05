package com.grade.manage.dto.summary;

/**
 *
 * @author Studios TKOH!
 */
public record TemplateSummary(
        Long id, 
        String name, 
        Integer weekday, 
        String description, 
        Boolean active) {

}
