package com.application.parts.service;

import com.application.parts.model.Part;

import java.util.List;
import java.util.Map;

public interface PartService {
    /**
     * Filters parts by map of parameters
     * @param params pairs (parameter, value) to be filtered
     * @return filtered list of parts
     */
    List<Part> filter(Map<String, String> params);
}
