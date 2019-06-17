package com.wiserv.util.scan;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ScanPackage {
	
	
	private List<ScanListener> listenerList = new LinkedList<ScanListener>();
	
	

	/**
	 * 扫描packedge
	 * @param packageName
	 * @return
	 */
	public void scanPackge(String packageName) {
		 if (packageName == null || !packageName.matches("[\\w]+(\\.[\\w]+)*")){
	         throw new IllegalArgumentException("illegal package name");
	      }
	      String packageDirName = packageName.replace('.', '/');
	      Enumeration<URL> dirs = null;
	      try {
	    	  //关键点
	         dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);       
	         while (dirs.hasMoreElements()) {
	        
	            URL url = dirs.nextElement();
	            String protocol = url.getProtocol();	    
	            if ("file".equals(protocol)) {
	               String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
	               findAndAddClassesInPackageByFile(packageName, filePath);
	            }else if ("jar".equals(protocol)) {             
	               JarFile jar = null;
	               try {
	                  jar = ((JarURLConnection)url.openConnection()).getJarFile();	             
	                  Enumeration<JarEntry> entries = jar.entries();          
	                  while (entries.hasMoreElements()) {	                  
	                     JarEntry entry = entries.nextElement();
	                     String name = entry.getName();	             
	                     if (name.charAt(0) == '/') {             
	                        name = name.substring(1);
	                     }    
	                     if (name.startsWith(packageDirName)) {
	                        int idx = name.lastIndexOf('/');                      	   
	                        if (idx != -1) {
	                        	  packageName = name.substring(0, idx).replace('/', '.');
	                           if (name.endsWith(".class") && !entry.isDirectory()) {	                     
	                              String className = name.substring( packageName.length() + 1,name.length() - 6);
	                              try {	        
	                                 Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass( packageName + '.' + className);
	                                 triggerOnScanClass(clazz);
	                              }catch (ClassNotFoundException e) {
	                                 System.err.println("loading *.class error happended");
	                              }
	                           }
	                        }
	                     }
	                  }
	               }catch (Exception e) {
	                  System.err.println("scan error");
	                  e.printStackTrace();
	               }
	            }
	         }
	      } catch (IOException e) {
	         System.err.println("scan error");
	         e.printStackTrace();
	      }	
	}
	 /**
	    * 以文件的形式来获取包下的所有Class
	    * 
	    * @param packageName
	    * @param packagePath
	    */
	   private void findAndAddClassesInPackageByFile(String packageName,String packagePath) {
	      
	      File dir = new File(packagePath);
	      if (!dir.exists() || !dir.isDirectory()) {
	         System.err.println(packageName + " has no file");
	         return;
	      }
	      
	      File[] dirFiles = dir.listFiles((file) -> { return (file.isDirectory()) || (file.getName().endsWith(".class"));
	      });
	      for (File file : dirFiles) {     
	         if (file.isDirectory()) {
	            findAndAddClassesInPackageByFile(packageName + "." + file.getName(),file.getAbsolutePath());
	         }else {	         
	            String className = file.getName().substring(0,file.getName().length() - 6);
	            try {
	               Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
					triggerOnScanClass(clazz);
	            }
	            catch (ClassNotFoundException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	   }
	   /**
	    * 通知监听者
	    * @param clazz
	    */
	   private void triggerOnScanClass(Class<?> clazz) {
		   if (listenerList != null ){
			   for(ScanListener listener : listenerList) {
				   listener.onScanClass(clazz);
			   } 
		    }
	   }
	   
	   public void addListener(ScanListener listener) {
		   if (listenerList != null ){
			   listenerList.add(listener);
		   }
	   }
}
