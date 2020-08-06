<!doctype html>
<html lang="en">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Droid+Sans:400,700">
<link rel="stylesheet" type="text/css" href="../css/chat.css">


<div id="live-chat">
    <header class="clearfix">
        <a href="#" class="chat-close">x</a>
        <h4>Chat Bot</h4>
        <span class="chat-message-counter">3</span>
    </header>
    <div class="chat">
        <div class="chat-history">
            <img src="../images/bot.png" alt="Bot">
            <div class="chat-message clearfix">
                <img src="../images/bot.png" alt="Bot" width="32" height="32">
                <div class="chat-message-content clearfix">
                    <h5>Bot</h5>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                        laboris
                        nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                        velit
                        esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                        sunt in
                        culpa qui officia deserunt mollit anim id est laborum.</p>
                    <button type="button" class="btn btn-outline-dark">
                        <span>wqwerwerwrewerwewerwe rwerwerwe</span>
                    </button>
                </div> <!-- end chat-message-content -->
            </div> <!-- end chat-message -->
<%--            <hr>--%>
<%--            <div class="chat-message clearfix">--%>
<%--                <img src="http://gravatar.com/avatar/2c0ad52fc5943b78d6abe069cc08f320?s=32" alt="" width="32"--%>
<%--                     height="32">--%>
<%--                <div class="chat-message-content clearfix">--%>
<%--                    <span class="chat-time">13:37</span>--%>
<%--                    <h5>Marco Biedermann</h5>--%>
<%--                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis, nulla accusamus magni vel--%>
<%--                        debitis numquam qui tempora rem voluptatem delectus!</p>--%>
<%--                </div> <!-- end chat-message-content -->--%>
<%--            </div> <!-- end chat-message -->--%>
<%--            <hr>--%>
<%--            <div class="chat-message clearfix">--%>
<%--                <img src="ASD">--%>
<%--                <div class="chat-message-content clearfix">--%>
<%--                    <span class="chat-time">13:38</span>--%>
<%--                    <h5>John Doe</h5>--%>
<%--                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing.</p>--%>
<%--                </div> <!-- end chat-message-content -->--%>
<%--            </div> <!-- end chat-message -->--%>
<%--            <hr>--%>
        </div> <!-- end chat-history -->
    </div> <!-- end chat -->
</div> <!-- end live-chat -->
<script>
    (function () {
        $('#live-chat header').on('click', function () {
            $('.chat').toggle(300, 'swing');
        });
    })();

    function showQuestions(){

    }
</script>

</html>