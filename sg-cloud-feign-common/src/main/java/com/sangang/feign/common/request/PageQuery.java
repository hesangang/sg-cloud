package com.sangang.feign.common.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * sangang
 */
@Data
public class PageQuery<T> implements Serializable {

    private List<String> lastSort;

    private String pitId;

    private Integer pageSize = 10;

    private Integer pageNumber = 1;

}
