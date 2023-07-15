package com.gosoft.assignment.ecommerce.controller;

import com.gosoft.assignment.ecommerce.model.dto.ResponseDTO;
import com.gosoft.assignment.ecommerce.model.dto.request.UserRequestDTO;
import com.gosoft.assignment.ecommerce.model.entity.TbMediaFile;
import com.gosoft.assignment.ecommerce.model.entity.TbUser;
import com.gosoft.assignment.ecommerce.service.MediaFileService;
import com.gosoft.assignment.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
//@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MediaFileService mediaFileService;

    @GetMapping(path = "/user/{id}", produces = "application/json")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        try {
            if (id == 0) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                TbUser tbUser = (TbUser) auth.getCredentials();
                id = tbUser.getId();
            }
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "GET DATA", userService.getById(id).toDTO(), "GET SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @PutMapping(path = "/user", produces = "application/json")
    public ResponseEntity updateUser(@RequestBody UserRequestDTO dto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getCredentials() instanceof String) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "TOKEN EXPIRED", null, "TOKEN EXPIRED"));
            }
            TbUser tbUser = (TbUser) auth.getCredentials();
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "UPDATE DATA", userService.updateAll(dto, tbUser).toDTO(), "UPDATE SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @PostMapping(path = "/user/uploadImage", produces = "application/json")
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("type") String type) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getCredentials() instanceof String) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "TOKEN EXPIRED", null, "TOKEN EXPIRED"));
            }
            TbUser tbUser = (TbUser) auth.getCredentials();
            TbMediaFile tbMediaFile = mediaFileService.createMediaFile(file, type, tbUser);
            return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK.value(), "UPLOAD DATA", tbMediaFile.toDTO(), "UPLOAD SUCCESS"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));
        }
    }

    @Value("${physicalImageLocation}")
    private String physicalImageLocation;

    @GetMapping(path = "/images/{name}")
    @ResponseBody
    public ResponseEntity getImage(@PathVariable("name") String name) {
        try {
            String physicalImageLocation = System.getProperty("user.dir") + this.physicalImageLocation + name;
            Path path = Paths.get(physicalImageLocation);
            File file = new File(physicalImageLocation);
            String mediaType = URLConnection.guessContentTypeFromName(file.getName());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mediaType))
                    .body(Files.readAllBytes(path));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null, e.getStackTrace().toString()));

        }
    }

}
