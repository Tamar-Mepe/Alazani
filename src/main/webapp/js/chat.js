let questions = [];

// update questions
function writeQuestions(questions, text) {
    function f(bot, i) {
        const buttonTag = $(`<button type="button" class="btn btn-outline-dark" id="${i}"></button>`);
        const spanTag = $(`<span>${bot['question']}</span>`);

        buttonTag.append(spanTag);
        return buttonTag;

    }

    const mainMsg = $('<div class="chat-message clearfix"></div>');
    const mainImg = $('<img src="../images/bot.png" alt="Bot" width="32" height="32">');
    const mainQuestions = $('<div class="chat-message-content clearfix"></div>');
    const mainBot = $('<h5>Bot</h5>');
    const mainText = $(`<p>${text}</p>`);

    mainQuestions.append(mainBot);
    mainQuestions.append(mainText);

    for (let i = 0; i < questions.length; i++)
        mainQuestions.append(f(questions[i], i));

    mainMsg.append(mainImg);
    mainMsg.append(mainImg);
    mainMsg.append(mainQuestions);

    const mainChat = $(document.getElementById('chat-main'));
    mainChat.append('<hr>');
    mainChat.append(mainMsg);
}

// fetch questions
url = 'http://localhost:8080/Questions';
WELCOME_TEXT = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, ' +
    'sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ' +
    'quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute ' +
    'irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ' +
    'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.<br>' +
    'How Can I Help You With?';
$.ajax({
    type: 'GET',
    url: url,
    async: false,
    context: document.body,
    success: function (data) {
        questions = data;
        writeQuestions(questions, WELCOME_TEXT);
        writeQuestions(questions, WELCOME_TEXT);
    },
});


(function () {
    $('#live-chat header').on('click', function () {
        $('.chat').toggle(300, 'swing');
    });
})();