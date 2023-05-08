let _addressInsert = {
    $scope : null, // 영역
    $contentForm : null,
    $tableList : null,

    init : function () {

        this.$scope = $("#addressInsert");
        this.$contentForm = $("#addressInsertForm", this.$scope);
        this.$tableList = $("#addressTable", this.$scope);

        this.events();
        console.log("address insert 실행");
    },

    events : function () {
        const _this = this;

        //유저 정보 찾기 팝업
        $('#search_user').on('click', function () {
            let url = '/user/userListPopup';
            window.open(url, '', '_blank');
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
                        $('#address1').val(roadAddr);
                    }
                    /*else if(jibunAddr !== ''){
                        $('#addr1').val(jibunAddr);
                    }*/
                }
            }).open();
        });
        //업로드 이미지 선택
        $('#chooseImage').on('click', function (){
           window.open(url, '', '_blank')
        });

        $('#btnSave').on('click', function (){
            _addressInsert.saveAddress();
        });

    },
    //insert form
    saveAddress : function (){
        const _this = this;

        let formData = new FormData();
        formData.append('usersSeq', $('#usersSeq', _this.$contentForm).val());
        formData.append('usersID', $('#usersId', _this.$contentForm).val());
        formData.append('address1', $('#address1', _this.$contentForm).val());
        formData.append('address2', $('#address2', _this.$contentForm).val());
        formData.append('nickname', $('#nickname', _this.$contentForm).val());
        //main주소지 체크 확인
        if ($('#mainAddress').is(":checked") === true){
            formData.append('mainAddress', "Y");
        }else{
            formData.append('mainAddress', "N");
        }

        $.ajax({
            type : "POST",
            processData : false,
            contentType : false,
            url : "/address/save",
            data : formData,
            success : function(result){
                alert(result);
                if(result === "success"){
                    location.href = '/address/addressInfo/' + $('#usersSeq', _this.$contentForm).val();
                }else{

                }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){
            }
        });
    }
};

// onload
$(document).ready(function() {
    _addressInsert.init();
});