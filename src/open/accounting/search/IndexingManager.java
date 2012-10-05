package open.accounting.search;



import open.accounting.book.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexingManager {
	@Autowired
	private BookDao bookDao;
	
	public void storeBook(Book book) {
		//bookDao.save(book);
	}
	
}
