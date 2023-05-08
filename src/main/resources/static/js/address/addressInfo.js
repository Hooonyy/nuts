let _addressInfo = {
    $scope : null, // 영역
    $tableList : null,

    init : function () {

        this.$scope = $("#addressInfo");
        this.$tableList = $('#addressTable', this.$scope);

        this.events();
        console.log("실행");
    },

    events : function () {
        const _this = this;

        $('#backToList').on('click', function () {
            location.href = '/user';
        });
    },
};

// onload
$(document).ready(function() {
    _addressInfo.init();
});