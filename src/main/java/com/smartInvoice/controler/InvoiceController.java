package com.smartInvoice.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> hello()
    {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

}