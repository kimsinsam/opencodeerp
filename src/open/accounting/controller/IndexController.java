package open.accounting.controller;

import open.accounting.search.IndexingManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@Autowired
	private IndexingManager indexingManager;
	
	@RequestMapping(value = "/main/index")
	public String index(ModelMap modelMap) {
		return "/index/main";
	}
}
