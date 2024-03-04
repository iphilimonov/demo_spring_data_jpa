package com.example.model;

import jakarta.persistence.*;

import java.util.*;

/**
 * @author : Ivan Filimonov
 * @since : 01.03.2024, 21:22
 **/
@Entity
public class LabOrderDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne
    private LabOrder labOrder;

    @OneToMany(targetEntity= SubDetail.class,mappedBy="labOrderDetail")
    private List<SubDetail> subDetailList;

    public LabOrderDetail()
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

    public List<SubDetail> getSubDetailList()
    {
        return subDetailList;
    }

    public void setSubDetailList(List<SubDetail> subDetailList)
    {
        this.subDetailList = subDetailList;
    }
}
