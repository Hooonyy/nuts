let _userList = {
    $scope: null, // 영역
    $tableList: null,
    $profileList: null,
    $userInfo: null,
    $paging: null,

    init: function () {

        this.$scope = $('#userList');
        this.$tableList = $('#userListTable', this.$scope);
        this.$profileList = $('#userProfileTable', this.$scope);
        this.$paging = $('#pagingBox', this.$scope);

        this.events();
        console.log("userList실행");
    },

    events: function () {
        const _this = this;
        this.userList(1);
       /* this.userProfileList(1);*/

        //유저ID 조회
        $('#searchUserId').on('click', function () {
            _this.userList(1);
        });
        //유저배송지등록
        $('#addressInsert').on('click', function () {
            location.href = '/address/insert';
        });
        //신규유저 등록
        $('#registerUserBtn').on('click', function () {
            location.href = '/user/register';
        });
        //클릭된 페이지로가기
        $(document).on('click', "#pagination li", function () {

            let page = $(this).attr("data-page");
            console.log(page);
            if (page != null) {
                _this.userList(page);
            }
        });

    },

    userList: function (page) {
        const _this = this;

        var param = {
            pageNum: page,
            usersId: $("#searchUser").val()
        };
        $.ajax({
            type: "GET",
            url: "/user/userList",
            data: param,
            success: function (result) {
                _this.dataBind(result);
                console.log(result.userList);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }
        });

    },

    dataBind: function (res) {
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
                        $('<td/>').append($('<a/>').text(val.usersName).attr("href", "/address/addressInfo/" + val.usersSeq))
                    ).append(
                        $('<td/>').text(val.address)
                    ).append(
                        $('<td/>').text(val.age)
                    ).append(
                        $('<td/>').append($('<a/>').text("수정").attr("href", "/user/userInfo/" + val.usersSeq))
                    )
                )
            });
        } else {
            _this.$tableList.append(
                $('<tr/>').append($('<td/>').attr({colspan: 6}).text('검색결과가 없습니다.'))
            );
        }
        //paging
        this.$paging.find('#pagination').empty();
        let size = res.listSize / res.pageSize;
        let a = res.listSize % res.pageSize;
        if (a > 0) {
            size = size + 1;
        }
        for (let i = 1; i <= size; i++) {        //시작페이지부터 종료페이지까지 반복문

            if (res.page === i) {                            //현재페이지가 반복중인 페이지와 같다면

                $("#pagination").append("<li class=\"disabled active\"><a>" + i + "</a></li>");    //버튼 비활성화

            } else {

                $("#pagination").append("<li class=\"goPage\" data-page=\"" + i + "\"><a>" + i + "</a></li>"); //버튼 활성화

            }
        }
        //$("#pagination").append("<li class=\"disabled\"><a></a></li>");        //첫페이지로가는버튼 비활성화
    },
}

$(document).ready(function () {
    _userList.init();
});

