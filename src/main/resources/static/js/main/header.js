let _header = {
    $scope:null,

    init:function (){
        this.$scope = $("#userProfile");

        this.events();
    },
    events: function (){
        this.userProfile();
    },
    userProfile: function (){
        /*let uid = sessionStorage.getItem("user");
        let pwd = sessionStorage.getItem("password");
        console.log(uid);
        console.log(pwd)*/
    }
}
$(document).ready(function (){
    _header.init();
})