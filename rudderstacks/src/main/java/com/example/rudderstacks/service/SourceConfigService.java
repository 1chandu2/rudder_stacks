package com.example.rudderstacks.service;

import com.example.rudderstacks.dto.request.sourceConfig.SourceConfigRequest;
import com.example.rudderstacks.entity.sourceConfig.SourceConfig;

import java.util.List;

public interface SourceConfigService {
    SourceConfig saveSourceConfig(SourceConfigRequest sourceConfigRequest, String type);
}
