package com.panda.server.cloud.file.controller;

import com.panda.server.cloud.common.web.controller.BasicController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController extends BasicController {

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity add(){
        return assemble();
    }

    @GetMapping("/{imageId}")
    @ResponseBody
    public ResponseEntity detail(@PathVariable("imageId") String imageId){
        return assemble();
    }
}
