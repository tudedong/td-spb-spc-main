package com.td.common.exception.file;

/**
 * @author tudedong
 * @description 文件名称超长限制异常类
 * @date 2019-12-12 19:10:33
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
