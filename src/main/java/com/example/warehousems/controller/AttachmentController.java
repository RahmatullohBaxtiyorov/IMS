package com.example.warehousems.controller;

import com.example.warehousems.payload.ResponseApi;
import com.example.warehousems.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/attachment")
@CrossOrigin
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseApi upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadFile(request);
    }

}
