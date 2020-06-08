package module;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Dictionary {
    private static String name = "柯林斯词典";
    private static Datebase datebase = null;

    public Dictionary() {
        datebase = new Datebase();
        Datebase.creatConnection();
    }

    public String getName() {
        return Dictionary.name;
    }

    public boolean check(String word) {
        String right = "abcdefghijklmnopqrstuvwxyz'- ";
        word = word.toLowerCase();
        for(int i = 0; i < word.length(); i++) {
            if(right.indexOf(word.charAt(i)) == -1) return false;
        }
        return true;
    }

    public void searchWord(String word) {
        Word result = datebase.searchWord(word);

        if(result == null) {
            System.out.println("查询失败，找不到该单词！");
            return;
        } else {
            System.out.println("查询结果为：" + result.getWord());

        }
        if(result.getStars().equals("")) {
            result.setStars("☆☆☆☆☆");
        }
        System.out.println(result.getWord() + "，重要程度：" + result.getStars());
        System.out.println("---------------词意--------------");
        for(int i = 0; i < result.getItems().size(); i++) {
            Item item = result.getItems().get(i);
            System.out.print("【" + (i + 1) + "】");
            System.out.println(item.getLabel() + " " + item.getWord_ch());
            System.out.println(item.getExplanation() + " " + item.getGram());

            for(int j = 0; j < item.getSentences().size(); j++) {
                Sentence sentence = item.getSentences().get(j);
                System.out.println("·" + sentence.getSentence_en());
                System.out.println(" " + sentence.getSentence_ch());
            }
            String[] arr1, arr2;
            for(int j = 0; j < item.getTips().size(); j++) {
                String temp = item.getTips().get(j);
                arr1 = temp.split("。");
                for(int k = 0; k < arr1.length; k++) {
                    arr2 = arr1[k].split("\\.");
                    int cnt = 0;
                    for(int m = 0; m < arr2.length; m++) {
                        if(!arr2[m].equals("")) {
                            if(m == 0 && k == 0) {
                                System.out.println("·" + arr2[m]);
                            } else {
                                cnt++;
                                if(cnt % 2 == 1){
                                    System.out.print("\t·");
                                } else {
                                    System.out.print("\t ");
                                }
                                System.out.println(arr2[m]);
                            }
                        }
                    }
                }
            }
            System.out.println();
        }

        for(int i = 0; i < result.getSee_also().size(); i++) {
            System.out.println("【" + (result.getItems().size() + i + 1) + "】" + " See also：" + result.getSee_also().get(i));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        System.out.println("欢迎使用柯林斯词典");
        while (true) {
            System.out.print("请输入需要查询的单词(输入Q/q退出)：");
            String word = input.nextLine();
            if(word.equals("q") || word.equals("Q")) break;
            else if(!dictionary.check(word)) {
                System.out.println("输入错误，请重新输入！");
            } else {
                System.out.println("------------------------------------");
                dictionary.searchWord(word);
            }
        }
    }
}
