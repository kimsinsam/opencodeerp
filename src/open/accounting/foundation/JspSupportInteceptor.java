package open.accounting.foundation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class JspSupportInteceptor extends HandlerInterceptorAdapter {
	private Config config;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView.getViewName().startsWith("redirect")) {
			return;
		}
		modelAndView.getModelMap().addAttribute("imageHost", config.get("imageHost"));
	}

	
	@Autowired
	public void setConfig(Config config) {
		this.config = config;
	}
}
