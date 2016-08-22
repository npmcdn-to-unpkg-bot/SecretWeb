package ca.tklab.secret.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import ca.tklab.secret.model.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

	public List<Article> findTop10ByWriterIdOrderByIdDesc(String wirterId);

	@Query("Select  a " 
			+ " from Article a " 
			+ " WHERE "
			+ "	writerId in ( SELECT id FROM Account WHERE id = :uid  ) " // my article
			+ " or  writerId in ( 	SELECT acceptantID " + "  	FROM Friend " 
			+ " 	WHERE requesterID = :uid  and accepted = true )" 
			+ " or  writerId in ( 	SELECT requesterID " + "  	FROM Friend "
			+ " 	WHERE acceptantID = :uid  and accepted = true )" 			
			+ " Order By id desc ")
	public List<Article> getArticlesByUID(@Param("uid") String wirterId, Pageable pageable);

	default List<Article> getArticlesByUID(String wirterId) {
		return getArticlesByUID(wirterId, new PageRequest(0, 10));
	}
}
