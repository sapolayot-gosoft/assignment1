package com.gosoft.assignment.ecommerce.service;

import com.gosoft.assignment.ecommerce.model.entity.TbMediaFile;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import org.springframework.web.multipart.MultipartFile;

public interface MediaFileService extends BaseService<TbMediaFile> {

    TbMediaFile createMediaFile(MultipartFile file, String type, TbUser user);

}
