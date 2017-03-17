package kr.pe.jady.wordict.word.service;

import kr.pe.jady.wordict.model.Word;

/**
 * Word Service
 * @author jhlee7854
 */
public interface WordService {
    /**
     * 시스템에 단어를 추가한다.
     * @param word 추가될 단어
     * @return 추가된 단어
     */
    Word add(Word word);

    /**
     * 시스템에 추가된 단어를 아이디를 이용하여 찾는다.
     * @param id 추가된 단어의 아이디
     * @return 찾은 단어
     */
    Word findById(Long id);

    /**
     * 시스템에 추가된 단어 중 제시된 아이디를 가진 단어의 설명을 변경한다.
     * @param id 설명을 변경할 단어의 아이디
     * @param description 변경할 단어 설명
     * @return 설명이 변경된 단어
     */
    Word modifyDescription(Long id, String description);

    /**
     * 시스템에 추가된 단어 중 제시된 아이디를 가진 단어를 제거한다.
     * @param id 제거할 단어의 아이디
     * @return 제거된 단어
     */
    Word remove(Long id);

    /**
     * 시스템에 추가된 단어 중 제시된 이름과 일차하는 단어를 찾는다.
     * @param name 시스템에 추가된 단어의 이름
     * @return 찾은 단어
     */
    Word findByName(String name);

    /**
     * 시스템에 추가된 모든 단어를 찾는다.
     * @return 찾은 단어들
     */
    Iterable<Word> findAll();
}
