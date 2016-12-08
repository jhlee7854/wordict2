package kr.pe.jady.wordict.word.service.impl;

import kr.pe.jady.wordict.word.model.Word;
import kr.pe.jady.wordict.word.repository.WordRepository;
import kr.pe.jady.wordict.word.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link kr.pe.jady.wordict.word.service.WordService}의 구현체
 * @author jhlee7854
 */
@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private WordRepository wordRepository;

    @Override
    public Word add(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public Word findById(Long id) {
        return wordRepository.findOne(id);
    }

    @Override
    public Word modifyDescription(Long id, String description) {
        Word foundWord = wordRepository.findOne(id);
        foundWord.setDescription(description);
        return wordRepository.save(foundWord);
    }

    @Override
    public Word remove(Long id) {
        Word foundWord = wordRepository.findOne(id);
        wordRepository.delete(id);
        return foundWord;
    }
}
