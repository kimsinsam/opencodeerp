package open.accounting.search;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import open.accounting.book.Book;

import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BookDao {
	/*@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void init() {
		super.setSessionFactory(sessionFactory);
	}
	
	public void save(Book book) {
		getHibernateTemplate().save(book);
	}
*/
	
}
