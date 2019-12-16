package com.td.generator.mapper;

import java.util.List;

import com.td.generator.domain.ColumnInfo;
import com.td.generator.domain.TableInfo;

/**
 * @author tudedong
 * @description 代码生成 数据层
 * @date 2019-12-12 19:10:33
 */
public interface GenMapper
{
    /**
     * 查询ry数据库表信息
     * 
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    public List<TableInfo> selectTableList(TableInfo tableInfo);

    /**
     * 根据表名称查询信息
     * 
     * @param tableName 表名称
     * @return 表信息
     */
    public TableInfo selectTableByName(String tableName);

    /**
     * 根据表名称查询列信息
     * 
     * @param tableName 表名称
     * @return 列信息
     */
    public List<ColumnInfo> selectTableColumnsByName(String tableName);
}