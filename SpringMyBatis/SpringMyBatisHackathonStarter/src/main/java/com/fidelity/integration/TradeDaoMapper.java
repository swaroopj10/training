package com.fidelity.integration;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.fidelity.business.*;

@Repository
public interface TradeDaoMapper {
//    @Insert("INSERT INTO cp_order(orderId, instrumentId, quantity, targetPrice, direction, clientId) " +
//            "VALUES (#{orderId}, #{instrumentId}, #{quantity}, #{targetPrice}, #{direction}, #{clientId})")
//    @Options(useGeneratedKeys = true, keyProperty = "orderId",  keyColumn = "orderId")
    String createOrder(Order order);

//    @Insert("INSERT INTO cp_trade(tradeId, instrumentId, quantity, executionPrice, direction, clientId, orderId, cashValue) " +
//            "VALUES (#{tradeId}, #{instrumentId}, #{quantity}, #{executionPrice}, #{direction}, #{clientId}, #{order.orderId}, #{cashValue})")
    int createTrade(Trade trade);

//    @Select("SELECT t.tradeid, t.instrumentid, t.quantity, t.executionprice, t.direction, t.clientid, o.orderid, t.cashvalue, o.targetprice " +
//            "FROM cp_trade t " +
//            "INNER JOIN cp_order o ON t.orderId = o.orderId " +
//            "WHERE t.clientId = #{clientId}")
    List<Trade> getTradeHistory(String clientId);

//    @Select("SELECT t.tradeid, t.instrumentid, t.quantity, t.executionprice, t.direction, t.clientid, o.orderid, t.cashvalue, o.targetprice " +
//            "FROM cp_trade t " +
//            "INNER JOIN cp_order o ON t.orderId = o.orderId " +
//            "WHERE t.clientId = #{clientId}")
    List<Trade> getPortfolio(String clientId);

//    @Select("SELECT t.tradeid, t.instrumentid, t.quantity, t.executionprice, t.direction, t.clientid, o.orderid, t.cashvalue, o.targetprice " +
//            "FROM cp_trade t " +
//            "INNER JOIN cp_order o ON t.orderId = o.orderId " +
//            "WHERE t.clientId = #{clientId}")
    double getBalanceAmount(String clientId);

//    @Select("SELECT t.tradeid, t.instrumentid, t.quantity, t.executionprice, t.direction, t.clientid, o.orderid, t.cashvalue, o.targetprice " +
//            "FROM cp_trade t " +
//            "INNER JOIN cp_order o ON t.orderId = o.orderId " +
//            "WHERE t.clientId = #{clientId}")
    double getPortfolioWorth(String clientId);
}