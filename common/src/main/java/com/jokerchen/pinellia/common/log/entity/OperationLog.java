package com.jokerchen.pinellia.common.log.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**   
 * @description: 
 * @author Joker Chen 
 * @date 2019-03-28 16:17:27  
 */
@Data
@Entity
@Table(name="tc_sys_operation_log")
public class OperationLog {

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /** 操作人 */
    @Basic
    @Column(name="operator")
    private String operator;
    
    /** 发起访问的主机地址 */
    @Basic
    @Column(name="remoteAddr")
    private String remoteAddr;

    /** 调用方法 */
    @Basic
    @Column(name="method")
    private String method;

    /** 传入的参数 */
    @Basic
    @Column(name="args")
    private String args;

    /** 操作时间 */
    @Basic
    @Column(name="operateTime")
    private Date operateTime;

    /** 操作类型，C：新增，R：查询，U：修改，D：删除 */
    @Basic
    @Column(name="operateType")
    private String operateType;
    
    /** 操作描述 */
    @Basic
    @Column(name="operateDesc")
    private String operateDesc;

    /** 操作结果，0：成功，1：异常 */
    @Basic
    @Column(name="state")
    private Integer state;

    /** 异常信息 */
    @Basic
    @Column(name="exception")
    private String exception;

}