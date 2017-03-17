package kr.pe.jady.wordict.word.service;

import com.google.common.collect.Iterables;
import kr.pe.jady.wordict.config.spring.app.AppConfig;
import kr.pe.jady.wordict.config.spring.app.DataSourceTestConfig;
import kr.pe.jady.wordict.word.model.Word;
import kr.pe.jady.wordict.word.system.exception.NotFoundException;
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

    @Test(expected = IllegalArgumentException.class)
    public void testAddWord() {
        Word add = new Word("add", "추가하다.");

        assertThat("단어 추가", wordService.add(add), is(add));
        wordService.add(new Word("add", "더하다.")); // 동일한 이름으로 단어를 추가할 수 없다. IllegalArgumentException 발생.
    }

    @Test(expected = NotFoundException.class)
    public void testFindById() {
        Word find = new Word("find", "찾다.");

        assertThat("추가된 단어를 아이디를 이용하여 검색", wordService.findById(wordService.add(find).getId()), is(find));
        wordService.findById(5000000L); // 아이디가 존재하지 않는 단어는 찾을 수 없다. NotFoundException 발생.
    }

    @Test
    public void testModifyDescription() {
        Word modify = new Word("modify", "수정하다.");
        Word foundWord = wordService.findById(wordService.add(modify).getId());

        assertThat("단어 설명 변경", wordService.modifyDescription(foundWord.getId(), "변경하다.").getDescription(), is("변경하다."));
    }

    @Test(expected = NotFoundException.class)
    public void testRemove() {
        Word delete = new Word("delete", "삭제하다.");
        Word addedWord = wordService.add(delete);

        assertThat("추가된 단어를 삭제한다.", wordService.remove(addedWord.getId()), is(addedWord));
        wordService.findById(addedWord.getId()); // 단어가 이미 삭제되었으므로 해당 단어는 찾을 수 없다. NotFoundException 발생.
    }

    @Test(expected = NotFoundException.class)
    public void testNotExistsWordRemove() {
        wordService.remove(5000000L); // 아이디가 존재하지 않는 단어는 찾아서 지울 수 없다. NotFoundException 발생.
    }

    @Test(expected = NotFoundException.class)
    public void testFindByName() {
        Word add = wordService.add(new Word("add", "추가하다."));
        Word find = wordService.add(new Word("find", "찾다."));
        Word modify = wordService.add(new Word("modify", "수정하다."));

        assertThat("추가된 단어(add)를 이름으로 찾는다.", wordService.findByName("add"), is(add));
        assertThat("추가된 단어(find)를 이름으로 찾는다.", wordService.findByName("find"), is(find));
        assertThat("추가된 단어(modify)를 이름으로 찾는다.", wordService.findByName("modify"), is(modify));
        wordService.findByName("monster"); // 추가되지 않은 단어는 찾을 수 없다. NotFoundException 발생.
    }

    @Test
    public void testFindAll() {
        wordService.add(new Word("add", "추가하다."));
        wordService.add(new Word("find", "찾다."));
        wordService.add(new Word("modify", "수정하다."));

        Iterable<Word> words = wordService.findAll();
        assertThat("추가된 모든 단어를 조회한다.", Iterables.size(words), is(3));
    }
}
