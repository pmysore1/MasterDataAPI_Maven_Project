/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.lang.reflect.Field;
import java.io.*;
import java.util.*;

/**
 *
 * @author afitzwater1
 */
public class BasicObj implements Serializable {

    public String getAllFieldsShort() {
        return getAllFieldsShort(",");
    }

    public String getAllFieldsShort(String fs) {
        String s = "";
        try {
            String className = this.getClass().getName();
            // System.out.println("My class name is: " + className);
            Class cls = Class.forName(className);


            List fields = getAllFields(new LinkedList<Field>(), cls);
            Iterator itr = fields.iterator();
            int cnt = 0;
            while (itr.hasNext()) {
                if (cnt++ > 0) {
                    s += fs;
                }
                Field fld = (Field) itr.next();
                fld.setAccessible(true);
                String fldName = fld.getName();
                s += fldName + "=" + fld.get(this);
            }

        } catch (Throwable e) {
            System.err.println(e);
        }

        return s;
    }

    public String getAllFields(String fs) {
        String s = "";
        try {
            String className = this.getClass().getName();
            System.out.println("My class name is: " + className);
            Class cls = Class.forName(className);

            List fields = getAllFields(new LinkedList<Field>(), cls);
            Iterator itr = fields.iterator();
            int cnt = 0;
            while (itr.hasNext()) {

                Field fld = (Field) itr.next();
                fld.setAccessible(true);
                String fldName = fld.getName();
                s += " name = " + fldName
                        + ", value = " + fld.get(this) + fs;
            }

        } catch (Throwable e) {
            System.err.println(e);
        }

        return s;
    }

    public String getAllFields() {
        return getAllFields("\n");
    }

    public void setFieldStrValue(String fieldName, String fieldVal) {
        //  System.out.println("Setting field value:" + fieldName);
        try {
            // get field object for this item
            String className = this.getClass().getName();
            Class cls = Class.forName(className);
            Field fld = cls.getDeclaredField(fieldName);
            fld.setAccessible(true);

            // check field types ..
            if (fld.getType().getName().equals("java.lang.String")) {
                // set string value ..
                fld.set(this, fieldVal);
            } else if (fld.getType().getName().equals("double")) {
                //System.out.println(">>>>>>>>>>>>>>>>>>>>> DOUBLE" );
                double dblVal = Double.parseDouble(fieldVal);
                fld.set(this, dblVal);
            } else { // unhandled type
                System.out.println(">>>>>>>>>>>>>>>>>>>>> NOT A processed type "
                        + fld.getType().getName());
            }

        } catch (Throwable e) {
            System.err.println(e);
        }

    }

    public List<Field> getAllFields(List<Field> fields, Class<?> type) {
        for (Field field : type.getDeclaredFields()) {
            fields.add(field);
        }

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
