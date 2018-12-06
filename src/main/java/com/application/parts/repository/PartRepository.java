package com.application.parts.repository;

import com.application.parts.model.Part;

import java.util.List;
import java.util.Map;

public interface PartRepository {

    /**
     * Filters parts by map of parameters
     * @param paramMap pairs (parameter, value) to be filtered
     * @return filtered list of parts
     */
    List<Part> filterParts(Map<String, String> paramMap);

}
