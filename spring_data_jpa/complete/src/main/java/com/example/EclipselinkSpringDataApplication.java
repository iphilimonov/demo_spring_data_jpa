package com.example;

import com.example.model.*;
import com.example.repo.*;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringBootApplication
public class EclipselinkSpringDataApplication {

    private static final Logger log = LoggerFactory.getLogger(EclipselinkSpringDataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EclipselinkSpringDataApplication.class, args);

    }


    @Bean
    public CommandLineRunner demo(LabOrderRepository repository) {
        return (args) -> {

           // readAllOrdersAndThenNTimesReadReports(repository);

            //withGraphReadAllOrdersAndThenNTimesReadReports(repository);

            //[EL Fine]: sql: 2024-03-01 21:04:07.152--ServerSession(1375111241)--Connection(1564173701)--SELECT ID, DESCRIPTION FROM LABORDER
            //[EL Fine]: sql: 2024-03-01 21:04:07.181--ServerSession(1375111241)--Connection(2005232837)--SELECT t0.ID, t0.LABORDER_ID FROM LABORDERREPORT t0, LABORDER t1 WHERE (t0.LABORDER_ID = t1.ID)
            //[EL Fine]: sql: 2024-03-01 21:04:07.185--ServerSession(1375111241)--Connection(436338687)--SELECT ID, DESCRIPTION FROM LABORDER WHERE (ID = ?)
            //	bind => [2]
            //List<LabOrder> batchAll = repository.withEntityGraphFindAllOrdersThenBatchAllCorrespondingReports();

            //[EL Fine]: sql: 2024-03-01 21:33:46.96--ServerSession(1842844180)--Connection(1714478376)--SELECT t1.ID, t1.DESCRIPTION, t0.ID, t0.LABORDER_ID FROM LABORDERREPORT t0, LABORDER t1 WHERE (t0.LABORDER_ID = t1.ID)
            //List<LabOrder> joinFetchAll = repository.withEntityGraphFindAllOrdersAndJoinFetchReports();

            //[EL Fine]: sql: 2024-03-01 21:37:57.477--ServerSession(162716758)--Connection(1167849596)--SELECT t1.ID, t1.DESCRIPTION, t0.ID, t0.LABORDER_ID, t2.ID, t2.DESCRIPTION, t2.LABORDER_ID FROM LABORDERREPORT t0, LABORDERDETAIL t2, LABORDER t1 WHERE ((t0.LABORDER_ID = t1.ID) AND (t2.LABORDER_ID = t1.ID))
            //List<LabOrder> joinFetchReportsAndOrderDetails = repository.withEntityGraphFindAllOrdersLeftJoinFetchReportsAndOrderDetails();


            //List<LabOrder> joinFetchWithNested = repository.withEntityGraphFindAllOrdersLeftJoinFetchReportsAndOrderDetailsNested();

            List<LabOrder> labOrderList = repository.findAllReportAndDetailNestedBatchFetch();

            log.info("");
        };
    }

    private void readAllOrdersAndThenNTimesReadReports(LabOrderRepository repository)
    {
        log.info("In this example all the reports are loaded lazily");
        Iterable<LabOrder> labOrders = repository.findAll();
        //[EL Fine]: sql: 2024-03-04 19:06:56.578--ServerSession(439244350)--Connection(1320260667)--SELECT ID, DESCRIPTION FROM LABORDER

        LabOrder labOrder = labOrders.iterator().next();
        log.info("trigger n queries");
        labOrder.getLabOrderReportList().size();
        //[EL Fine]: sql: 2024-03-04 19:06:56.606--ServerSession(439244350)--Connection(1531514206)--SELECT ID, LABORDER_ID FROM LABORDERREPORT WHERE (LABORDER_ID = ?)
        //        bind => [1]
        //[EL Fine]: sql: 2024-03-04 19:06:56.615--ServerSession(439244350)--Connection(1974062116)--SELECT ID, LABORDER_ID FROM LABORDERREPORT WHERE (LABORDER_ID = ?)
        //        bind => [2]
    }

    private void withGraphReadAllOrdersAndThenNTimesReadReports(LabOrderRepository repository)
    {
        log.info("Read All orders, then perform N queries to read reports. In contrast with previous example it will perform all the queries eagerly");
        //[EL Fine]: sql: 2024-03-01 21:03:25.058--ServerSession(1375111241)--Connection(1256914788)--SELECT ID, DESCRIPTION FROM LABORDER
        //[EL Fine]: sql: 2024-03-01 21:03:25.083--ServerSession(1375111241)--Connection(818464427)--SELECT ID, LABORDER_ID FROM LABORDERREPORT WHERE (LABORDER_ID = ?)
        //	bind => [1]
        //[EL Fine]: sql: 2024-03-01 21:03:25.092--ServerSession(1375111241)--Connection(799570413)--SELECT ID, LABORDER_ID FROM LABORDERREPORT WHERE (LABORDER_ID = ?)
        //	bind => [2]
        List<LabOrder> batchAll = repository.withEntityGraphFindAllOrdersThenNTimesReports();
    }
}
