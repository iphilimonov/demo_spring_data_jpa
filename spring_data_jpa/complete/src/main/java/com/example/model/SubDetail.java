package com.example.model;

import jakarta.persistence.*;

/**
 * @author : Ivan Filimonov
 * @since : 01.03.2024, 21:43
 **/
@Entity
public class SubDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private LabOrderDetail labOrderDetail;

    public SubDetail()
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

    public LabOrderDetail getLabOrderDetail()
    {
        return labOrderDetail;
    }

    public void setLabOrderDetail(LabOrderDetail labOrderDetail)
    {
        this.labOrderDetail = labOrderDetail;
    }
}
