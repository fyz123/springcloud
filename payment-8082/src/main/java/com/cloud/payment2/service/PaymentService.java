package com.cloud.payment2.service;

import com.cloud.common.entities.Payment;
import com.cloud.payment2.dao.PaymentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int save(Payment payment){
        return paymentDao.save(payment);
    }
    public Payment query(long id){
        return  paymentDao.queryById(id);
    }

}
