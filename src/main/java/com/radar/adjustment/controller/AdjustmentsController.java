package com.radar.adjustment.controller;

import com.radar.adjustment.models.AdjustmentDto;
import com.radar.adjustment.services.AdjustmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adjustments")
@Slf4j
public class AdjustmentsController {

    @Autowired
    private AdjustmentService adjustmentService;

    @GetMapping("/{adjustmentId}")
    public AdjustmentDto getAdjustmentById(@PathVariable("adjustmentId") Integer adjustmentId){
        log.info("searching by ID: " + adjustmentId);
        AdjustmentDto adjustmentDto = adjustmentService.getAdjustmentById(adjustmentId);
        return adjustmentDto;
    }

    @PostMapping("/addAdjustment")
    public AdjustmentDto addAdjustment(@RequestBody AdjustmentDto adjustmentDto){
        return adjustmentService.createAdjustment(adjustmentDto);
    }

    @PutMapping("/updateAdjustment")
    public AdjustmentDto updateAdjustment(@RequestBody AdjustmentDto adjustmentDto){
        return adjustmentService.updateAdjustment(adjustmentDto);
    }
}
