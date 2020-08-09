function validateForm() {
    const current_password = document.forms["changeForm"]["current-password"].value;

    url = 'http://localhost:8080/ConfirmPassword';
    $.ajax({
        type: 'POST',
        url: url,
        data: {'pass': current_password},
        success: function (data) {
            console.log(data);
        }
    });

    const elem = document.getElementById('pass-err-no-match');
    if (!elem) {
        const errMsg = $('<label id="pass-err-no-match" style="color:red" >Password Mismatch</label>');
        $('.modal-body').append(errMsg);
    }
    return false;
}