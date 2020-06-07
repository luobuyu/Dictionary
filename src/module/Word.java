package module;

import java.util.ArrayList;

public class Word {
    private int wordID;
    private String word;
    private String stars;
    private ArrayList<Item> items;
    private ArrayList<String> see_also;

    public Word() {
        items = new ArrayList<>();
        see_also = new ArrayList<>();
    }

    public ArrayList<String> getSee_also() {
        return see_also;
    }

    public void setSee_also(ArrayList<String> see_also) {
        this.see_also = see_also;
    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public Word(String word, String stars) {
        this.word = word;
        this.stars = stars;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

}

class Item {
    private int itemID;
    private String label;
    private String word_ch;
    private String gram;
    private String explanation;
    private ArrayList<Sentence> sentences;
    private ArrayList<String> tips;
    public Item(int itemID, String label, String word_ch, String gram, String explanation) {
        this.itemID = itemID;
        this.label = label;
        this.word_ch = word_ch;
        this.gram = gram;
        this.explanation = explanation;
        sentences = new ArrayList<>();
        tips = new ArrayList<>();
    }

    public void addSentence(Sentence s) {
        this.sentences.add(s);
    }

    public void addTip(String tip) {
        this.tips.add(tip);
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<Sentence> sentences) {
        this.sentences = sentences;
    }

    public ArrayList<String> getTips() {
        return tips;
    }

    public void setTips(ArrayList<String> tips) {
        this.tips = tips;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getWord_ch() {
        return word_ch;
    }

    public void setWord_ch(String word_ch) {
        this.word_ch = word_ch;
    }

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}

class Sentence {
    private String sentence_en;
    private String sentence_ch;

    public Sentence(String sentence_en, String sentence_ch) {
        this.sentence_en = sentence_en;
        this.sentence_ch = sentence_ch;
    }

    public String getSentence_en() {
        return sentence_en;
    }

    public void setSentence_en(String sentence_en) {
        this.sentence_en = sentence_en;
    }

    public String getSentence_ch() {
        return sentence_ch;
    }

    public void setSentence_ch(String sentence_ch) {
        this.sentence_ch = sentence_ch;
    }
}
