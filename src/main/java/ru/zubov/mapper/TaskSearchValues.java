package ru.zubov.mapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskSearchValues {
    private final String title;
    private final Integer completed;
    private final Long priorityId;
    private final Long categoryId;
    private String email;

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    //постраничность
    private Integer pageNumber;
    private Integer pageSize;

    //сортировка
    private final String sortColumn;
    private final String sortDirection;
}
