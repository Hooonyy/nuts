let _productInfo = {
    $scope : null,
    $productData : null,
    $contentForm : null,

    init: function (){
        this.$scope = $('#productInfo')
        this.$productData = $('#productData', this.$scope);
        this.$contentForm = $('#productUpdateForm', this.$scope);

        this.events();
    },
    events: function (){
        $('#backToList').on('click', function () {
            location.href = '/product'
        });
        $('#updateProductInfo').on('click', function () {
            console.log('click update btn')
            _productInfo.updateProductData();
        });
        $('#deleteProductInfo').on("click", function () {
            _productInfo.deleteProductData();
        });

        $("#productPrice").on("propertychange change keyup paste input", function() {
            $('#productDiscountCode').val('none')
            $('#productDiscount').val("");
        });
        $('#productDiscountCode').on('change', function (){
            $('#productDiscount').val("");
            _productInfo.test(this);
        });
        $('#chooseImage').on('change', function () {
            ext = $(this).val().split('.').pop().toLowerCase(); //확장자
            //배열에 추출한 확장자가 존재하는지 체크
            if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                resetFormElement($(this)); //폼 초기화
                window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
            } else {
                file = $('#chooseImage').prop("files")[0];
                blobURL = window.URL.createObjectURL(file);
                $('#productImgpath').attr('src', blobURL);
                $('#productImgpath').slideDown(); //업로드한 이미지 미리보기
            }
        });
    },
    test: function (){
        var val = $(':selected').val();
        if (val == 'none'){
            $('#productDiscount').attr("readonly",true);
        }else if(val == 'percent'){
            $('#productDiscount').attr("readonly",false).attr("placeholder","숫자만 입력하시오").attr("min", 1).attr("max",100);
        }else {
            $('#productDiscount').attr("readonly",false).attr("placeholder","숫자만 입력하시오").attr("min",0).attr("max",val);
        }
        console.log(val);

    },
    updateProductData: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('productSeq', $('#productSeq', _this.$contentForm).val());
        formData.append('productName', $('#productName', _this.$contentForm).val());
        formData.append('productPrice', $('#productPrice', _this.$contentForm).val());
        formData.append('productContents', $('#productContents', _this.$contentForm).val());
        formData.append('productEtc', $('#productEtc', _this.$contentForm).val());
        formData.append('productDiscountCode', $('#productDiscountCode', _this.$contentForm).val());
        formData.append('productDiscount', $('#productDiscount', _this.$contentForm).val());
        formData.append('productIsdel', $('#productIsdel', _this.$contentForm).val());
        $.each($("#chooseImage", _this.$contentForm)[0].files, function (k, value) {
            formData.append("multipartFile", value);
        });

        if (formData.get('productDiscountCode') == 'percent'){
            console.log("percent");
            let num1 = Number(formData.get('productDiscount'));
            console.log(num1);
            if (num1 < 1 || num1 >= 100){
                alert("할인율 범위를 초과했습니다.")
                console.log(formData.get('productDiscount'));
                return false;
            }
        }
        if (formData.get('productDiscountCode') == 'price'){
            if (formData.get('productPrice')>formData.get('productDiscount')){
                alert("할인가격이 상품가격보다 높게 설정되었습니다.");
                return false;
            }
        }

        $.ajax({
            type: "PUT",
            processData: false,
            contentType: false,
            url: "/product/update",
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
    }
    ,
    deleteProductData: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('productSeq', $('#productSeq', _this.$contentForm).val());

        $.ajax({
            type: "DELETE",
            processData: false,
            contentType: false,
            url: "/product/delete",
            data: formData,
            success: function (result) {
                alert(result);
                location.href = "/product";
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest, textStatus, errorThrown);
            }
        });
    }
}
$(document).ready(function (){
    _productInfo.init();
})