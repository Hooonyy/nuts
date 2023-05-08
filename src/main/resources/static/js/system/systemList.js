let _systemList = {
    $scope: null,
    $systemList: null,
    $paging: null,

    init: function () {
        this.events();
        this.$scope = $('#systemList', this.$scope);
        this.$systemList = $('#systemListTable', this.$scope);
        this.$paging = $('#pagingBox', this.$scope);
    },
    events: function () {
        const _this = this;
        this.systemList(1);
        $('#registerSystem').on('click', function () {
            console.log('insert click')
            let url = '/system/insert';
            window.open(url, '', '_blank');
        });
        //클릭된 페이지로가기
        $(document).on('click', "#pagination li", function () {
            let page = $(this).attr("data-page");
            if (page != null) {
                _this.systemList(page);
            }
        });

    },
    systemList: function (page) {
        const _this = this;
        var param = {
            pageNum: page,
        }
        $.ajax({
            type: "GET",
            url: "/system/systemList",
            data: param,
            success: function (result) {
                _this.dataBind(result);
                console.log(result);
                console.log("ajax")
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }

        });
    },
    dataBind: function (res) {
        const _this = this;
        console.log(res.systemList)
        console.log("databind")

        _this.$systemList.find('tbody').empty();
        if (res.systemList.length > 0) {
            $.each(res.systemList, function (i, val) {
                _this.$systemList.append(
                    $('<tr/>').append(
                    ).append(
                        $('<td/>').text(val.divisionWork)
                    ).append(
                        $('<td/>').text(val.institutionName)
                    ).append(
                        $('<td/>').text(val.tel)
                    ).append(
                        $('<td/>').text(val.email)
                    ).append(
                        $('<td/>').text(val.useMetasysYn)
                    ).append(
                        $('<td/>').text(val.maintenPerson)
                    ).append(
                        $('<td/>').text(val.maintenTel)
                    ).append(
                        $('<td/>').text(val.maintenEmail)
                    ).append(
                        $('<td/>').append($('<a/>').text(val.systemNm).attr("href", "system/systemInfo/" + val.sysSeq))
                    )
                )
            });
        } else {
            _this.$systemList.append(
                $('<tr/>').append($('<td/>').attr({colspan: 8}).text('검색결과가 없습니다.'))
            );
        }
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
    }
}
$(document).ready(function () {
    _systemList.init();
})