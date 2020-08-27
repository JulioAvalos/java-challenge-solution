package com.applaudo.movies.web.api.v1.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class MoviePagedList extends PageImpl<MovieDTO> {

    public MoviePagedList(List<MovieDTO> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public MoviePagedList(List<MovieDTO> content) {
        super(content);
    }
}
