package com.application.parts.service;

import com.application.parts.model.Part;
import com.application.parts.repository.PartRepository;
import com.application.parts.repository.PartRepositoryImpl;

import java.util.List;
import java.util.Map;

public class PartServiceImpl implements PartService {

    private PartRepository partRepository = new PartRepositoryImpl();

    public List<Part> filter(Map<String, String> params) {

        return partRepository.filterParts(params);
    }
}
