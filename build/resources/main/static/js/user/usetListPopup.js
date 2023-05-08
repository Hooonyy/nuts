let _userListPopup = {
    $scope : null, // 영역
    $tableList : null,
    $paging: null,

    init : function () {

        this.$scope = $("#userListPopup");
        this.$tableList = $('#userListTable', this.$scope);
        this.$paging = $('#pagingBox', this.$scope);

        this.events();
        console.log("실행");
    },

    events : function () {
        const _this = this;
        this.search(1);

        $(document).on("click", ".chooseUser", function() {
            console.log("choose");
            let chooseUser = $(this);
            let tr = chooseUser.parent();
            $('#usersSeq', opener.document).val(tr.children().eq(0).text());
            $('#userId', opener.document).val(tr.children().eq(1).text());
            window.close();
        });
        //클릭된 페이지로가기
        $(document).on('click', "#pagination li", function () {

            let page = $(this).attr("data-page");
            console.log(page);
            if(page != null){
                _this.search(page);
            }
        });

    },

    search : function(page) {
        const _this = this;

        var param = {
            pageNum: page
        };
        $.ajax({
            type : "GET",
            url : "/user/userList",
            data : param,
            success : function(result){
                _this.dataBind(result);
                console.log(result.userList);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){

            }
        });

    },

    dataBind : function(res) {
        const _this = this;
        _this.$tableList.find('tbody').empty();
        if (res.userList.length > 0) {
            $.each(res.userList, function (i, val) {
                _this.$tableList.append(
                    $('<tr/>').append(
                        $('<td/>').text(val.usersSeq)
                    ).append(
                        $('<td/>').text(val.usersId)
                    ).append(
                        $('<td/>').text(val.usersName)
                    ).append(
                        $('<td/>').text(val.address)
                    ).append(
                        $('<td/>').text(val.age)
                    ).append(
                        $('<button/>').text('선택').attr("class", "chooseUser")
                    )
                )
            });
        } else {
            _this.$tableList.append(
                $('<tr/>').append($('<td/>').attr({colspan : 6}).text('검색결과가 없습니다.'))
            );
        }
        this.$paging.find('#pagination').empty();
        for (let i = 1; i <= (res.listSize / res.pageSize) + 1; i++) {        //시작페이지부터 종료페이지까지 반복문

            if (res.page === i) {                            //현재페이지가 반복중인 페이지와 같다면

                $("#pagination").append("<li class=\"disabled active\"><a>" + i + "</a></li>");    //버튼 비활성화

            } else {

                $("#pagination").append("<li class=\"goPage\" data-page=\"" + i + "\"><a>" + i + "</a></li>"); //버튼 활성화

            }
        }
    }
};

// onload
$(document).ready(function() {
    _userListPopup.init();
});