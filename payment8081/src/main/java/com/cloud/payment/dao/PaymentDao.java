package com.cloud.payment.dao;

import com.cloud.common.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    public int save(Payment payment);
    public Payment queryById(@Param("id") long id);

}
