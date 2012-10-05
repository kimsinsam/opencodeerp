package open.accounting.foundation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * 설정사항에 대한 프로퍼티 파일을 읽어들여 쓸 수 있게 해주는 클래스.
 */
@Service
public class Config {
    private Resource configFile;
    
	private final Properties props = new Properties();

    @PostConstruct
    public void init() {
        load();
    }
    
    public synchronized void reload() {
        load();
    }
    
    private void load() {
        InputStream is = null;
        try {
            is = configFile.getInputStream();
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try { is.close(); } catch (Exception ie) {}
        }
    }
    
    public void set(String key, String value) {
        props.setProperty(key, value);
    }
    
    /**
     * key에 해당하는 값을 문자열형으로 가져온다. 값이 없는 경우 공백 문자열을 반환한다. 
     */
    public String get(String key) {
        return get(key, "");
    }
    
    /**
     * key에 해당하는 값을 문자열형으로 가져온다. 
     * 값이 없거나 공백일 경우 지정한 default 값을 반환한다. 
     */
    public String get(String key, String def) {
        return props.getProperty(key, def);
    }
    
    /**
     * key 에 해당하는 값을 가져올 때, 쉼표 문자(',') 또는 공백 문자를 구분자로 하여
     * 문자열 배열로 가져온다.
     */
    public String[] getArray(String key) {
        String originalValue = get(key, "");
        String[] returnValue;
        if (-1 == originalValue.indexOf(",")) { // ','가 없을 때 스페이스를 구분자로 분리
            returnValue = StringUtils.split(originalValue);
        } else {
            returnValue = StringUtils.split(originalValue, ',');
            for (int i = 0 ; i < returnValue.length ; i++) {
                returnValue[i] = returnValue[i].trim(); 
            }
        }
        return returnValue;
    }
    
    /**
     * int형으로 프로퍼티 값을 가져온다. 
     * 값이 없거나 공백, 또는 숫자형으로 변환되지 않을 때에는 0을 반환한다.
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }
    
    /**
     * int형으로 프로퍼티 값을 가져온다.
     * 값이 없거나 공백, 또는 숫자형으로 변환되지 않을 때에는 지정한 기본값이 반환된다.
     */
    public int getInt(String key, int def) {
        String temp = props.getProperty(key);
        if (null == temp) {
            return def;
        }
        int value = 0;
        try {
            value = Integer.parseInt(temp);
        } catch (Exception e) {
            value = def;
        }
        return value;
    }
    
    /**
     * boolean형으로 프로퍼티 값을 가져온다. 값이 "true", "yes", "on"인 경우에만 true를 리턴한다.
     * 값이 없거나 true가 아닌 경우는 false 이다.
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }
    
    /**
     * boolean형으로 프로퍼티 값을 가져온다. 값이 "true", "yes", "on"인 경우에만 true를 리턴한다.
     * 값이 없거나 형식이 맞지 않는 경우 지정한 기본값을 반환한다.
     */
    public boolean getBoolean(String key, boolean def) {
        String temp = props.getProperty(key);
        if (null == temp) {
            return def;
        }
        temp = temp.toLowerCase();
        if ("true".equals(temp) || "yes".equals(temp) || "on".equals(temp)) {
            return true;
        } else if ("false".equals(temp) || "no".equals(temp) || "off".equals(temp)) {
            return false;
        } else {
            return def;
        }
    }
    
    public Set<Object> getKeys() {
        return props.keySet();
    }
    
    public Properties getProperties() {
        return props;
    }

    @Value("/WEB-INF/config/config.properties")
	public void setConfigFile(Resource configFile) {
		this.configFile = configFile;
	}
}