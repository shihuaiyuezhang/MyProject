package com.wiserv.util.scan;

public class ScanOnMethodListener implements ScanListener{

	public void onScanClass(Class<?> clazz) {
		System.out.println(clazz);
		
	}

}
