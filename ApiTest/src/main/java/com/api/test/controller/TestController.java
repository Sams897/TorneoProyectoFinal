package com.api.test.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class TestController
{
    @RequestMapping(value="test", method = RequestMethod.GET)
    public String[] getTest(HttpServletRequest request)
    {
        String[] result = {"Test 01", "TEST 02", "Test 03", "test 04"};
        return result;
    }
}
