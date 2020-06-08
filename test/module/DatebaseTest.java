package module;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatebaseTest {
    private Datebase db = new Datebase();
    @Before
    public void setUp() throws Exception {
        Datebase.creatConnection();
    }

    @After
    public void tearDown() throws Exception {
        db.closeConnection();
    }

    @Test
    public void searchWordNull() {
        String word1 = "awnwims";
        Word result = db.searchWord(word1);
        assertNull(result);
    }

    @Test
    public void searchWordRight() {
        String word2 = "hello";
        Word result = db.searchWord(word2);
        Word right = new Word(word2, "★★★☆☆");
        right.setWordID(13904);
        right.addItem(new Item(26459, "[CONVENTION 惯用语] ", "哈罗;你好；", "[formulae]", "You say 'Hello' to someone when you meet them. "));
        right.getItems().get(right.getItems().size() - 1).addSentence(new Sentence("Hello, Trish...", "你好，特里茜。"));
        right.getItems().get(right.getItems().size() - 1).addSentence(new Sentence("Do you want to pop your head in and say hallo to my girlfriend?", "你要不要进来和我女友打个招呼？"));
        right.getItems().get(right.getItems().size() - 1).addTip("Hello is also a noun.The salesperson greeted me with a warm hello.售货员热情地和我打招呼。");

        right.addItem(new Item(26460, "[CONVENTION 惯用语] ", "打电话时的招呼语喂；", "[formulae]", "You say 'Hello' to someone at the beginning of a telephone conversation, either when you answer the phone or before you give your name or say why you are phoning. "));
        right.getItems().get(right.getItems().size() - 1).addSentence(new Sentence("A moment later, Cohen picked up the phone. 'Hello?'", "过了一会儿，科恩接起电话。“喂？”"));
        right.getItems().get(right.getItems().size() - 1).addSentence(new Sentence("Hallo, may I speak to Frank, please.", "喂，我找弗兰克。"));

        right.addItem(new Item(26461, "[CONVENTION 惯用语] ", "用于引起别人注意喂；", "", "You can call 'hello' to attract someone's attention. "));
        right.getItems().get(right.getItems().size() - 1).addSentence(new Sentence("She could see the open door of a departmental office. 'Hello! Excuse me. This is the department of French, isn't it?'...", "她看到一个系办公室的门开着。“喂！请问，这是法语系，对吗？”"));
        right.getItems().get(right.getItems().size() - 1).addSentence(new Sentence("Very softly, she called out: 'Hallo? Who's there?'", "她轻声细气喊道：“喂？有人吗？”"));

        assertEquals(right, result);
    }
}