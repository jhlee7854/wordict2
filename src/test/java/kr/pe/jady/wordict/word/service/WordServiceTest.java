package kr.pe.jady.wordict.word.service;

import kr.pe.jady.wordict.word.config.spring.app.AppConfig;
import kr.pe.jady.wordict.word.config.spring.app.DataSourceTestConfig;
import kr.pe.jady.wordict.word.model.Word;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NamingException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Word Service Test
 * @author jhlee7854
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
public class WordServiceTest {
    @Autowired
    private WordService wordService;

    @BeforeClass
    public static void setUpClass() throws NamingException {
        DataSourceTestConfig.buildNamingContext();
    }

    @Before
    public void setUp() {
        assertThat("테스트 대상 확인", wordService, is(notNullValue()));
    }

    @Test
    public void testAddWord() {
        Word add = new Word("add", "추가하다.");

        assertThat("단어 추가", wordService.add(add), is(add));
    }

    @Test
    public void testFindById() {
        Word find = new Word("find", "찾다.");

        assertThat("추가된 단어를 아이디를 이용하여 검색", wordService.findById(wordService.add(find).getId()), is(find));
    }

    @Test
    public void testModifyDescription() {
        Word modify = new Word("modify", "수정하다.");
        Word foundWord = wordService.findById(wordService.add(modify).getId());

        assertThat("단어 설명 변경", wordService.modifyDescription(foundWord.getId(), "변경하다.").getDescription(), is("변경하다."));
    }

    @Test
    public void testRemove() {
        Word delete = new Word("delete", "삭제하다.");
        Word addedWord = wordService.add(delete);

        assertThat("추가된 단어를 삭제한다.", wordService.remove(addedWord.getId()), is(addedWord));
        assertThat("단어가 이미 삭제되었으므로 해당 단어를 찾을 수 없다.", wordService.findById(addedWord.getId()), is(nullValue()));
    }
}
