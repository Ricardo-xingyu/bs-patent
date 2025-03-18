package com.bs.media.service;


import com.bs.base.model.PageParams;
import com.bs.base.model.PageResult;
import com.bs.media.model.dto.QueryMediaParamsDto;
import com.bs.media.model.dto.UploadFileParamsDto;
import com.bs.media.model.dto.UploadFileResultDto;
import com.bs.media.model.po.MediaFiles;

/**
 * @description 媒资文件管理业务类
 * @author Mr.M
 * @date 2022/9/10 8:55
 * @version 1.0
 */
public interface MediaFileService {

 /**
  * @description 媒资文件查询方法
  * @param pageParams 分页参数
  * @param queryMediaParamsDto 查询条件
  * @return com.bs.base.model.PageResult<po.model.com.bs.media.MediaFiles>
  * @author Mr.M
  * @date 2022/9/10 8:57
 */
 public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);

 /**
  * 上传文件
  * @param companyId 机构id
  * @param uploadFileParamsDto 上传文件信息
  * @param localFilePath 文件磁盘路径
  * @return 文件信息
  */
 public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath);



}
