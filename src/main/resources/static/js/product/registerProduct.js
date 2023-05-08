let _registerProduct = {
    $scope : null,
    $contentForm: null,
    $registerUserTable: null,

    init: function () {
        this.$scope = $("#registerProduct");
        this.$contentForm = $('#registerProductForm', this.$contentForm);
        this.events();
    },
    events: function (){
        const _this = this;

        $('#registerProductBtn').on('click', function () {
            console.log('click save btn')
            _registerProduct.saveProductData();

        });
        $('#backToList').on('click', function () {
            location.href = '/product'
        });

        $("#selectDiscountValue option:selected").val();
        $('#chooseImage').on('change', function () {

            ext = $(this).val().split('.').pop().toLowerCase(); //확장자

            //배열에 추출한 확장자가 존재하는지 체크
            if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                resetFormElement($(this)); //폼 초기화
                window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
            } else {
                file = $('#chooseImage').prop("files")[0];
                blobURL = window.URL.createObjectURL(file);
                $('#uploadImg img').attr('src', blobURL);
                $('#uploadImg').slideDown(); //업로드한 이미지 미리보기
            }
        });
    },
    saveProductData: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('productName', $('#productName', _this.$contentForm).val());
        formData.append('productPrice', $('#productPrice', _this.$contentForm).val());
        formData.append('productEtc', $('#productEtc', _this.$contentForm).val());
        formData.append('productContents', $('#productContents', _this.$contentForm).val());
        formData.append('productDiscount', $('#productDiscount', _this.$contentForm).val());
        formData.append('productDiscountCode', $('#productDiscountCode', _this.$contentForm).val())
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
            if (formData.get('productPrice')<formData.get('productDiscount')){
                console.log(formData.get('productPrice'))
                console.log(formData.get('productDiscount'))
                alert("할인가격이 상품가격보다 높게 설정되었습니다.");
                return false;
            }
        }
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/product/save",
            data: formData,
            success: function (result) {
                alert(result);
                if (result == "success") {
                    location.href = '/product'
                } else {

                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }
}
$(document).ready(function (){
 _registerProduct.init();
})