package com.example.spring.dto.book;

import java.math.BigDecimal;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsExclude;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
    @EqualsExclude
    private List<Long> categoryIds;
}

