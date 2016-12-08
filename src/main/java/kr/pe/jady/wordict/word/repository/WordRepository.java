package kr.pe.jady.wordict.word.repository;

import kr.pe.jady.wordict.word.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Word Repository
 * @author jhlee7854
 */
@Repository
public interface WordRepository extends JpaRepository<Word, Long>, QueryDslPredicateExecutor<Word> {
}
