package com.emrekaraman.springsocial.business.dtos;

import com.emrekaraman.springsocial.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PagesDto<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;

    public PagesDto(Page<T> page){
        this.content = page.getContent();
        this.pageNumber = page.getPageable().getPageNumber();
        this.pageSize = page.getPageable().getPageSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalPages();
        this.isFirstPage = page.isFirst();
        this.isLastPage = page.isLast();
    }
}
