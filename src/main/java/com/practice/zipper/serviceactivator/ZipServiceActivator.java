package com.practice.zipper.serviceactivator;

import com.practice.zipper.dto.ZipServiceRequest;
import com.practice.zipper.dto.ZipServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ZipServiceActivator {
    private static final Logger logger =
            LoggerFactory.getLogger(ZipServiceActivator.class);
    private static final String RESPONSE_MESSAGE_SUCCESS = "Success";

    public ZipServiceResponse zip(ZipServiceRequest request) {
        logger.info("Zip Request received {}", request.toString());
        ZipServiceResponse result = new ZipServiceResponse();

        // TODO: Zip implementation
        result.setResponseMessage(RESPONSE_MESSAGE_SUCCESS);
        return result;
    }

    public ZipServiceResponse unzip(ZipServiceRequest request) {
        logger.info("Unzip request received {}", request.toString());
        ZipServiceResponse result = new ZipServiceResponse();

        // TODO: Unzip implementation
        result.setResponseMessage(RESPONSE_MESSAGE_SUCCESS);
        return result;
    }
}
