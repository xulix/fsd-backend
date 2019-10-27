package com.fsd.mod.user.service;

import com.fsd.mod.user.dto.PageDto;
import org.springframework.data.domain.Page;

public interface IPageService {
    <S, T> PageDto<T> convertToPageDto(Page<S> page, Converter<S, T> converter);
}
