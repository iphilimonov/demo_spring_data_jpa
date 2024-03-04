package com.example.model;

import jakarta.persistence.*;

/**
 * @author : Ivan Filimonov
 * @since : 29.02.2024, 19:36
 **/
@Entity//(name = "LAB_ORDER_REPORT")
//@Table(name = "LAB_ORDER_REPORT")
public class LabOrderReport
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private LabOrder labOrder;

    public LabOrderReport()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LabOrder getLabOrder()
    {
        return labOrder;
    }

    public void setLabOrder(LabOrder labOrder)
    {
        this.labOrder = labOrder;
    }
}
