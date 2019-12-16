package com.td.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.common.constant.Constants;
import com.td.common.utils.StringUtils;
import com.td.generator.config.GenConfig;
import com.td.generator.domain.ColumnInfo;
import com.td.generator.domain.TableInfo;
import com.td.generator.mapper.GenMapper;
import com.td.generator.service.IGenService;
import com.td.generator.util.GenUtils;
import com.td.generator.util.VelocityInitializer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tudedong
 * @description 代码生成 服务层处理
 * @date 2019-12-12 19:10:33
 */
@Service
@Slf4j
public class GenServiceImpl implements IGenService {

	@Autowired
	private GenMapper genMapper;

	/**
	 * 查询ry数据库表信息
	 *
	 * @param tableInfo
	 *            表信息
	 * @return 数据库表列表
	 */
	@Override
	public List<TableInfo> selectTableList(TableInfo tableInfo) {
		return genMapper.selectTableList(tableInfo);
	}

	/**
	 * 生成代码
	 *
	 * @param tableName
	 *            表名称
	 * @return 数据
	 */
	@Override
	public byte[] generatorCode(String tableName) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		generatorCode(tableName, zip);
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	/**
	 * 批量生成代码
	 *
	 * @param tableNames
	 *            表数组
	 * @return 数据
	 */
	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		for (String tableName : tableNames) {
			generatorCode(tableName, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	/**
	 * 查询表信息并生成代码
	 */
	private void generatorCode(String tableName, ZipOutputStream zip) {
		// 查询表信息
		TableInfo table = genMapper.selectTableByName(tableName);
		// 查询列信息
		List<ColumnInfo> columns = genMapper.selectTableColumnsByName(tableName);

		// 表名转换成Java属性名
		String className = GenUtils.tableToJava(table.getTableName());
		table.setClassName(className);
		table.setClassname(StringUtils.uncapitalize(className));
		// 列信息
		table.setColumns(GenUtils.transColums(columns));
		// 设置主键
		table.setPrimaryKey(table.getColumnsLast());

		VelocityInitializer.initVelocity();

		String packageName = GenConfig.getPackageName();
		String moduleName = GenUtils.getModuleName(packageName);

		VelocityContext context = GenUtils.getVelocityContext(table);

		// 获取模板列表
		List<String> templates = GenUtils.getTemplates();
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, Constants.UTF8);
			tpl.merge(context, sw);
			try {
				// 添加到zip
				zip.putNextEntry(new ZipEntry(GenUtils.getFileName(template, table, moduleName)));
				IOUtils.write(sw.toString(), zip, Constants.UTF8);
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				log.error("渲染模板失败，表名：" + table.getTableName(), e);
			}
		}
	}
}
