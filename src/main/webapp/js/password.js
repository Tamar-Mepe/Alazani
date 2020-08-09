function validateForm(isCart = false) {
    const current_password = document.forms["changeForm"]["current-password"].value;
    let url = 'http://localhost:8080/ConfirmPassword';
    let result = false;
    $.ajax({
        type: 'POST',
        url: url,
        async: false,
        data: {'current-password': current_password},
        success: function (data) {
            if (data === "1") {
                if (isCart) {
                    const loader = $('<div class="circle-loader"><div class="checkmark draw"></div></div>');
                    $('.loader-wrapper-class').append(loader);
                    $('.loader-text-class').html("Proceeding...");

                    const wait = ms => new Promise(resolve => setTimeout(resolve, ms));
                    wait(2500).then(() => function () {
                            $('.circle-loader').toggleClass('load-complete');
                            $('.checkmark').toggle();
                            $('.loader-text-class').html("Transaction Completed!");
                            wait(1400).then(() => document.getElementById("buy-form-id").submit());
                        }()
                    );

                    result = false;
                } else {
                    result = true;
                }
            } else {
                const elem = document.getElementById('pass-err-no-match');
                if (!elem) {
                    const errMsg = $('<label id="pass-err-no-match" style="color:red" >Password Mismatch</label>');
                    $('.modal-body').append(errMsg);
                }
                result = false;
            }
        }
    });
    return result;

}