package com.practice.zipper.serviceactivator;

import com.practice.zipper.dto.ZipServiceRequest;
import com.practice.zipper.dto.ZipServiceResponse;
import com.practice.zipper.service.ZipperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ZipServiceActivator {
    private static final Logger logger =
            LoggerFactory.getLogger(ZipServiceActivator.class);
    private static final String RESPONSE_MESSAGE_SUCCESS = "Success";

    @Autowired
    ZipperService zipperService;

    public ZipServiceResponse zip(ZipServiceRequest request) {
        logger.info("Zip Request received {}", request.toString());
        ZipServiceResponse result = new ZipServiceResponse();

        // Compress files
        try {
            zipperService.compressFiles(
                    request.getInputDir(),
                    request.getOutputDir(),
                    request.getMaxSize());
        }
        catch (Exception e) {
            logger.error("Exception occurred while compressing", e);
        }
        result.setResponseMessage(RESPONSE_MESSAGE_SUCCESS);
        return result;
    }

    public ZipServiceResponse unzip(ZipServiceRequest request) {
        logger.info("Unzip request received {}", request.toString());
        ZipServiceResponse result = new ZipServiceResponse();

        // Decompress files
        try {
            zipperService.decompressFiles(
                    request.getInputDir(),
                    request.getOutputDir());
        } catch (IOException e) {
            logger.error("Exception occurred while decompressing", e);
        }

        result.setResponseMessage(RESPONSE_MESSAGE_SUCCESS);
        return result;
    }
}
