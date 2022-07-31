package com.radar.adjustment.services;

import com.radar.adjustment.models.AdjustmentDto;
import org.springframework.stereotype.Service;

@Service
public interface AdjustmentService {

    AdjustmentDto getAdjustmentById(Integer id);

    AdjustmentDto createAdjustment(AdjustmentDto adjustmentDto);

    AdjustmentDto updateAdjustment(AdjustmentDto adjustmentDto);
}
