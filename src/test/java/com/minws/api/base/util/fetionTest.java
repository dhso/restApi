package com.minws.api.base.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.minws.api.framework.util.FetionUtil;

public class fetionTest {
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		System.out.print(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); 
	}

}
