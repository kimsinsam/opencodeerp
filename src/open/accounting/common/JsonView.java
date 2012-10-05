package open.accounting.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (null == model) {
            throw new IOException();
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Gson gson = new GsonBuilder().serializeNulls().create();
        out.print( gson.toJson(model));
        out.flush();
    }

}
