package com.td.system.mapper;

import java.util.List;

import com.td.system.domain.SysConfig;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author tudedong
 * @description 参数配置 数据层
 * @date 2019-12-12 19:10:33
 */
public interface SysConfigMapper extends Mapper<SysConfig>{
	/**
	 * 查询参数配置信息
	 *
	 * @param config
	 *            参数配置信息
	 * @return 参数配置信息
	 */
	public SysConfig selectConfig(SysConfig config);

	/**
	 * 查询参数配置列表
	 *
	 * @param config
	 *            参数配置信息
	 * @return 参数配置集合
	 */
	public List<SysConfig> selectConfigList(SysConfig config);

	/**
	 * 根据键名查询参数配置信息
	 *
	 * @param configKey
	 *            参数键名
	 * @return 参数配置信息
	 */
	public SysConfig checkConfigKeyUnique(String configKey);

	/**
	 * 新增参数配置
	 *
	 * @param config
	 *            参数配置信息
	 * @return 结果
	 */
	public int insertConfig(SysConfig config);

	/**
	 * 修改参数配置
	 *
	 * @param config
	 *            参数配置信息
	 * @return 结果
	 */
	public int updateConfig(SysConfig config);

	/**
	 * 批量删除参数配置
	 *
	 * @param configIds
	 *            需要删除的数据ID
	 * @return 结果
	 */
	public int deleteConfigByIds(String[] configIds);
}