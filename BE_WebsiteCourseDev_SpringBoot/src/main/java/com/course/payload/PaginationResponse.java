package com.course.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponse <T>{
    private List<T> data;
    private int currentPage;
    private long totalItems;
    private int totalPages;
}
