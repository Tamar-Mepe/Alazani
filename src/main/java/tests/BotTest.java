package tests;

import db.DB;
import db.Migration;
import db.MySQL;
import models.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        DB db = MySQL.getInstance();
        Migration.createTables(db);
    }

    @Test
    public void testGet() {
        Bot testBot = new Bot("How are you?", "Im fine");
        testBot.save();
        int savedId = testBot.getId();
        assertEquals("How are you?", testBot.getQuestion());
        assertEquals("Im fine", testBot.getAnswer());

        Bot newBot = Bot.get(savedId);
        assertEquals(newBot.getQuestion(), testBot.getQuestion());
        assertEquals(newBot.getAnswer(), testBot.getAnswer());
    }

    @Test
    public void testGetAll() {
        List<Bot> bots = new ArrayList<Bot>() {
            {
                add(new Bot("question1?", "answer1!"));
                add(new Bot("question2?", "answer2!"));
                add(new Bot("question3?", "answer3!"));
                add(new Bot("question4?", "answer4!"));
                add(new Bot("question5?", "answer5!"));
            }
        };

        // Save all of them in DB
        for (Bot currBot : bots)
            currBot.save();

        List<Bot> allBots = Bot.getAll();
        for (int i = 0; i < allBots.size(); i++) {
            Bot currBot1 = bots.get(i);
            Bot currBot2 = allBots.get(i);

            assertEquals(currBot1.getAnswer(), currBot2.getAnswer());
            assertEquals(currBot1.getQuestion(), currBot2.getQuestion());
        }

        List<ArrayList<String>> questionsAndAnswers = Bot.allQuestionAnswers();

        for (int i = 0; i < questionsAndAnswers.size(); i++) {
            String botId1 = String.valueOf(bots.get(i).getId());
            String question1 = bots.get(i).getQuestion();
            String answer1 = bots.get(i).getAnswer();
            String botId2 = questionsAndAnswers.get(i).get(0);
            String question2 = questionsAndAnswers.get(i).get(1);
            String answer2 = questionsAndAnswers.get(i).get(2);

            assertEquals(botId1, botId2);
            assertEquals(question1, question2);
            assertEquals(answer1, answer2);
        }
    }

}