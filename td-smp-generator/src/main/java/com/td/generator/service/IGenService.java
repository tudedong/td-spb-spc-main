package com.td.generator.service;

import java.util.List;

import com.td.generator.domain.TableInfo;

/**
 * @author tudedong
 * @description 代码生成 服务层
 * @date 2019-12-12 19:10:33
 */
public interface IGenService
{
    /**
     * 查询ry数据库表信息
     * 
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    public List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 生成代码
     * 
     * @param tableName 表名称
     * @return 数据
     */
    public byte[] generatorCode(String tableName);

    /**
     * 批量生成代码
     * 
     * @param tableNames 表数组
     * @return 数据
     */
    public byte[] generatorCode(String[] tableNames);
}
