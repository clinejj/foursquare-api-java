/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class responsible of parsing API responses
 * 
 * @author Antti Leppä
 */
public class JSONFieldParser {

  /**
   * Utility class so no constructor needed.
   */
  private JSONFieldParser() {
    
  }
  
  /**
   * Static method that parses JSON array into array of FoursquareEntities
   * 
   * @param clazz entity class
   * @param jsonArray JSON Array
   * @param skipNonExistingFields whether parser should ignore non-existing fields
   * @return list of entities
   * @throws FoursquareApiException when something unexpected happens
   */
  public static FoursquareEntity[] parseEntities(Class<?> clazz, JSONArray jsonArray, boolean skipNonExistingFields) throws FoursquareApiException {
    FoursquareEntity[] result = (FoursquareEntity[]) Array.newInstance(clazz, jsonArray.length());
    
    for (int i = 0, l = jsonArray.length(); i < l; i++) {
      JSONObject jsonObject;
      try {
        jsonObject = jsonArray.getJSONObject(i);
        result[i] = parseEntity(clazz, jsonObject, skipNonExistingFields);
      } catch (JSONException e) {
        throw new FoursquareApiException(e);
      }
    }
    
    return result;
  }
  
  /**
   * Static method that parses JSON "named array" into array of FoursquareEntities
   * 
   * @param clazz entity class
   * @param jsonHashList JSON "named array"
   * @param skipNonExistingFields whether parser should ignore non-existing fields
   * @return list of entities
   * @throws FoursquareApiException when something unexpected happens
   */
  public static FoursquareEntity[] parseEntitiesHash(Class<?> clazz, JSONObject jsonHashList, boolean skipNonExistingFields) throws FoursquareApiException {
    String[] keys = getFieldNames(jsonHashList);
    
    FoursquareEntity[] result = (FoursquareEntity[]) Array.newInstance(clazz, keys.length);
    int i = 0;
    for (String key : keys) {
      JSONObject jsonObject;
      try {
        jsonObject = jsonHashList.getJSONObject(key);
        result[i++] = parseEntity(clazz, jsonObject, skipNonExistingFields);
      } catch (JSONException e) {
        throw new FoursquareApiException(e);
      }
    }
    
    return result;
  }
  
  /**
   * Static method that parses single JSON Object into FoursquareEntity
   * 
   * @param clazz entity class
   * @param jsonObject JSON Object
   * @param skipNonExistingFields whether parser should ignore non-existing fields
   * @return entity
   * @throws FoursquareApiException when something unexpected happens
   */
  public static FoursquareEntity parseEntity(Class<?> clazz, JSONObject jsonObject, boolean skipNonExistingFields) throws FoursquareApiException {
    FoursquareEntity entity = createNewEntity(clazz);
    
    String[] objectFieldNames = getFieldNames(jsonObject);
    if (objectFieldNames != null) {
      for (String objectFieldName : objectFieldNames) {
        Class<?> fieldClass = getFieldClass(entity.getClass(), objectFieldName);
        if (fieldClass == null) {
          Method setterMethod = getSetterMethod(entity.getClass(), objectFieldName);
          if (setterMethod == null) {
            if (!skipNonExistingFields) {
              throw new FoursquareApiException("Could not find field " + objectFieldName + " from " + entity.getClass().getName() + " class");
            }
          } else {
            Class<?>[] parameters = setterMethod.getParameterTypes();
            if (parameters.length == 1) {
              fieldClass = parameters[0];
              
              try {
                setterMethod.setAccessible(true);
                setterMethod.invoke(entity, parseValue(fieldClass, jsonObject, objectFieldName, skipNonExistingFields));
              } catch (JSONException e) {
                throw new FoursquareApiException(e);
              } catch (IllegalArgumentException e) {
                throw new FoursquareApiException(e);
              } catch (IllegalAccessException e) {
                throw new FoursquareApiException(e);
              } catch (InvocationTargetException e) {
                throw new FoursquareApiException(e);
              }
            } else {
              throw new FoursquareApiException("Could not find field " + objectFieldName + " from " + entity.getClass().getName() + " class");
            }
          }
        } else {
          try {
            setEntityFieldValue(entity, objectFieldName, parseValue(fieldClass, jsonObject, objectFieldName, skipNonExistingFields));
          } catch (JSONException e) {
            throw new FoursquareApiException(e);
          }
        }
      }
    }
    
    return entity;
  }
  
  /**
   * Parses single JSON object field into a value. Value might be of type String, Integer, Long, Double, Boolean or FoursquareEntity depending classes field type
   * 
   * @param clazz class
   * @param jsonObject JSON Object
   * @param objectFieldName field to be parsed
   * @param skipNonExistingFields whether parser should ignore non-existing fields
   * @return field's value
   * @throws JSONException when JSON parsing error occures
   * @throws FoursquareApiException when something unexpected happens
   */
  private static Object parseValue(Class<?> clazz, JSONObject jsonObject, String objectFieldName, boolean skipNonExistingFields) throws JSONException, FoursquareApiException {
    if (clazz.isArray()) {
	  Object value = jsonObject.get(objectFieldName);
	  
	  JSONArray jsonArray;
	  if(value instanceof JSONArray) {
        jsonArray = (JSONArray)value;
	  } else {
	    if((value instanceof JSONObject) && (((JSONObject)value).has("items"))) {
	      jsonArray = ((JSONObject)value).getJSONArray("items");
	    }
	    else {
	      throw new FoursquareApiException("JSONObject[\"" + objectFieldName + "\"] is neither a JSONArray nor a {count, items} object.");
	    }
	  }

      Class<?> arrayClass = clazz.getComponentType();
      Object[] arrayValue = (Object[]) Array.newInstance(arrayClass, jsonArray.length());
      
      for (int i = 0, l = jsonArray.length(); i < l; i++) {
        if (arrayClass.equals(String.class)) {
          arrayValue[i] = jsonArray.getString(i);
        } else if (arrayClass.equals(Integer.class)) {
          arrayValue[i] = jsonArray.getInt(i);  
        } else if (arrayClass.equals(Long.class)) {
          arrayValue[i] = jsonArray.getLong(i);  
        } else if (arrayClass.equals(Double.class)) {
          arrayValue[i] = jsonArray.getDouble(i);  
        } else if (arrayClass.equals(Boolean.class)) {
          arrayValue[i] = jsonArray.getBoolean(i);  
        } else if (isFoursquareEntity(arrayClass)) {
          arrayValue[i] = parseEntity(arrayClass, jsonArray.getJSONObject(i), skipNonExistingFields);
        } else {
          throw new FoursquareApiException("Unknown array type: " + arrayClass);
        }
      }
      
      return arrayValue;
    } else if (clazz.equals(String.class)) {
      return jsonObject.getString(objectFieldName);
    } else if (clazz.equals(Integer.class)) {
      return jsonObject.getInt(objectFieldName);
    } else if (clazz.equals(Long.class)) {
      return jsonObject.getLong(objectFieldName);
    } else if (clazz.equals(Double.class)) {
      return jsonObject.getDouble(objectFieldName);
    } else if (clazz.equals(Boolean.class)) {
      return jsonObject.getBoolean(objectFieldName);
    } else if (isFoursquareEntity(clazz)) {
      return parseEntity(clazz, jsonObject.getJSONObject(objectFieldName), skipNonExistingFields); 
    } else {
      throw new FoursquareApiException("Unknown type: " + clazz);
    }
  }
  
  /**
   * Returns whether class is derived from FoursquareEntity interface
   * 
   * @param clazz class
   * @return whether class is derived from FoursquareEntity interface
   */
  private static boolean isFoursquareEntity(Class<?> clazz) {
    for (Class<?> intrf : clazz.getInterfaces()) {
      if (intrf.equals(FoursquareEntity.class)) {
        return true;
      }
    }
    
    Class<?> superClass = clazz.getSuperclass();
    if (!superClass.equals(Object.class)) {
      return isFoursquareEntity(superClass);
    }
    
    return false;
  }
  
  /**
   * Returns field of class
   * 
   * @param entityClass class
   * @param fieldName field
   * @return Field
   */
  private static Field getField(Class<?> entityClass, String fieldName) {
    try {
      Field field = entityClass.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field;
    } catch (SecurityException e) {
      return null;
    } catch (NoSuchFieldException e) {
      Class<?> superClass = entityClass.getSuperclass();
      if (superClass.equals(Object.class)) {
        return null;
      } else {
        return getField(superClass, fieldName);
      }
    }
  } 
  
  /**
   * Returns list of all methods in a class
   * 
   * @param entityClass class
   * @return list of all methods in a class
   */
  private static List<Method> getMethods(Class<?> entityClass) {
    List<Method> result = new ArrayList<Method>(Arrays.asList(entityClass.getDeclaredMethods()));
    if (!entityClass.getSuperclass().equals(Object.class)) {
      result.addAll(getMethods(entityClass.getSuperclass()));
    }    
    
    return result;
  }
  
  /**
   * Returns setter method for a field
   * 
   * @param entityClass class
   * @param fieldName field
   * @return setter method for a field
   */
  private static Method getSetterMethod(Class<?> entityClass, String fieldName) {
    StringBuilder methodNameBuilder = new StringBuilder();
    methodNameBuilder.append("set");
    methodNameBuilder.append(Character.toUpperCase(fieldName.charAt(0)));
    methodNameBuilder.append(fieldName.substring(1));
    
    String methodName = methodNameBuilder.toString();
    
    List<Method> methods = getMethods(entityClass);
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        return method;
      }
    }
    
    return null;
  }
  
  /**
   * Returns class for class's field
   * 
   * @param entityClass class
   * @param fieldName field
   * @return class's field's class
   */
  private static Class<?> getFieldClass(Class<?> entityClass, String fieldName) {
    Field field = getField(entityClass, fieldName);
    return field != null ? field.getType() : null;
  } 
  
  /**
   * Sets entity's field's value
   * 
   * @param entity entity
   * @param fieldName field
   * @param value value
   * @throws FoursquareApiException when something unexpected happens
   */
  private static void setEntityFieldValue(FoursquareEntity entity, String fieldName, Object value) throws FoursquareApiException {
    java.lang.reflect.Field fieldProperty;
    try {
      fieldProperty = getField(entity.getClass(), fieldName);
      fieldProperty.setAccessible(true);
      fieldProperty.set(entity, value);
    } catch (Exception e) {
      throw new FoursquareApiException(e);
    }
  }
  
  /**
   * Initializes new entity instance
   * 
   * @param clazz class
   * @return new entity instance
   */
  private static FoursquareEntity createNewEntity(Class<?> clazz) {
    try {
      return (FoursquareEntity) clazz.newInstance();
    } catch (InstantiationException e) {
      return null;
    } catch (IllegalAccessException e) {
      return null;
    }
  }
  
  /**
   * Returns JSONObject's field names. 
   * 
   * This method is used instead of JSONObject.getNames -method because 
   * Android's org.json implementation does not contain that method.
   * 
   * @param jsonObject JSONObject
   * @return JSONObject's field names
   */
  private static String[] getFieldNames(JSONObject jsonObject) {
    int length = jsonObject.length();
    if (length == 0) {
      return null;
    }
    
    Iterator<?> iterator = jsonObject.keys();
    String[] names = new String[length];
    int i = 0;

    while (iterator.hasNext()) {
      names[i] = (String) iterator.next();
      i += 1;
    }
    
    return names;
  }
}
