package com.example.request;

public class ListRequest {

    private String type;
    private String page;
    private String limit;
    private String sort;
    private String typeSort;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(String typeSort) {
        this.typeSort = typeSort;
    }

    @Override
    public String toString() {
        return "ListRequest{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", limit='" + limit + '\'' +
                ", sort='" + sort + '\'' +
                ", typeSort='" + typeSort + '\'' +
                '}';
    }
}