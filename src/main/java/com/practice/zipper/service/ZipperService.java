package com.practice.zipper.service;

import com.practice.zipper.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipperService {
    @Autowired
    Utilities utilities;

    public void compressFiles(
            String inputDir,
            String outputDir,
            int maxSize) {

    }

    public void decompressFiles(
            String inputDir,
            String outputDir,
            int maxSize) {

    }
}
