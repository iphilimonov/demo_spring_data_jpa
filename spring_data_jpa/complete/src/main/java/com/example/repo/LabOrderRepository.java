package com.example.repo;

import com.example.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

/**
 * Created by adam.
 */
public interface LabOrderRepository extends CrudRepository<LabOrder, Long>, LabOrderReportAndDetailRepository, CustomLabOrderRepository {

    LabOrder findByDescription(String description);

    /**
     * [IF]: I spent some hard time trying to figure out why I'm still facing N+1 queries. Even with entity graph.
     * Here is an explanation:
     *
     *
     * EclipseLink you'll notice always does an extra query for any relationship mapping unless you specify a fetchJoin annotation
     * (or query hint, or modify the mapping with customizers) that tells it what to do, and this is independent of the relationship
     * being eager or lazily fetched. Hibernate on the other hand interprets all eager access to use join.
     * No point debating which is better - they are situational and there are good reasons to go with either solution as a generic one.
     *
     * This means that if you want a join with EclipseLink on the fly, you'll have to do more than just indicate the relationship needs to be eagerly fetched,
     * and include in query hints. If you are going with fetch graphs to optimize things, it might be a helpful to look into the other fetch types, such as batch fetching.
     * An extra query/statement isn't always a bad thing for performance, especially as object graphs grow.
     * The database is going to be forced to return N*M duplicate rows of Foo data, one for each Bar and FooBar combination in the results.
     * Depending on the data size, there will be a point where it is more efficient to get the children in separate queries.
     *
     * https://stackoverflow.com/questions/66765679/load-subentities-with-jpql-entitgraph-is-not-working-with-eclipselink-and-spring
     */
    //@QueryHints(value = {  @QueryHint(name = QueryHints.BATCH_TYPE, value = /*BatchFetchType.JOIN*/ "JOIN")})
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"labOrderReportList"})
    Iterable<LabOrder> findAll();

//    List<Person> findByLastName(String lastName);
//
//    Person findById(long id);

}
