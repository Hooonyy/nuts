let _productList = {
    $scope: null,
    $productList: null,
    $paging: null,

    init: function () {
        this.events();
        this.$scope = $('#productList', this.$scope);
        this.$productList = $('#productListTable', this.$scope);
        this.$paging = $('#pagingBox', this.$scope);
    },
    events: function () {
        const _this = this;
        this.productList(1);
        $('#registerProduct').on('click', function () {
            console.log('insert click')
            location.href = '/product/insert'
        });
        //클릭된 페이지로가기
        $(document).on('click', "#pagination li", function () {
            let page = $(this).attr("data-page");
            if (page != null) {
                _this.productList(page);
            }
        });
        $('#allexcelFileExport').on('click', function () {
            location.href = '/excelDownload'
        })

    },
    productList: function (page) {
        const _this = this;
        var param = {
            pageNum: page,
        }

        $.ajax({
            type: "GET",
            url: "/product/productList",
            data: param,
            success: function (result) {
                _this.dataBind(result);
                console.log(result.productList);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {

            }
        });

    },
    dataBind: function (res) {
        const _this = this;
        _this.$productList.find('tbody').empty();
        if (res.productList.length > 0) {

            $.each(res.productList, function (i, val) {
                _this.$productList.append(
                    $('<tr/>').append(
                        $('<td/>').text(val.productName)
                    ).append(
                        $('<img>').attr("src", val.productImgpath)
                    ).append(
                        $('<td/>').text(val.productPrice)
                    ).append(
                        $('<td/>').text(val.productContents)
                    ).append(
                        $('<td/>').text(val.productEtc)
                    ).append(
                        $('<td/>').text(val.productDiscountCode)
                    ).append(
                        $('<td/>').text(val.productDiscount)
                    ).append(
                        $('<td/>').text(val.discountPrice)
                    ).append(
                        $('<td/>').append($('<a/>').text('수정').attr("href", "/product/productInfo/" + val.productSeq))
                    )
                )
            });

        } else {
            _this.$productList.append(
                $('<tr/>').append($('<td/>').attr({colspan: 7}).text('검색결과가 없습니다.'))
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
    _productList.init();
})