package open.accounting.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

/**
 * Servlet에서 쓰이는 유틸 클래스.
 * 
 */
public class ServletUtils {
    
    /**
     * 웹 브라우저가 페이지 내용을 캐시하지 못하게 HTTP Header를 세팅한다.
     */
    public static void noCache(HttpServletResponse response) {
        // response.addHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Expires", "-1");
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "must-revalidate, no-store, no-cache");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
    }

    
    public static void createCookie(String name, String value, String domain, int age, HttpServletResponse response) {
		Cookie idSaveCookie = new Cookie(name, Base64.encodeBase64URLSafeString(value.getBytes()));
		idSaveCookie.setDomain("." + domain);
		idSaveCookie.setPath("/");
		idSaveCookie.setMaxAge(age);
		response.addCookie(idSaveCookie);
    }
}
