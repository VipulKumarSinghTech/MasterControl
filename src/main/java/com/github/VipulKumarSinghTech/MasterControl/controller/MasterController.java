package com.github.VipulKumarSinghTech.MasterControl.controller;

import com.github.VipulKumarSinghTech.MasterControl.dto.ListRequestDto;
import com.github.VipulKumarSinghTech.MasterControl.dto.RequestDto;
import com.github.VipulKumarSinghTech.MasterControl.service.MasterControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("control")
public class MasterController {

    private final MasterControlService masterControlService;

    @Autowired
    public MasterController(MasterControlService masterControlService) {
        this.masterControlService = masterControlService;
    }

    /**
     * @return
     */
    @GetMapping("index")
    public Map<String, String> getIndex() {
        return masterControlService.getIndex();
    }

    /**
     * @param listRequestDto
     * @return
     * @throws ClassNotFoundException
     */
    @PostMapping("get")
    public ResponseEntity<?> getList(@RequestBody ListRequestDto listRequestDto) throws ClassNotFoundException {
        if (listRequestDto.getId() != null) {
            return ResponseEntity.ok(masterControlService.findById(listRequestDto.getKey(), listRequestDto.getId()));
        }
        return ResponseEntity.ok(masterControlService.findAll(listRequestDto.getKey()));
    }

    /**
     * @param requestDto
     * @return
     * @throws ReflectiveOperationException
     */
    @PostMapping("create")
    public Object createData(@RequestBody RequestDto requestDto) throws ReflectiveOperationException {
        return masterControlService.createData(requestDto.getKey(), requestDto.getId(), requestDto.getFieldValueMap());
    }

    /**
     * @param requestDto
     * @return
     * @throws ReflectiveOperationException
     */
    @PutMapping("update")
    public Object updateData(@RequestBody RequestDto requestDto) throws ReflectiveOperationException {
        return masterControlService.updateData(
                requestDto.getKey(),
                requestDto.getId(),
                requestDto.getFieldValueMap());
    }

    /**
     * @param id
     * @param key
     * @return
     * @throws ReflectiveOperationException
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Object id,
                                             @RequestParam String key) throws ReflectiveOperationException {
        masterControlService.deleteById(key, id);
        return ResponseEntity.ok("Deletion successful.");
    }

}
