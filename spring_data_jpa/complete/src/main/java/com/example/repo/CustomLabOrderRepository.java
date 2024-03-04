package com.example.repo;

import com.example.model.*;

import java.util.*;

/**
 * @author : Ivan Filimonov
 * @since : 01.03.2024, 19:26
 **/
public interface CustomLabOrderRepository
{

    //@EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"labOrderReportList"})//has no effect
    List<LabOrder> withEntityGraphFindAllOrdersThenNTimesReports();

    List<LabOrder> withEntityGraphFindAllOrdersThenBatchAllCorrespondingReports();

    List<LabOrder> withEntityGraphFindAllOrdersAndJoinFetchReports();

}
