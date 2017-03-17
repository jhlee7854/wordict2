package kr.pe.jady.wordict.word.service.impl;

import com.mysema.query.BooleanBuilder;
import kr.pe.jady.wordict.model.QWord;
import kr.pe.jady.wordict.model.Word;
import kr.pe.jady.wordict.word.repository.WordRepository;
import kr.pe.jady.wordict.word.service.WordService;
import kr.pe.jady.wordict.system.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link kr.pe.jady.wordict.word.service.WordService}의 구현체
 * @author jhlee7854
 */
@Service
public class WordServiceImpl implements WordService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WordRepository wordRepository;

    @Override
    public Word add(Word word) {
        try {
            if (findByName(word.getName()) != null) throw new IllegalArgumentException("이미 존재하는 단어 입니다.");
        } catch (NotFoundException e) {
            logger.debug(e.getMessage());
        }
        return wordRepository.save(word);
    }

    @Override
    public Word findById(Long id) {
        Word foundWord = wordRepository.findOne(id);
        if (foundWord == null) throw new NotFoundException("'" + id + "' 아이디를 가진 단어를 찾을 수 없습니다.");
        return foundWord;
    }

    @Override
    public Word modifyDescription(Long id, String description) {
        Word foundWord = wordRepository.findOne(id);
        foundWord.setDescription(description);
        return wordRepository.save(foundWord);
    }

    @Override
    public Word remove(Long id) {
        Word foundWord = findById(id);
        wordRepository.delete(id);
        return foundWord;
    }

    @Override
    public Word findByName(String name) {
        BooleanBuilder spec = new BooleanBuilder();
        spec.and(QWord.word.name.eq(name));
        Word foundWord = wordRepository.findOne(spec);
        if (foundWord == null) throw new NotFoundException("'" + name + "' 이름을 가진 단어를 찾을 수 없습니다.");
        return foundWord;
    }

    @Override
    public Iterable<Word> findAll() {
        return wordRepository.findAll();
    }
}
