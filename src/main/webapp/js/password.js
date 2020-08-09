function validateForm() {
    const current_password = document.forms["changeForm"]["current-password"].value;

    let url = 'http://localhost:8080/ConfirmPassword';
    let result = false;
    $.ajax({
        type: 'POST',
        url: url,
        async: false,
        data: {'current-password': current_password},
        success: function (data) {
            console.log(data);
            if (data === "1") {
                result = true;
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