package com.practice.zipper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZipServiceRequest {
    @JsonProperty
    private String inputDir;

    @JsonProperty
    private String outputDir;

    @JsonProperty
    private String maxSize;

    public String getInputDir() {
        return inputDir;
    }

    public void setInputDir(String inputDir) {
        this.inputDir = inputDir;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public String toString() {
        return "ZipServiceRequest{" +
                "inputDir='" + inputDir + '\'' +
                ", outputDir='" + outputDir + '\'' +
                ", maxSize='" + maxSize + '\'' +
                '}';
    }
}
