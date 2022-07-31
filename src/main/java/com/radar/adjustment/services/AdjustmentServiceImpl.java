package com.radar.adjustment.services;


import com.radar.adjustment.data.Adjustment;
import com.radar.adjustment.data.AdjustmentField;
import com.radar.adjustment.data.AdjustmentRecord;
import com.radar.adjustment.models.AdjustmentDto;
import com.radar.adjustment.models.AdjustmentFieldDto;
import com.radar.adjustment.models.AdjustmentRecordDto;
import com.radar.adjustment.models.User;
import com.radar.adjustment.repositories.AdjustmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdjustmentServiceImpl implements AdjustmentService {

    @Autowired
    private AdjustmentRepository adjustmentRepository;

    @Override
    public AdjustmentDto getAdjustmentById(Integer id) {
        Optional<Adjustment> adjustmentOptional = adjustmentRepository.findById(id);
        AdjustmentDto adjustmentDto = new AdjustmentDto();
        if(adjustmentOptional.isPresent()){
            log.info(adjustmentOptional.get().toString());
            adjustmentDto = adjustmentEntityToDto(adjustmentOptional.get());
        }else{
            log.error("No record found!!");
        }
        return adjustmentDto;
    }

    @Override
    public AdjustmentDto createAdjustment(AdjustmentDto adjustmentDto) {
        Adjustment adjustment = adjustmentDtoToEntity(adjustmentDto);
        Adjustment savedAdjustment = adjustmentRepository.save(adjustment);
        adjustmentDto = adjustmentEntityToDto(savedAdjustment);
        return adjustmentDto;
    }

    @Override
    public AdjustmentDto updateAdjustment(AdjustmentDto adjustmentDto) {
        Optional<Adjustment> adjustmentOptional = adjustmentRepository.findById(adjustmentDto.getAdjustmentId());
        if(adjustmentOptional.isPresent()){
            Adjustment savedAdjustment = adjustmentRepository.save(adjustmentOptional.get());
            adjustmentDto = adjustmentEntityToDto(savedAdjustment);
        }else{
            log.error("No record found to update!!");
        }
        return adjustmentDto;
    }

    private AdjustmentDto adjustmentEntityToDto(Adjustment savedAdjustment) {
        AdjustmentDto adjustmentDto = new AdjustmentDto();
        AdjustmentRecordDto adjustmentRecordDto = new AdjustmentRecordDto();
        User userDto = new User();

        List adjustmentRecordList = new ArrayList<>();
        List adjustmentFieldList = new ArrayList();

        adjustmentDto.setAdjustmentId(savedAdjustment.getAdjustmentId());
        adjustmentDto.setAdjustmentStatus(savedAdjustment.getAdjustmentStatus());
        adjustmentDto.setAdjustmentReportedDate(savedAdjustment.getAdjustmentReportedDate());
        adjustmentDto.setRegion(savedAdjustment.getRegion());
        adjustmentDto.setReasonForAdjustment(savedAdjustment.getReasonForAdjustment());
        adjustmentDto.setAdjustmentRecords(adjustmentRecordList);
        adjustmentDto.setCreatedDate(savedAdjustment.getCreatedDate());
        userDto.setUserId(savedAdjustment.getCreatedBy());
        adjustmentDto.setUser(userDto);
        for (AdjustmentRecord record : savedAdjustment.getAdjustmentRecords()) {
            adjustmentRecordDto.setRecordId(record.getRecordId());
            for (AdjustmentField field : record.getAdjustmentFields()) {
                AdjustmentFieldDto adjustmentField = new AdjustmentFieldDto();
                adjustmentField.setFieldId(field.getFieldId());
                adjustmentField.setFieldName(field.getFieldName());
                adjustmentField.setFieldType(field.getFieldType());
                adjustmentField.setFieldValue(field.getFieldValue());
                adjustmentField.setDataAsset(field.getDataAsset());
                adjustmentFieldList.add(adjustmentField);
            }
            adjustmentRecordDto.setAdjustmentFields(adjustmentFieldList);
            adjustmentRecordList.add(adjustmentRecordDto);
            adjustmentDto.setAdjustmentRecords(adjustmentRecordList);
        }
        adjustmentDto.setComment(savedAdjustment.getComment());
        return adjustmentDto;
    }

    private Adjustment adjustmentDtoToEntity(AdjustmentDto adjustmentDto) {
        Adjustment adjustment = new Adjustment();
        AdjustmentRecord adjustmentRecord = new AdjustmentRecord();

        List adjustmentRecordList = new ArrayList();
        List adjustmentFieldList = new ArrayList();

        adjustment.setAdjustmentStatus(adjustmentDto.getAdjustmentStatus());
        adjustment.setAdjustmentReportedDate(adjustmentDto.getAdjustmentReportedDate());
        adjustment.setCreatedBy(adjustmentDto.getUser().getUserId());
        adjustment.setRegion(adjustmentDto.getRegion());
        adjustment.setReasonForAdjustment(adjustmentDto.getReasonForAdjustment());
        adjustment.setAdjustmentRecords(adjustmentRecordList);
        adjustment.setCreatedDate(new Date());
        for (AdjustmentRecordDto records : adjustmentDto.getAdjustmentRecords()) {
            for (AdjustmentFieldDto fieldDto : records.getAdjustmentFields()) {
                AdjustmentField adjustmentField = new AdjustmentField();
                adjustmentField.setFieldName(fieldDto.getFieldName());
                adjustmentField.setFieldType(fieldDto.getFieldType());
                adjustmentField.setFieldValue(fieldDto.getFieldValue());
                adjustmentField.setDataAsset(fieldDto.getDataAsset());
                adjustmentFieldList.add(adjustmentField);
            }
            adjustmentRecord.setAdjustmentFields(adjustmentFieldList);
            adjustmentRecordList.add(adjustmentRecord);
            adjustment.setAdjustmentRecords(adjustmentRecordList);
        }
        adjustment.setComment(adjustmentDto.getComment());
        return adjustment;
    }
}
