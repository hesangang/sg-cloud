package com.sangang.feign.common.respose;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * sangang
 */
@Data
public class PageResult<T> implements Serializable {

    private List<String> lastSort;

    private String pitId;

    private Long total;

    private List<T> result;

    public PageResult() {
    }

    public PageResult(Long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public PageResult(String pitId, Long total, List<T> result) {
        this.pitId = pitId;
        this.total = total;
        this.result = result;
    }

    public PageResult(String pitId,List<String> lastSort, Long total, List<T> result) {
        this.lastSort = lastSort;
        this.pitId = pitId;
        this.total = total;
        this.result = result;
    }
}
