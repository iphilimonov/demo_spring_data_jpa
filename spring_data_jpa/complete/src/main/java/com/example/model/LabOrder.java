package com.example.model;

import jakarta.persistence.*;
import org.eclipse.persistence.queries.*;

import java.util.*;

@Entity//(name = "LAB_ORDER")
//@Table(name = "LAB_ORDER")
public class LabOrder
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    //@OneToMany
    @OneToMany(targetEntity=LabOrderReport.class,mappedBy="labOrder")
    private List<LabOrderReport> labOrderReportList;

    @OneToMany(targetEntity=LabOrderDetail.class,mappedBy="labOrder")
    private List<LabOrderDetail> labOrderDetailList;
    public LabOrder()
    {
    }

    public LabOrder(String firstName)
    {
        this.description = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String firstName) {
        this.description = firstName;
    }

    public List<LabOrderReport> getLabOrderReportList()
    {
        return labOrderReportList;
    }

    public void setLabOrderReportList(List<LabOrderReport> labOrderReportList)
    {
        this.labOrderReportList = labOrderReportList;
    }

    public List<LabOrderDetail> getLabOrderDetailList()
    {
        return labOrderDetailList;
    }

    public void setLabOrderDetailList(List<LabOrderDetail> labOrderDetailList)
    {
        this.labOrderDetailList = labOrderDetailList;
    }
}
