package com.example.repo;

import com.example.model.*;

import java.util.*;

/**
 * @author : Ivan Filimonov
 * @since : 01.03.2024, 21:32
 **/
public interface LabOrderReportAndDetailRepository
{

    List<LabOrder> withEntityGraphFindAllOrdersLeftJoinFetchReportsAndOrderDetails();

    List<LabOrder> withEntityGraphFindAllOrdersLeftJoinFetchReportsAndOrderDetailsNested();

    List<LabOrder> findAllReportAndDetailNestedBatchFetch();

}
