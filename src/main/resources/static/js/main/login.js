let _login = {
    $scope: null,
    $tableList: null,
    $contentForm: null,

    init: function () {
        this.$scope = $("#loginBox");
        this.$tableList = $("");

        this.events();
    },
    events: function () {
        const _this = this;
        $('#loginBtn').on('click', function (key) {
            /* if(key.keycode == 13)*/
            _login.login();
        })


    },
    login: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('usersId', $('#usersId', this.$contentForm).val());
        formData.append('password', $('#password', this.$contentForm).val());
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/main/login",
            data: formData,
            success: function (result) {
                if (result === "success") {
                    location.href = "/user";
                } else {
                    alert("다시입력해주세요");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }
        })
    },

}
$(document).ready(function () {
    _login.init();
})