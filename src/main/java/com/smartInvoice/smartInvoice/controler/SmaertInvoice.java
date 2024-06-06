package com.smartInvoice.smartInvoice.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/invoice")
public class SmaertInvoice {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> Ellow() {
        return new ResponseEntity<>("hello invice", HttpStatus.OK);
    }


}
