package com.minws.api.base.util;

import java.text.NumberFormat;
import java.util.Arrays;

import com.minws.api.framework.email.SmtpEmailUtil;

public class Test {

	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(1);//这个1的意识是保存结果到小数点后几位，但是特别声明：这个结果已经先＊100了。
      System.out.println(nf.format(0.12345));
	}
	}
