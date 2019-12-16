package com.td.common.exception.file;

import com.td.common.exception.base.BaseException;

/**
 * @author tudedong
 * @description 文件信息异常类
 * @date 2019-12-12 19:10:33
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
