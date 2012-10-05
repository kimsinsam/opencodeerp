package open.accounting.common;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.view.AbstractView;

public class MessageShowView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(null == model) {
            response.sendRedirect("/");
            return ;
        }
        String code = (String) model.get("code");
        String url = "";
        String message = "";
        String isPopUp = "";
        //code는 없고 message만 있을때 적용
        if(model.containsKey("message")) {
            message = (String) model.get("message");
        }
        //인자 값을 받는다
        Object[] args = (Object[]) model.get("args");
        
        //message  뿌린후 원하는 url로 보낸다
        if (model.containsKey("returnUrl")) {
            url = (String) model.get("returnUrl");
        }
        
        //code가 있을때 해당되는 메세지를 불러온다
        if (null != code) {
            if (null == args) {
                message = getMessage(code);
            } else {
                message = getMessage(code, args);
            }

            //popUp 구분
            if (model.containsKey("popupClose")) {
                isPopUp = "1";
            }
        }
        
        // 웹 브라우저가 페이지 내용을 캐시하지 못하게 HTTP Header를 세팅한다.
        ServletUtils.noCache(response);
        setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        out.println("<script type=\"text/javascript\">");
        out.println("function end(url, message, isPopUp) {");
        out.println("   if(message != '') alert(message);");
        out.println("   if(url != '') {");
        out.println("       if(opener && isPopUp == '1') {");
        out.println("           opener.window.location.href = url;");
        out.println("           window.close();");
        out.println("       } else {");
        out.println("           window.location.href = url;");
        out.println("       }");
        out.println("   } else {");
        out.println("       if(isPopUp == '1') window.close();");
        out.println("       history.go(-1);");
        out.println("   }");
        out.println("}");
        //자바 스크립트 value mapping
        out.println("end('" + url + "', '" + message + "', '" + isPopUp + "');");
        
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }
    
    private String getMessage(String code) {
        try {
            return getApplicationContext().getMessage(code, null, Locale.KOREAN);
        } catch (NoSuchMessageException e) {
            return "System Error : code " + code;
        }
    }
    
    private String getMessage(String code, Object[] args) {
        try {
            return getApplicationContext().getMessage(code, args, Locale.KOREAN);
        } catch (NoSuchMessageException e) {
            return "System Error : code " + code;
        }
    }
}
