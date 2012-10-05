package open.accounting.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

public class ParameterParser {
    private ServletRequest req;

    public ParameterParser(ServletRequest req) {
        this.req = req;
    }

    /**
     * 파라미터를 String 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @return 파라미터가 없을 경우 null
     */
    public String get(String name) {
        String value = req.getParameter(name);
        if (null != value && 0 == value.length()) {
            return null;
        }
        return StringUtils.trim(value);
    }

    /**
     * 파라미터를 String 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @param def 디폴트 값
     * @return 파라미터가 없을 경우 디폴트
     */
    public String get(String name, String def) {
        String value = get(name);
        if (null == value) {
            return def;
        }
        return StringUtils.trim(value);
    }

    /**
     * 파라미터를 boolean 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @return 파라미터가 없을 경우 false
     */
    public boolean getBoolean(String name) {
        String value = get(name);
        if (null == value || 0 == value.length()) {
            return false;
        }
        value = value.toLowerCase();
        if ((value.equalsIgnoreCase("true")) || (value.equalsIgnoreCase("on")) || (value.equalsIgnoreCase("yes"))) {
            return true;
        } else if ((value.equalsIgnoreCase("false")) || (value.equalsIgnoreCase("off")) || (value.equalsIgnoreCase("no"))) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * 파라미터를 boolean 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @param def 디폴트
     * @return 파라미터가 없을 경우 디폴트
     */
    public boolean getBoolean(String name, boolean def) {
        String value = get(name);
        if (null == value || 0 == value.length()) {
            return def;
        }
        value = value.toLowerCase();
        if ((value.equalsIgnoreCase("true")) || (value.equalsIgnoreCase("on")) || (value.equalsIgnoreCase("yes"))) {
            return true;
        } else if ((value.equalsIgnoreCase("false")) || (value.equalsIgnoreCase("off")) || (value.equalsIgnoreCase("no"))) {
            return false;
        } else {
            return def;
        }
    }

    /**
     * 파라미터를 int 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @return 파라미터가 없을 경우, 예외 발생시 0
     */
    public int getInt(String name) {
        String value = get(name);
        if (null == value || 0 == value.length()) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 파라미터를 int 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @param def 디폴트
     * @return 파라미터가 없을 경우, 예외 발생시 디폴트
     */
    public int getInt(String name, int def) {
        String value = get(name);
        if (null == value || 0 == value.length()) {
            return def;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return def;
        }
    }
    
    /**
     * 파라미터를 int 타입으로 얻는다. 3자리마다 붙는 쉼표(,)를 삭제한다.
     * 
     * @param name 파라미터 key
     * @return 파라미터가 없을 경우, 예외 발생시 0
     */
    public int getCurrency(String name) {
        return getCurrency(name, 0);
    }
    
    /**
     * 파라미터를 int 타입으로 얻는다. 3자리마다 붙는 쉼표(,)를 삭제한다.
     * 
     * @param name 파라미터 key
     * @param def 디폴트
     * @return 파라미터가 없을 경우, 예외 발생시 디폴트
     */
    public int getCurrency(String name, int def) {
        String value = get(name);
        if (null == value) {
            return def;
        }
        int length = value.length();
        char c = 0;
        StringBuffer sbf = new StringBuffer();
        for (int i = 0 ; i < length ; i++) {
            c = value.charAt(i);
            if ('-' == c || ('0' <= c && c <= '9')) {
                sbf.append(c);
            } else if (',' == c) {
                continue;
            } else {
                return def;
            }
        }
        try {
            return Integer.parseInt(sbf.toString());
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 파라미터를 long 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @return 파라미터가 없을 경우, 예외 발생시 0
     */
    public long getLong(String name) {
        String value = get(name);
        if (null == value || 0 == value.length()) {
            return 0;
        }
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 파라미터를 int 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @param def 디폴트
     * @return 파라미터가 없을 경우, 예외 발생시 디폴트
     */
    public long getLong(String name, long def) {
        String value = get(name);
        if (null == value || 0 == value.length()) {
            return def;
        }
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return def;
        }
    }
    
    /**
     * 파라미터를 Date 형으로 읽는다. "yyyy-MM-dd" 형식이다.
     * 파라미터가 없거나 형식이 맞지 않는 경우 현재 시간을 사용한다.
     */
    public Date getDate(String name) {
        return getDate(name, new Date());
    }
    
    /**
     * 파라미터를 Date 형으로 읽는다. "yyyy-MM-dd" 형식이다.
     */
    public Date getDate(String name, Date def) {
        String temp = get(name);
        if (null == temp) {
            return def;
        }
        try {
            return DateUtils.getDateFormat("yyyy-MM-dd").parse(temp);
        } catch (Exception e) {
            return def;
        }
    }
    
    public Date getDateTime(String name) {
        return getDateTime(name, new Date());
    }
    
    public Date getDateTime(String name, Date def) {
        String temp = get(name);
        if (null == temp) {
            return def;
        }
        try {
            return DateUtils.getDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(temp);
        } catch (Exception e) {
            return def;
        }
    }
    /**
     * 파라미터를 Date 형으로 읽는다. "yyyy-MM-dd HH:mm" 형식이다.
     */
    public Date getDateHour(String name) {
        return getDateHour(name, new Date());
    }
    
    public Date getDateHour(String name, Date def) {
        String temp = get(name);
        if (null == temp) {
            return def;
        }
        try {
            return DateUtils.getDateFormat("yyyy-MM-dd HH:mm").parse(temp);
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 파라미터를 String array 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @return String Array
     */
    public String[] getArray(String name) {
        List<String> list = new ArrayList<String> ();
        String[] tempArray = req.getParameterValues(name);
        if (null == tempArray) {
            return null;
        }
        for (int i = 0; i < tempArray.length; i++) {
            if (null != tempArray[i] && 0 != tempArray[i].length()) {
                list.add(tempArray[i]);
            }
        }
        String[] returnArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            returnArray[i] = (String) list.get(i);
        }
        return returnArray;
    }
    
    /**
     * 파라미터를 integer array 타입으로 얻는다.
     * 
     * @param name 파라미터 key
     * @return integer Array
     */
    public int[] getIntArray(String name) {
        String[] tempArray = req.getParameterValues(name);
        if (null == tempArray) {
            return null;
        }
        int len = tempArray.length;
        int[] result = new int[len];
        int temp = 0;
        for (int i = 0 ; i < len ; i++) {
            try {
                temp = Integer.parseInt(tempArray[i]);
            } catch (Exception e) {
                temp = 0;
            }
            result[i] = temp;
        }
        return result;
    }
    
    /**
     * 인자로 들어온 문자열 배열에 포함된 이름들을 제외한 파라미터의 
     * 이름 - 값 맵을 만든다. 모든 값은 문자열로 취급하며 같은 이름으로
     * 여러 값이 온 경우 처음 값을 제외하고 모두 무시한다. 여기서 어느 것이
     * 처음 값이 될지는 정해지지 않았으며 서블릿 컨테이너에 의존적이다.
     * 
     * @param exclusion 제외시킬 파라미터 이름들. null 이면 모두 포함
     * @return 파라미터 이름 - 값 맵
     */
    public Map toMap(String[] exclusions) {
        Map result = new HashMap();
        Iterator it = req.getParameterMap().entrySet().iterator();
        Map.Entry entry = null;
        String[] value = null;
        while (it.hasNext()) {
            entry = (Map.Entry) it.next();
            value = (String[]) entry.getValue();
            if (null != value && value.length > 0) {
                result.put(entry.getKey(), value[0]);
            }
        }
        int sz = 0;
        if (null != exclusions && (sz = exclusions.length) > 0) {
            for (int i = 0 ; i < sz ; i++) {
                result.remove(exclusions[i]);
            }
        }
        return result;
    }
    
    /**
     * 해당 Object의 public 필드를 파라미터 이름과 매핑시켜 값을 넣는다.
     * @deprecated
     */
    public void copyTo(Object entity) {
        Class c = entity.getClass();
        Field[] fields = c.getFields();
        String fieldType = null;
        String fieldName = null;
        try {
            for (int i = 0 ; i < fields.length ; i++) {
                fieldType = fields[i].getType().getName();
                fieldName = fields[i].getName();
                
                if ("int".equals(fieldType)) {
                    fields[i].setInt(entity, getInt(fieldName, fields[i].getInt(entity)));
                } else if ("long".equals(fieldType)) {
                    fields[i].setLong(entity, getLong(fieldName, fields[i].getLong(entity)));
                } else if ("boolean".equals(fieldType)) {
                    fields[i].setBoolean(entity, getBoolean(fieldName, fields[i].getBoolean(entity)));
                } else if ("java.lang.String".equals(fieldType)) {
                    fields[i].set(entity, get(fieldName, (String)fields[i].get(entity)));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public String toString() {
        StringBuffer sbf = new StringBuffer();
        sbf.append('{');
        Enumeration en = req.getParameterNames();
        String name = null;
        String[] value = null;
        int i = 0;
        while (en.hasMoreElements()) {
            name = (String)en.nextElement();
            sbf.append("name= ");
            value = req.getParameterValues(name);
            if (null == value || 0 == value.length) {
                sbf.append(';');
                continue;
            }
            if (1 == value.length) {
                sbf.append(value);
            } else {
                sbf.append('[');
                sbf.append(value[0]);
                for (i = 1 ; i < value.length ; i++) {
                    sbf.append(",");
                    sbf.append(value[i]);
                }
                sbf.append(']');
            }
            sbf.append(';');
        }
        sbf.append('}');
        return sbf.toString();           
    }
    
    public String paramDebug() {
        StringBuffer sbf = new StringBuffer();
        sbf.append('{');
        Enumeration en = req.getParameterNames();
        String name = null;
        String[] value = null;
        int i = 0;
        while (en.hasMoreElements()) {
            name = (String)en.nextElement();
            sbf.append(name).append(" : ");
            value = req.getParameterValues(name);
            if (null == value || 0 == value.length) {
                sbf.append(';');
                continue;
            }
            if (1 == value.length) {
                sbf.append(value[0]);
            } else {
                sbf.append('[');
                sbf.append(value[0]);
                for (i = 1 ; i < value.length ; i++) {
                    sbf.append(",");
                    sbf.append(value[i]);
                }
                sbf.append(']');
            }
            sbf.append(';');
        }
        sbf.append('}');
        return sbf.toString();           
    }
}
