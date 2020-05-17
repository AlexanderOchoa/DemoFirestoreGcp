package com.example.repository.reusable;

import com.example.request.ListRequest;
import com.google.cloud.firestore.Query;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class ReusableRepositoryImpl {

    private static final String FORMAT_DATE_HOURS_XM = "yyyy-MM-dd HH:mm:ss";

    protected static final String FIELD_CODE = "code";
    protected static final String FIELD_TYPE = "type";
    protected static final String FIELD_MODIFIED_DATE = "moficatedDate";

    protected Query addPagination(Query query, final ListRequest request) throws Exception {
        if (request.getPage() != null && !request.getPage().isEmpty() && request.getLimit() != null && !request.getLimit().isEmpty()) {
            query = query.offset(NumberUtils.toInt(request.getPage())).limit(NumberUtils.toInt(request.getLimit()));
        }

        return query;
    }

    protected Query addSort(Query query, final ListRequest request) {
        if (request.getSort() != null && !request.getTypeSort().isEmpty()) {
            query = query.orderBy(request.getSort(), getTypeSort(request.getTypeSort()));
        }

        return query;
    }

    protected Query addFilterByStartDate(Query query, final String dateStart) throws Exception {
        query = query.whereGreaterThanOrEqualTo(FIELD_MODIFIED_DATE, getDateFromString(dateStart, FORMAT_DATE_HOURS_XM));
        query = query.whereLessThanOrEqualTo(FIELD_MODIFIED_DATE, getDateFromString(dateStart, FORMAT_DATE_HOURS_XM));

        return query;
    }

    protected Query addFilterLike(Query query, String campo, String filter) throws Exception{
        if (filter != null && !filter.isEmpty()) {
            query = query.whereArrayContains(campo, filter);
        }

        return query;
    }

    protected Query addGenericFilter(Query query, String field, String filter) throws Exception {
        if (filter != null && !filter.isEmpty()) {
            query = query.whereEqualTo(field, filter);
        }

        return query;
    }

    protected Query addGenericFilterIn(Query query, String campo, List<String> filter) throws Exception {
        if (filter != null && !filter.isEmpty()) {
            query = query.whereIn(campo, filter);
        }

        return query;
    }

    private Query.Direction getTypeSort(String sortType) {
        return sortType != null ? Query.Direction.valueOf(sortType): Query.Direction.DESCENDING;
    }

    private Date getDateFromString(final String date, final String format) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

}
