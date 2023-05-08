let _registerUser = {
    $scope: null,
    $contentForm: null,
    $registerUserTable: null,

    init: function () {
        this.$scope = $("registerUser");
       /* this.$registerUserTable = $("registerUserTable", this.$scope);*/

        this.events();
    },
    events: function () {
        const _this = this;

        $('#registerUserBtn').on('click', function () {
            console.log('click save btn')
            _registerUser.saveUserData();

        });
        // 주소 조회
        $('#search_daum').on('click', function () {
            new daum.Postcode({
                oncomplete: function (data) {
                    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    let roadAddr = data.roadAddress; // 도로명 주소 변수
                    //let jibunAddr = data.jibunAddress; // 지번 주소 변수

                    //$('#zipCode').val(data.zonecode);
                    if (roadAddr !== '') {
                        $('#address').val(roadAddr);
                    }
                    /*else if(jibunAddr !== ''){
                        $('#addr1').val(jibunAddr);
                    }*/
                }
            }).open();
        });
        $('#chooseImage').on('change', function () {

            ext = $(this).val().split('.').pop().toLowerCase(); //확장자

            //배열에 추출한 확장자가 존재하는지 체크
            if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                /*resetFormElement($(this)); //폼 초기화*/
                window.alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
            } else {
                file = $('#chooseImage').prop("files")[0];
                blobURL = window.URL.createObjectURL(file);
                $('#uploadImg img').attr('src', blobURL);
                $('#uploadImg').slideDown(); //업로드한 이미지 미리보기
            }
        });
    },
    saveUserData: function () {
        const _this = this;
        let formData = new FormData();
        formData.append('usersId', $('#usersId', _this.$contentForm).val());
        formData.append('password', $('#password', _this.$contentForm).val());
        formData.append('address', $('#address', _this.$contentForm).val());
        formData.append('usersName', $('#usersName', _this.$contentForm).val());
        formData.append('age', $('#age', _this.$contentForm).val());
        $.each($("#chooseImage", _this.$contentForm)[0].files, function (k, value) {
            formData.append("multipartFile", value);
        });
        $.ajax({
            type: "POST",
            processData: false,
            contentType: false,
            url: "/user/save",
            data: formData,
            success: function (result) {
                alert(result);
                if (result == "success") {
                    location.href = '/user'
                } else {

                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            }
        });
    }
}

// onload
$(document).ready(function () {
    _registerUser.init();
});