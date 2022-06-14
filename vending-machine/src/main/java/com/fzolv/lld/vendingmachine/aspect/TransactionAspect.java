package com.fzolv.lld.vendingmachine.aspect;

import com.fzolv.lld.vendingmachine.exception.TransactionExistsException;
import com.fzolv.lld.vendingmachine.model.Transaction;
import com.fzolv.lld.vendingmachine.service.TransactionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

    @Autowired
    TransactionService transactionService;

    @Around("@annotation(TransactionEnabled)")
    public Object transactionContext(ProceedingJoinPoint joinPoint) throws Throwable {
        Transaction transaction = (Transaction) joinPoint.getArgs()[0];
        Integer machineId = transaction.getMachineId();
        boolean state = transactionService.beginTransaction(transaction);
        if(!state) throw new TransactionExistsException();
        Object response = null;
        try {
            response = joinPoint.proceed();
        } catch(Exception e) {
            transactionService.rollbackTransaction(machineId);
            throw new RuntimeException("Ran into an issue", e);
        }
        transactionService.commitTransaction(machineId);
        return response;
    }
}
