package com.td.common.exception.file;

/**
 * @author tudedong
 * @description 文件名大小限制异常类
 * @date 2019-12-12 19:10:33
 */
public class FileSizeLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
