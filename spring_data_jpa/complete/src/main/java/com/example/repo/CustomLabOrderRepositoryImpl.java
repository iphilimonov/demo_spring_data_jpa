package com.example.repo;

import com.example.model.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.eclipse.persistence.config.*;

//import org.springframework.data.jpa.repository.EntityGraph;

import java.util.*;

/**
 * @author : Ivan Filimonov
 * @since : 01.03.2024, 19:26
 **/
public class CustomLabOrderRepositoryImpl implements CustomLabOrderRepository
{
    @PersistenceContext
    private EntityManager em;

   // @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"labOrderReportList"}) //has no effect
    @Override
    public List<LabOrder> withEntityGraphFindAllOrdersThenNTimesReports()
    {
        EntityGraph<LabOrder> orderWithReportsGraph = em.createEntityGraph(LabOrder.class);
        orderWithReportsGraph.addSubgraph("labOrderReportList", LabOrderReport.class);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<LabOrder> query = criteriaBuilder.createQuery(LabOrder.class);

        return em.createQuery(query)
                .setHint(QueryHints.JPA_LOAD_GRAPH, orderWithReportsGraph)
                .getResultList();
//[EL Fine]: sql: 2024-03-01 21:01:13.545--ServerSession(1646207916)--Connection(501293975)--SELECT ID, DESCRIPTION FROM LABORDER
//[EL Fine]: sql: 2024-03-01 21:01:13.571--ServerSession(1646207916)--Connection(2098738059)--SELECT ID, LABORDER_ID FROM LABORDERREPORT WHERE (LABORDER_ID = ?)
//	bind => [1]
//[EL Fine]: sql: 2024-03-01 21:01:13.582--ServerSession(1646207916)--Connection(436338687)--SELECT ID, LABORDER_ID FROM LABORDERREPORT WHERE (LABORDER_ID = ?)
//	bind => [2]
    }

    @Override
    public List<LabOrder> withEntityGraphFindAllOrdersThenBatchAllCorrespondingReports()
    {
        EntityGraph<LabOrder> orderWithReportsGraph = em.createEntityGraph(LabOrder.class);
        orderWithReportsGraph.addSubgraph("labOrderReportList", LabOrderReport.class);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<LabOrder> query = criteriaBuilder.createQuery(LabOrder.class);

        return em.createQuery(query)
                .setHint(QueryHints.JPA_LOAD_GRAPH, orderWithReportsGraph)
                .setHint(QueryHints.BATCH, "LabOrder.labOrderReportList")
                .setHint(QueryHints.BATCH_TYPE, "JOIN")
                .getResultList();
//[EL Fine]: sql: 2024-03-01 21:04:07.152--ServerSession(1375111241)--Connection(1564173701)--SELECT ID, DESCRIPTION FROM LABORDER
//[EL Fine]: sql: 2024-03-01 21:04:07.181--ServerSession(1375111241)--Connection(2005232837)--SELECT t0.ID, t0.LABORDER_ID FROM LABORDERREPORT t0, LABORDER t1 WHERE (t0.LABORDER_ID = t1.ID)
//[EL Fine]: sql: 2024-03-01 21:04:07.185--ServerSession(1375111241)--Connection(436338687)--SELECT ID, DESCRIPTION FROM LABORDER WHERE (ID = ?)
//	bind => [2]
    }

    @Override
    public List<LabOrder> withEntityGraphFindAllOrdersAndJoinFetchReports()
    {
        EntityGraph<LabOrder> orderWithReportsGraph = em.createEntityGraph(LabOrder.class);
        orderWithReportsGraph.addSubgraph("labOrderReportList", LabOrderReport.class);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<LabOrder> query = criteriaBuilder.createQuery(LabOrder.class);

        return em.createQuery(query)
                .setHint(QueryHints.JPA_LOAD_GRAPH, orderWithReportsGraph)
                .setHint(QueryHints.FETCH, "LabOrder.labOrderReportList")
                .getResultList();
//[EL Fine]: sql: 2024-03-01 21:33:46.96--ServerSession(1842844180)--Connection(1714478376)--SELECT t1.ID, t1.DESCRIPTION, t0.ID, t0.LABORDER_ID FROM LABORDERREPORT t0, LABORDER t1 WHERE (t0.LABORDER_ID = t1.ID)
    }
}
