package open.accounting.common;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;

public class SpecialViewResolver extends AbstractCachingViewResolver implements Ordered {
    private int order;

    
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        if(viewName.equals(":message")) {
            MessageShowView messageShowView = new MessageShowView();
            messageShowView.setApplicationContext(getApplicationContext());
            return messageShowView;
        } else if(viewName.equals(":json")) {
            JsonView jsonView = new JsonView();
            jsonView.setApplicationContext(getApplicationContext());
            return jsonView;
        } else if (viewName.equals(":excel")) {
        	ExcelGridView excelGridView = new ExcelGridView();
        	excelGridView.setApplicationContext(getApplicationContext());
        	return excelGridView;
        }
        return null;
    }


}
