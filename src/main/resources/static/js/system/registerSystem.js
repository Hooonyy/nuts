let _registerSystem = {
    $scope : null,
    $contentForm: null,

    init: function () {
        this.$scope = $("#registerSystem");
        this.$contentForm = $('#registerSystemForm', this.$contentForm);
        this.events();
    },
    events: function (){
        const _this = this;

        $('#registerSystemBtn').on('click', function () {
            console.log('click save btn')
            _registerSystem.saveSystemData();

        });
        $('#backToList').on('click', function () {
            location.href = '/system'
        });
    },
    saveSystemData: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('systemNm', $('#systemNm', _this.$contentForm).val());
        formData.append('divisionWork', $('#divisionWork', _this.$contentForm).val());
        formData.append('institutionName', $('#institutionName', _this.$contentForm).val());
        formData.append('tel', $('#tel', _this.$contentForm).val());
        formData.append('email', $('#email', _this.$contentForm).val());
        formData.append('useMetasysYn', $('#useMetasysYn', _this.$contentForm).val());
        formData.append('maintenPerson', $('#maintenPerson', _this.$contentForm).val());
        formData.append('maintenTel', $('#maintenTel', _this.$contentForm).val());
        formData.append('maintenEmail', $('#maintenEmail', _this.$contentForm).val());

        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/system/save",
            data: formData,
            success: function (result) {
                alert(result);
                if (result == "success") {
                    window.close();
                    opener.parent.location.reload();
                    location.href = '/system'
                } else {

                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }
}
$(document).ready(function (){
    _registerSystem.init();
})