let _systemInfo = {
    $scope : null,
    $systemData : null,
    $contentForm : null,

    init: function (){
        this.$scope = $('#systemInfo')
        this.$systemData = $('#systemData', this.$scope);
        this.$contentForm = $('#systemUpdateForm', this.$scope);

        this.events();
    },
    events: function (){
        $('#backToList').on('click', function () {
            location.href = '/system'
        });
        $('#updateSystemInfo').on('click', function () {
            console.log('click update btn')
            _systemInfo.updateSystemData();
        });
        $('#deleteSystemInfo').on("click", function () {
            console.log("click delete btn")
            _systemInfo.deleteSystemData();
        });
    },

    updateSystemData: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('sysSeq', $('#sysSeq', _this.$contentForm).val());
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
            type: "PUT",
            processData: false,
            contentType: false,
            url: "/system/update",
            data: formData,
            success: function (result) {
                console.log(result);
                if (result == 'success') {
                    alert(result);
                    location.reload();
                } else {
                    alert("실패");
                }
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest, textStatus, errorThrown);
            }
        });
    },

    deleteSystemData: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('sysSeq', $('#sysSeq', _this.$contentForm).val());

        $.ajax({
            type: "DELETE",
            processData: false,
            contentType: false,
            url: "/system/delete",
            data: formData,
            success: function (result) {
                alert(result);
                location.href = "/system";
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest, textStatus, errorThrown);
            }
        });
    }
}
$(document).ready(function (){
    _systemInfo.init();
})