package com.github.VipulKumarSinghTech.MasterControl.controller;

import com.github.VipulKumarSinghTech.MasterControl.dto.ListRequestDto;
import com.github.VipulKumarSinghTech.MasterControl.dto.RequestDto;
import com.github.VipulKumarSinghTech.MasterControl.service.MasterControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("control")
public class MasterController {

    private final MasterControlService masterControlService;

    @Autowired
    public MasterController(MasterControlService masterControlService) {
        this.masterControlService = masterControlService;
    }

    @GetMapping("index")
    public Map<String, String> getIndex() {
        return masterControlService.getIndex();
    }

    @PostMapping("get")
    public ResponseEntity<?> getList(@RequestBody ListRequestDto listRequestDto) throws ClassNotFoundException {
        if (listRequestDto.getId() != null) {
            return ResponseEntity.ok(masterControlService.findById(listRequestDto.getKey(), listRequestDto.getId()));
        }
        return ResponseEntity.ok(masterControlService.findAll(listRequestDto.getKey()));
    }

    @PostMapping("create")
    public Object createData(@RequestBody RequestDto requestDto) throws ReflectiveOperationException {
        return masterControlService.createData(requestDto.getKey(), requestDto.getFieldValueMap());
    }

    @PutMapping("update")
    public Object updateData(@RequestBody RequestDto requestDto) throws ReflectiveOperationException {
        return masterControlService.updateData(
                requestDto.getKey(),
                requestDto.getId(),
                requestDto.getFieldValueMap());
    }

    @PutMapping("delete")
    public ResponseEntity<Map<String, String>> deleteById(@RequestBody RequestDto requestDto) throws ReflectiveOperationException {
        masterControlService.deleteById(requestDto.getKey(), requestDto.getId());
        return ResponseEntity.ok(Collections.singletonMap("message", "Deletion of " + requestDto.getKey() + " with id " + requestDto.getId() + " successful."));
    }

}
