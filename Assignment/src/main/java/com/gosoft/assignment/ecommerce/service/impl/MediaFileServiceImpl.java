package com.gosoft.assignment.ecommerce.service.impl;


import com.gosoft.assignment.ecommerce.model.entity.TbMediaFile;
import com.gosoft.assignment.ecommerce.model.entity.TbOrder;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.repo.MediaFileRepository;
import com.gosoft.assignment.ecommerce.service.BaseService;
import com.gosoft.assignment.ecommerce.service.MediaFileService;
import com.gosoft.assignment.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MediaFileServiceImpl implements BaseService<TbMediaFile>, MediaFileService {

    @Autowired
    private MediaFileRepository repository;

    @Value("${physicalImageLocation}")
    private String physicalImageLocation;

    @Value("${imageServer}")
    private String imageServer;


    @Override
    public TbMediaFile create(TbMediaFile entity) throws Exception {
        return repository.save(entity);
    }

    @Override
    public List<TbMediaFile> getAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Page<TbMediaFile> getAll(Pageable pageable) throws Exception {
        return repository.findAll(pageable);
    }

    @Override
    public TbMediaFile getById(Long id) throws Exception {
        return repository.findById(id).orElse(new TbMediaFile());
    }

    @Override
    public TbMediaFile update(TbMediaFile entity) throws Exception {
        return repository.save(entity);
    }

    @Override
    public Boolean delete(TbMediaFile entity) throws Exception {
        try {
            repository.delete(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteById(Long id) throws Exception {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TbMediaFile createMediaFile(MultipartFile file, String type, TbUser user) {
        String uploadedFolder = System.getProperty("user.dir") + physicalImageLocation;
        String timeStamp = new SimpleDateFormat("dd-MM-YYYY").format(new Date());
        String newFilename = timeStamp + "-" + file.getOriginalFilename();
        Path path = Paths.get(uploadedFolder + newFilename);
        try {
            Files.createDirectories(Paths.get(uploadedFolder));
            Files.write(path, file.getBytes());
            return create(new TbMediaFile(file.getOriginalFilename(), imageServer + newFilename, file.getContentType(), type, file.getSize(), user));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
