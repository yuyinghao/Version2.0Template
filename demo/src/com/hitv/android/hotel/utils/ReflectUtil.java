
/**   
 * @Title: ReflectUtil.java
 * @Package com.hitv.common.utils
 * @Description: TODO
 * @author hujunwen
 * @date 2014-9-11 
 *  
 */
  
package com.hitv.android.hotel.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;



/**
 * Title: ReflectUtil 
 * Description: ReflectUtil
 * @Copyright: Copyright (c) 2013
 * @author: hujunwen
 * @version: 1.0
 * @date 2014-9-11  
 */

public class ReflectUtil {

	/**    
	 
	 * java反射bean的get方法    
	 
	 *     
	 
	 * @param objectClass    
	 
	 * @param fieldName    
	 
	 * @return    
	 
	 */       
	  
	@SuppressWarnings("unchecked")       
	  
	public static Method getGetMethod(Class objectClass, String fieldName) {       
	  
	    StringBuffer sb = new StringBuffer();       
	  
	    sb.append("get");       
	  
	    sb.append(fieldName.substring(0, 1).toUpperCase());       
	  
	    sb.append(fieldName.substring(1));       
	  
	    try {       
	  
	        return objectClass.getMethod(sb.toString());       
	  
	    } catch (Exception e) {       
	  
	    }       
	  
	    return null;
	  
	}       
	  
	       
	  
	public static Object getFieldValue(Object obj,String fieldName){
		
		try {
			Field	field = obj.getClass().getField(fieldName);
			return field.get(obj);////
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
		return null;
	}
	
	/**    
	 
	 * java反射bean的set方法    
	 
	 *     
	 
	 * @param objectClass    
	 
	 * @param fieldName    
	 
	 * @return    
	 
	 */       
	  
	@SuppressWarnings("unchecked")       
	  
	public static Method getSetMethod(Class objectClass, String fieldName) {     
	  
	    try {       
	  
	        Class[] parameterTypes = new Class[1];       
	        Field field = objectClass.getDeclaredField(fieldName);     
	        parameterTypes[0] = field.getType();
	        StringBuffer sb = new StringBuffer();
	        sb.append("set");       
	        sb.append(fieldName.substring(0, 1).toUpperCase());       
	        sb.append(fieldName.substring(1));       
	        Method method = objectClass.getMethod(sb.toString(), field.getType());       
	        return method;       
	  
	    } catch (Exception e) {       
	  
	        e.printStackTrace();       
	  
	    }       
	  
	    return null;       
	  
	}       
	  
	@SuppressWarnings("unchecked")       
	  
	public static Method getFieldMethod(Class objectClass, String fieldName) {     
	  
	    try {       
	  
//	        Class[] parameterTypes = new Class[1];    
	        Field field = objectClass.getDeclaredField(fieldName);     
	        Method method = objectClass.getMethod(fieldName,field.getType());       
	        return method;       
	  
	    } catch (Exception e) {       
	  
	        e.printStackTrace();       
	  
	    }       
	  
	    return null;       
	  
	}        
	  
	/**    
	 
	 * 执行set方法    
	 
	 *     
	 
	 * @param o执行对象    
	 
	 * @param fieldName属性    
 
 * @param value值    
 
 */       
  
public static void invokeSet(Object o, String fieldName, Object value) {       
  
    Method method = getSetMethod(o.getClass(), fieldName);       
  
    try {       
  
        method.invoke(o, new Object[] { value });       
  
    } catch (Exception e) {       
  
        e.printStackTrace();       
  
    }       
  
}       
  
       
  
	/**    
	 
	 * 执行get方法    
	 
	 *     
	 
	 * @param o执行对象    
	 
	 * @param fieldName属性    
	 
	 */       
	  
	public static Object invokeGet(Object o, String fieldName) {       
	  
	    Method method = getGetMethod(o.getClass(), fieldName);       
	  
	    try {       
	  
	        return method.invoke(o, new Object[0]);       
	  
	    } catch (Exception e) {       
	  
	        e.printStackTrace();       
	  
	    }       
	  
	    return null;       
	  
	}  
	
	
	public static Class<?> createClass(String className){
		
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	public static Object createInstance(String factoryClassName){
		
		   try {
			return Class.forName(factoryClassName).newInstance();
		   } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   } catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   
		   return null;
		       
	}

}
