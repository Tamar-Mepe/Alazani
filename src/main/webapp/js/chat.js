let questions = [];
let QUESTION_TXT = '<br>What is your question?<br>';

/* EVENT ON QUESTION SELECETION */
function answerQuestion(idx) {
    // 1. Delete Old Questions
    $('.bot-questions').html('');

    // 2. Add question on user side
    const mainMsg = $('<div class="chat-message clearfix"></div>');
    const mainImg = $('<img src="../images/user.svg" alt="Bot" width="40" height="40">');
    const mainQuestions = $('<div class="chat-message-content clearfix"></div>');
    const mainBot = $('<h5 class="chat-label">You</h5>');
    const question = `${questions[idx]['question']}`;
    const mainText = $(`<p>${question}</p>`);

    mainQuestions.append(mainBot);
    mainQuestions.append(mainText);

    mainMsg.append(mainImg);
    mainMsg.append(mainImg);
    mainMsg.append(mainQuestions);

    const mainChat = $(document.getElementById('chat-main'));
    mainChat.append('<hr>');
    mainChat.append(mainMsg);

    // 3. add answer and as questions again
    const wait = ms => new Promise(resolve => setTimeout(resolve, ms));
    wait(1500).then(() => function () {
            writeQuestions(questions, `${questions[idx]['answer']}<br>${QUESTION_TXT}`);
            const elem = document.getElementById('chat-main');
            elem.scrollTop = elem.scrollHeight;
        }()
    );

}

/* WRITE QUESTIONS IN CHAT */
function writeQuestions(questions, text) {
    function f(bot, i) {
        const buttonTag = $(`<button type="button" class="btn btn-outline-dark question-button" onclick="answerQuestion(${i})" id="${i}"></button>`);
        const spanTag = $(`<span>${bot['question']}</span>`);

        buttonTag.append(spanTag);
        return buttonTag;

    }

    const mainMsg = $('<div class="chat-message clearfix"></div>');
    const mainImg = $('<img src="../images/bot.png" alt="Bot" width="40" height="40">');
    const mainQuestions = $('<div class="chat-message-content clearfix"></div>');
    const mainBot = $('<h5 class="chat-label">Alazani Chat Bot</h5>');
    const mainText = $(`<p>${text}</p>`);

    mainQuestions.append(mainBot);
    mainQuestions.append(mainText);

    const botQuestions = $('<div class="bot-questions"></div>')
    for (let i = 0; i < questions.length; i++)
        botQuestions.append(f(questions[i], i));

    mainQuestions.append(botQuestions);
    mainMsg.append(mainImg);
    mainMsg.append(mainImg);
    mainMsg.append(mainQuestions);

    const mainChat = $(document.getElementById('chat-main'));
    mainChat.append('<hr>');
    mainChat.append(mainMsg);
}

/* FETCH QUESTIONS FROM SERVER */
url = 'http://localhost:8080/Questions';
WELCOME_TEXT = 'Hello There! <br> Get answers on your question instantly using our chat bot.';

$.ajax({
    type: 'GET',
    url: url,
    async: false,
    context: document.body,
    success: function (data) {
        questions = data;
        writeQuestions(questions, `${WELCOME_TEXT}<br>${QUESTION_TXT}`);
    },
});


(function () {
    $('#live-chat header').on('click', function () {
        $('.chat').toggle(300, 'swing');
    });
})();