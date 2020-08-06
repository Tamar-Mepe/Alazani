package models;

import db.Fields;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bot extends BaseModel {
    public static final String TABLE_NAME = "bots";
    public static final Map<String, Object> FIELDS = new LinkedHashMap<>();

    static {
        FIELDS.put("id", Fields.ID);
        FIELDS.put("question", Fields.varchar(256));
        FIELDS.put("answer", Fields.varchar(256));
    }

    private String question;
    private String answer;

    public Bot(String question, String answer) {
        super(TABLE_NAME);
        this.question = question;
        this.answer = answer;
    }

    public static Bot DBToJava(Map<String, String> fields) {
        Bot bot = new Bot(fields.get("question"), fields.get("answer"));
        bot.setId(Integer.parseInt(fields.get("id")));
        return bot;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public static Bot get(int id) {
        Map<String, String> fields = BaseModel.getGeneric(TABLE_NAME, id);
        assert fields != null;
        return DBToJava(fields);
    }

    public Map<String, Object> JavaToDB() {
        return new LinkedHashMap<String, Object>() {
            {
                put("question", question);
                put("answer", answer);
            }
        };
    }

    public static List<Bot> getAll() {
        List<Map<String, String>> fields = BaseModel.getAllGeneric(TABLE_NAME);
        List<Bot> allBots = new ArrayList<>();
        for (Map<String, String> field : fields) {
            allBots.add(DBToJava(field));
        }
        return allBots;
    }

}
