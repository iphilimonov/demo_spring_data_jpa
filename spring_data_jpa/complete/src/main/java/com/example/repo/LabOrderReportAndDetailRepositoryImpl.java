package com.example.repo;

import com.example.model.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.eclipse.persistence.config.*;

import java.util.*;

/**
 * @author : Ivan Filimonov
 * @since : 01.03.2024, 21:32
 **/
public class LabOrderReportAndDetailRepositoryImpl implements LabOrderReportAndDetailRepository
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<LabOrder> withEntityGraphFindAllOrdersLeftJoinFetchReportsAndOrderDetails()
    {
        EntityGraph<LabOrder> orderWithReportsGraph = em.createEntityGraph(LabOrder.class);
        orderWithReportsGraph.addSubgraph("labOrderReportList", LabOrderReport.class);
        orderWithReportsGraph.addSubgraph("labOrderDetailList", LabOrderDetail.class);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<LabOrder> query = criteriaBuilder.createQuery(LabOrder.class);

        return em.createQuery(query)
                .setHint(QueryHints.JPA_LOAD_GRAPH, orderWithReportsGraph)
                .setHint(QueryHints.LEFT_FETCH, "LabOrder.labOrderReportList")
                .setHint(QueryHints.LEFT_FETCH, "LabOrder.labOrderDetailList")

                .getResultList();
    }

    @Override
    public List<LabOrder> withEntityGraphFindAllOrdersLeftJoinFetchReportsAndOrderDetailsNested()
    {
        EntityGraph<LabOrder> orderWithReportsGraph = em.createEntityGraph(LabOrder.class);
        orderWithReportsGraph.addSubgraph("labOrderReportList", LabOrderReport.class);
        orderWithReportsGraph
                .addSubgraph("labOrderDetailList", LabOrderDetail.class)
                .addSubgraph("subDetailList", SubDetail.class);


        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<LabOrder> query = criteriaBuilder.createQuery(LabOrder.class);

        return em.createQuery(query)
                .setHint(QueryHints.JPA_LOAD_GRAPH, orderWithReportsGraph)
                .setHint(QueryHints.LEFT_FETCH, "LabOrder.labOrderReportList")
                .setHint(QueryHints.LEFT_FETCH, "LabOrder.labOrderDetailList")
                .setHint(QueryHints.LEFT_FETCH, "LabOrder.labOrderDetailList.subDetailList")

                .getResultList();
    }

    @Override
    public List<LabOrder> findAllReportAndDetailNestedBatchFetch()
    {
        EntityGraph<LabOrder> orderWithReportsGraph = em.createEntityGraph(LabOrder.class);
        orderWithReportsGraph.addSubgraph("labOrderReportList", LabOrderReport.class);
        orderWithReportsGraph
                .addSubgraph("labOrderDetailList", LabOrderDetail.class)
                .addSubgraph("subDetailList", SubDetail.class);


        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<LabOrder> query = criteriaBuilder.createQuery(LabOrder.class);

        return em.createQuery(query)
                .setHint(QueryHints.JPA_LOAD_GRAPH, orderWithReportsGraph)
                .setHint(QueryHints.BATCH, "LabOrder.labOrderReportList")
                .setHint(QueryHints.BATCH, "LabOrder.labOrderDetailList")
                .setHint(QueryHints.BATCH, "LabOrder.labOrderDetailList.subDetailList")

                .getResultList();
    }
}
